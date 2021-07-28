package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardFileRegisterPostReq")
public class BoardFileRegisterPostReq {
    @ApiModelProperty(name="첨부파일  ID")
    int fileId;
    @ApiModelProperty(name="게시판 글 ID")
    int boardId;
    @ApiModelProperty(name="첨부파일 이름")
    int fileName;
    @ApiModelProperty(name="첨부파일 크기")
    int fileSize;
    @ApiModelProperty(name="첨부파일 종류")
    int fileContentType;
    @ApiModelProperty(name="첨부파일 URL")
    int fileIUrl;


}
