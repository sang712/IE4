package com.ssafy.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
    public Board(int id, String boardType, int userId, String userName, int classId, String title, String content) {
        super.id = id;
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
