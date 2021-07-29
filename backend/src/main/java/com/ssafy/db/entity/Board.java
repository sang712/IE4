package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@Table(name="board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {
    String boardType;
    int userId;
    String userName;
    String title;
    String content;
    int classId;

    @Builder
    public Board( String boardType, int userId, String userName, int classId, String title, String content) {
        this.boardType = boardType;
        this.userId = userId;
        this.userName = userName;
        this.classId = classId;
        this.title = title;
        this.content = content;
    }

    public Board toEntity(){
        Board board = Board.builder()
                .boardType(boardType)
                .userId(userId)
                .userName(userName)
                .classId(classId)
                .title(title)
                .content(content)
                .build();
        return board;
    }
}
