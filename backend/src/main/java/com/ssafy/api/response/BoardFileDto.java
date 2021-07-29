package com.ssafy.api.response;

import com.ssafy.db.entity.BoardFile;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@ApiModel("BoardFile")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardFileDto {
    @ApiModelProperty(name="게시판 글 ID")
    int boardId;
    @ApiModelProperty(name="첨부파일 이름")
    String fileName;
    @ApiModelProperty(name="첨부파일 크기")
    long fileSize;
//    @ApiModelProperty(name="첨부파일 종류")
//    int fileContentType;
     @ApiModelProperty(name="첨부파일 URL")
     String fileUrl;

    public BoardFile toEntity() {
        BoardFile boardFile = BoardFile.builder()
                .boardId(boardId)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileSize(fileSize)
                .build();
        return boardFile;
    }

    @Builder
    public BoardFileDto(int boardId, String fileName, int fileSize, String fileUrl) {
        this.boardId = boardId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        //this.fileContentType = fileContentType;
        this.fileUrl = fileUrl;
    }
}
