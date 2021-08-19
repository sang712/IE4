package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 게시글 조회 API ([GET] /api/users/{boardId) 요청에 대한 응답값 정의.
 */

@Getter
@Setter
@ApiModel("BoardDetailResponse")
public class BoardDetailRes {
    int boardId;
    int userId;
    String boardType;
    String writer;
    String title;
    String content;
    int classId;
    String fileName;
    String fileUrl;
    LocalDateTime regDt;

    public static BoardDetailRes of(Board board, BoardFile boardFile) {
        BoardDetailRes detailRes = new BoardDetailRes();
        detailRes.setBoardId(board.getId());
        detailRes.setUserId(board.getUserId());
        detailRes.setBoardType(board.getBoardType());
        detailRes.setWriter(board.getUserName());
        detailRes.setClassId(board.getClassId());
        detailRes.setTitle(board.getTitle());
        detailRes.setContent(board.getContent());
        detailRes.setRegDt(board.getRegDt());
        if (boardFile == null){
            detailRes.setFileName(null);
            detailRes.setFileUrl(null);
        }else{
            detailRes.setFileName(boardFile.getFileName());
            detailRes.setFileUrl(boardFile.getFileUrl());
        }
        return detailRes;
    }
}
