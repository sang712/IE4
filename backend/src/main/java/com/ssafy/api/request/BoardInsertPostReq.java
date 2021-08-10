package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardInsertPostReq")
public class BoardInsertPostReq {
    @ApiModelProperty(name="게시판 글 제목", example="Test-Update-Title")
    int classId;
    @ApiModelProperty(name="게시판 글 제목", example="Test-Update-Title")
    int userId;
    @ApiModelProperty(name="게시판 글 제목", example="Test-Update-Title")
    String userName;
    @ApiModelProperty(name="게시판 글 제목", example="Test-Update-Title")
    String boardType;
    @ApiModelProperty(name="게시판 글 내용", example="Test-Update-Board-Content")
    String title;
    @ApiModelProperty(name="게시판 글 내용", example="Test-Update-Board-Content")
    String content;
}
