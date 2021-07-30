package com.ssafy.api.service;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardService {
    BoardFile insertBoard(MultipartFile files, Board board) throws IOException;
    Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, int boardId);
    int deleteBoard(int boardId);
    BoardFile detailBoardFile(int boardId);
    Board detailBoard(int boardId);

    //List<Board> getBoardList(int classId, String boardType);
    //List<Board> searchBoard(int classId, String boardType, String keyword);
    Page<Board> boardPage(int classId, String boardType, Pageable pageRequest);
    Page<Board> searchBoardPage(int classId, String boardType, Pageable pageRequest, String keyword);
}
