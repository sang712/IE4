package com.ssafy.api.controller;

import com.ssafy.api.request.BoardInsertPostReq;
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
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @Autowired
    BoardRepository boardRepository;

    @PostMapping(value = "", consumes = {"multipart/form-data"})
    @ApiOperation(value = "게시판 글과 파일 등록", notes = "게시판에 글 및 파일업로드 작성한다.")
    public ResponseEntity<? extends BaseResponseBody> insertBoardFile(Board board, MultipartFile files) throws IOException {

        if (files != null){
            BoardFile boardfile = boardService.insertBoard(files, board);
        }else{
            Board boardfile = boardService.insertBoard(board);
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @PatchMapping(value = "", consumes = {"multipart/form-data"})
    @ApiOperation(value = "게시판 글과 파일 수정", notes = "게시판에 글 및 파일업로드 수정한다.")
    public ResponseEntity<? extends BaseResponseBody> update(Board board, MultipartFile files) throws IOException {

        if (files != null){
            BoardFile boardfile = boardService.updateBoard(files, board);
        }else{
            Board boardfile = boardService.updateBoard(board);
        }

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping("/{boardId}")
    @ApiOperation(value = "게시물 삭제", notes = "해당 게시물을 삭제한다..")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 204, message = "삭제 성공"),
            @ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> delete(
            @PathVariable @ApiParam(value = "게시물 정보", required = true) int boardId) {
        int deletefileResult = boardService.deleteBoardFile(boardId);
        int deleteResult = boardService.deleteBoard(boardId);
        if(deleteResult == 1)
            return ResponseEntity.status(204).body(BaseResponseBody.of(204, "삭제 성공"));
        else
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "존재하지 않습니다."));
    }

    @GetMapping("/{boardId}")
    @ApiOperation(value = "게시글 확인", notes = "게시글을 자세히 확인한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "글 없음"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<BoardDetailRes> detail(@ApiIgnore Authentication authentication, @PathVariable int boardId) {
        Board board = boardService.detailBoard(boardId);
        BoardFile file = boardService.detailBoardFile(boardId);
        return ResponseEntity.status(200).body(BoardDetailRes.of(board, file));
    }

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
