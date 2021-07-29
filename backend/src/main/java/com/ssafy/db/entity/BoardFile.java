package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name="board_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardFile extends BaseEntity{

    int boardId;
    String fileName;
    long fileSize;
    //String fileContentType;
    String fileUrl;

    @Builder
    public BoardFile(int boardId, String fileName, long fileSize, String fileUrl) {
        this.boardId = boardId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileUrl = fileUrl;
    }

    public BoardFile toEntity(){
        BoardFile boardfile = BoardFile.builder()
                .boardId(boardId)
                .fileName(fileName)
                .fileSize(fileSize)
                .fileUrl(fileUrl)
                .build();
        return boardfile;
    }

    @Override
    public String toString() {
        return "BoardFile{" +
                "boardId=" + boardId +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
