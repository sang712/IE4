package com.ssafy.api.service;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.api.response.BoardDto;
import com.ssafy.db.entity.Board;
import com.ssafy.db.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */

@Service("boardService")
public class BoardServiceImpl implements BoardService{
    private static final int BLOCK_PAGE_NUM_COUNT = 5;
    private static final int PAGE_POST_COUNT = 4;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board insertBoard(BoardDto boardDto) {
        return boardRepository.save(boardDto.toEntity());
    }

    @Override
    public Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, int boardId) {
        Board board = boardRepository.findBoardById(boardId).get();

        board.setTitle(boardUpdateInfo.getTitle());
        board.setContent(boardUpdateInfo.getContent());
        return boardRepository.save(board);
    }

    @Override
    public int deleteBoard(int boardId) {
        Optional<Board> board = boardRepository.findBoardById(boardId);

        boardRepository.delete(board.get());
        return 1;
    }

    @Override
    public Board detailBoard(int boardId) {
        Board board = boardRepository.findBoardById(boardId).get();
        return board;
    }

//    @Override
//    public List<Board> getBoardList(int classID, int pageNum) {
//
//        Page<Board> page = boardRepository
//                .findAll(PageRequest.of(pageNum-1, PAGE_POST_COUNT, Sort.by(Sort.Direction.ASC, "createDate")));
//
//        List<Board> boards = page.getContent();
//        List<Board>
//        return null;
//    }


}
