package com.ssafy.api.controller;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.api.service.BoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import com.ssafy.db.repository.BoardRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.util.List;


/**
 * 게시판 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "게시판 API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping(value = "/insert", consumes = {"multipart/form-data"})
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
    public ResponseEntity<Board> detail(@ApiIgnore Authentication authentication, @PathVariable int boardId) {
        Board board = boardService.detailBoard(boardId);

        return ResponseEntity.status(200).body(board);
    }

//    @GetMapping("/{classId}")
//    public String boardList(Model model,
//                            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
//                            @PathVariable int classId) {
        // 검색기능 하고싶으면 아래들 주석 참고
        //                           @RequestParam(required = false, defaultValue = "") String field,
        //                           @RequestParam(required = false, defaultValue = "") String word
//        Page<Board> blist=boardRepository.findAll(pageable);
//        if(field.equals("username")) {
//            ulist = userRepository.findByUsernameContaining(word, pageable);
//        }else if(field.equals("email")){
//            ulist = userRepository.findByEmailContaining(word, pageable);
//        }

//        int pageNumber=blist.getPageable().getPageNumber(); //현재페이지
//        int totalPages=blist.getTotalPages(); //총 페이지 수. 검색에따라 10개면 10개..
//        int pageBlock = 5; //블럭의 수 1, 2, 3, 4, 5
//        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
//        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
//        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;
//        model.addAttribute("startBlockPage", startBlockPage);
//        model.addAttribute("endBlockPage", endBlockPage);
//        model.addAttribute("blist", blist); return blist; }


    @GetMapping("/")
    @ApiOperation(value = "게시글 목록", notes = "게시글 리스트를 가져온다.")
    public List<Board> list(Model model,
                       @RequestParam(value = "classId") int classId,
                       @RequestParam(value = "boardType") String boardType ) {
        // @RequestParam(value = "page", defaultValue = "1") int pageNum

        List<Board> boardList = boardService.getBoardList(classId,boardType);
        //int[] pageList = boardService.getPageList(pageNum);

        model.addAttribute("boardList", boardList);
        //model.addAttribute("pageList", pageList);

        return boardList;
    }

    @GetMapping("/search")
    @ApiOperation(value = "게시글 검색", notes = "검색 단어로 게시글을 검색한다.")
    public List<Board> search(Model model,
                                 @RequestParam(value = "classId") int classId,
                                 @RequestParam(value = "boardType") String boardType,
                                 @RequestParam(value = "keyword") String keyword){
        List<Board> boardList = boardService.searchBoard(classId,boardType,keyword);
        model.addAttribute("boardList", boardList);

        return boardList;
    }


}
