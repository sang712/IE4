package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 게시판 수정 API ([PATCH] /api/v1/board/{boardId}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("BoardUpdatePatchRequest")
public class BoardUpdatePatchReq {
    @ApiModelProperty(name="게시판 글 제목", example="Test-Update-Title")
    String title;
    @ApiModelProperty(name="게시판 글 내용", example="Test-Update-Board-Content")
    String content;
}
