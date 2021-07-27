package com.ssafy.db.entity;

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

}
