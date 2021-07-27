package com.ssafy.api.service;

import com.ssafy.api.request.BoardRegisterPostReq;
import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.db.entity.Board;

public interface BoardService {
    Board insertBoard(BoardRegisterPostReq boardRegisterPosReq);
    Board updateBoard(BoardUpdatePatchReq boardUpdateInfo, int boardId);
    int deleteBoard(int boardId);
}
