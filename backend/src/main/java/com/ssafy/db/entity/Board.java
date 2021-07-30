package com.ssafy.db.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name="board")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Board extends BaseEntity {
    String boardType;
    int userId;
    String userName;
    String title;
    String content;
    int classId;
    @CreationTimestamp
    LocalDateTime regDt;

    @Builder
    public Board( String boardType, int userId, String userName, int classId, String title, String content) {
        this.boardType = boardType;
        this.userId = userId;
        this.userName = userName;
        this.classId = classId;
        this.title = title;
        this.content = content;
        this.regDt = LocalDateTime.now();
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
