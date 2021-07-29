package com.ssafy.api.service;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BoardService {
    BoardFile insertBoard(MultipartFile files, Board board) throws IOException;
    Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, int boardId);
    int deleteBoard(int boardId);
    Board detailBoard(int boardId);
    List<Board> getBoardList(int classId, String boardType);
    List<Board> searchBoard(int classId, String boardType, String keyword);

}
