package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/* 게시판 글 등록 API ([POST] /api/v1/users) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("BoardRegisterPostRequest")
public class BoardRegisterPostReq {
    @ApiModelProperty(name="게시판 글 type", example="공지사항")
    String boardType;
    @ApiModelProperty(name="게시판 작성자 ID", example="3")
    int userId;
    @ApiModelProperty(name="게시판 작성자 이름", example="hyojin")
    String userName;
    @ApiModelProperty(name="게시판 글 제목", example="boardTest")
    String title;
    @ApiModelProperty(name="게시판 글 내용", example="example_content")
    String content;

    @ApiModelProperty(name="게시판 글 첨부파일 리스트")
    List<BoardFileRegisterPostReq> fileList;
}