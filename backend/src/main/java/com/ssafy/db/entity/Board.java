package com.ssafy.db.entity;

import lombok.*;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Board extends BaseEntity {
    String boardType;
    int userId;
    String userName;
    String title;
    String content;
    int classId;

    @Builder
    public Board(String boardType, int userId, String userName, int classId, String title, String content) {
        this.boardType = boardType;
        this.userId = userId;
        this.userName = userName;
        this.classId = classId;
        this.title = title;
        this.content = content;
    }

    public Board() {
    }
}
