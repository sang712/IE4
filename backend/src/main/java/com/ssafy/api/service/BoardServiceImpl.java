package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.db.entity.Board;
import com.ssafy.db.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */

@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Override
    public Board insertBoard(BoardRegisterPostReq boardRegisterInfo) {
        Board board = new Board();

        board.setBoardType(boardRegisterInfo.getBoardType());
        board.setUserId(boardRegisterInfo.getUserId());
        board.setUserName(boardRegisterInfo.getUserName());
        board.setTitle(boardRegisterInfo.getTitle());
        board.setContent(boardRegisterInfo.getContent());

        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, Integer boardId) {
        Board board = boardRepository.findBoardByBoardId(boardId).get();

        board.setTitle(boardUpdateInfo.getTitle());
        board.setContent(boardUpdateInfo.getContent());
        return boardRepository.save(board);
    }
}
