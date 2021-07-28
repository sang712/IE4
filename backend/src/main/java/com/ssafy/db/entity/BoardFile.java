package com.ssafy.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class BoardFile extends BaseEntity{
    int boardId;
    String fileName;
    String fileSize;
    String fileContentType;
    String fileUrl;
}
