package com.ssafy.api.controller;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.api.response.BoardDto;
import com.ssafy.api.service.BoardService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Board;
import com.ssafy.db.repository.BoardRepository;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping()
    @ApiOperation(value = "게시판 글 등록", notes = "게시판에 글을 작성한다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "없음"), @ApiResponse(code = 500, message = "서버 오류") })
    public ResponseEntity<? extends BaseResponseBody> register(
            @RequestBody @ApiParam(value="글 작성 정보", required = true) BoardDto boardDto) {
        Board board = boardService.insertBoard(boardDto);

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

    public 
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


//    @GetMapping("/{classId}")
//    public String list(Model model, @RequestParam(value = "page", defaultValue = "1") int pageNum, @PathVariable int classId) {
//        List<Board> boardList = boardService.getBoardlist(pageNum);
//        int[] pageList = boardService.getPageList(pageNum);
//
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("pageList", pageList);
//
//        return "board/list.html";
//    }
}
