package com.ssafy.api.controller;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.api.response.BoardDetailRes;
import com.ssafy.api.service.BoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import com.ssafy.db.repository.BoardRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;


/**
 * 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    @ApiOperation(value = "게시판 글과 파일 등록", notes = "게시판에 글 및 파일업로드 작성한다.")
    public ResponseEntity<? extends BaseResponseBody> insertBoardFile(
            @RequestParam(value = "classId",required = true) int classId,
            @RequestParam(value = "boardType", required = true) String boardType,
            @RequestParam(value = "userId", required = true) int userId,
            @RequestParam(value = "userName", required = true) String userName,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "content",required = false) String content,
            @RequestParam("file") MultipartFile files) throws IOException {

        Board board = new Board(boardType, userId, userName, classId, title,content);
        BoardFile boardfile = boardService.insertBoard(files, board);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PatchMapping("/{boardId}")
    @ApiOperation(value = "게시판 글 수정", notes = "글의 제목 및 내용을 수정한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> update(
            @PathVariable @ApiParam(value="게시판 정보", required = true) Integer boardId, @RequestBody @ApiParam(value="게시판 아이디", required = true) BoardUpdatePatchReq boardUpdateInfo) {

        Board board = boardService.updateBoard(boardUpdateInfo, boardId);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시물 삭제", notes = "해당 게시물을 삭제한다..")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 204, message = "삭제 성공"),
            @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> delete(
            @PathVariable @ApiParam(value = "게시물 정보", required = true) int boardId) {

        int deleteResult = boardService.deleteBoard(boardId);

        if(deleteResult == 1)
            return ResponseEntity.status(204).body(BaseResponseBody.of(204, "삭제 성공"));
        else
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "사용자가 존재하지 않습니다."));
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 확인", notes = "게시글을 자세히 확인한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "글 없음"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<BoardDetailRes> detail(@ApiIgnore Authentication authentication, @PathVariable int boardId) {
        System.out.println("게시물 상세내용 조회 시도------->"+ boardId);
        Board board = boardService.detailBoard(boardId);
        System.out.print("board는------------> "+ board);
        BoardFile file = boardService.detailBoardFile(boardId);
        System.out.print("board file은------------> "+ file);
        return ResponseEntity.status(200).body(BoardDetailRes.of(board, file));
    }
// 일반 게시글
//    @GetMapping("/")
//    @ApiOperation(value = "게시글 목록", notes = "게시글 리스트를 가져온다.")
//    public List<Board> list(Model model,
//                       @RequestParam(value = "classId") int classId,
//                       @RequestParam(value = "boardType") String boardType) {
//        List<Board> boardList = boardService.getBoardList(classId,boardType);
//
//        model.addAttribute("boardList", boardList);
//        return boardList;
//    }

//    @GetMapping("/search")
//    @ApiOperation(value = "게시글 검색", notes = "검색 단어로 게시글을 검색한다.")
//    public List<Board> search(Model model,
//                                 @RequestParam(value = "classId") int classId,
//                                 @RequestParam(value = "boardType") String boardType,
//                                 @RequestParam(value = "keyword") String keyword){
//        List<Board> boardList = boardService.searchBoard(classId,boardType,keyword);
//        model.addAttribute("boardList", boardList);
//
//        return boardList;
//    }


    //pageing 목록 불러오기
    @CrossOrigin
    @GetMapping("")
    public Page<Board> pagingBoard(@PageableDefault(size=5, sort="regDt", direction = Sort.Direction.DESC) Pageable pageRequest,
        @RequestParam(value = "classId") int classId,
        @RequestParam(value = "boardType") String boardType) {
        Page<Board> boardList = boardService.boardPage(classId, boardType, pageRequest);

        return boardList;
    }
    //paging 검색 목록 불러오기
    @CrossOrigin
    @GetMapping("/page/{keyword}")
    public Page<Board> pagingBoard(@PageableDefault(size=5, sort="regDt") Pageable pageRequest,
                                   @RequestParam(value = "classId") int classId,
                                   @RequestParam(value = "boardType") String boardType,
                                   @PathVariable String keyword) {
        Page<Board> boardList = boardService.searchBoardPage(classId,boardType, pageRequest, keyword);

        return boardList;
    }
}
