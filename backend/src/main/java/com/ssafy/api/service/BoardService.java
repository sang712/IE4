package com.ssafy.api.service;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.api.response.BoardDto;
import com.ssafy.db.entity.Board;

import java.util.List;

public interface BoardService {
    Board insertBoard(BoardDto boardDto);
    Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, int boardId);
    int deleteBoard(int boardId);
    Board detailBoard(int boardId);
    List<BoardDto> getBoardList(int classID, String boardType);

}
