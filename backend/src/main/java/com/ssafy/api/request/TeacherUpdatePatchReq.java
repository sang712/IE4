package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([PATCH] /api/v1/users/{userId}) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class UserUpdatePatchReq {
	@ApiModelProperty(name="유저 이름", example="your_name")
	String name;
	@ApiModelProperty(name="유저 부서", example="your_department")
	String department;
	@ApiModelProperty(name="유저 직급", example="your_position")
	String position;
}
