package com.ssafy.api.service;

import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardService {
    BoardFile insertBoard(MultipartFile files, Board board) throws IOException;
    Board insertBoard(Board board) throws IOException;
    Board updateBoard(Board board)throws IOException;
    BoardFile updateBoard(MultipartFile files, Board board) throws IOException;
    int deleteBoard(int boardId);
    int deleteBoardFile(int boardId);
    BoardFile detailBoardFile(int boardId);
    Board detailBoard(int boardId);

    Page<Board> boardPage(int classId, String boardType, Pageable pageRequest);
    Page<Board> searchBoardPage(int classId, String boardType, Pageable pageRequest, String keyword);

}
