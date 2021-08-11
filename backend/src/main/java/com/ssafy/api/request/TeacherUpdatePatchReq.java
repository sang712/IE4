package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([PATCH] /users?id=1&classId=101) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class TeacherUpdatePatchReq {
	@ApiModelProperty(name="id", example="id")
	int id;
	@ApiModelProperty(name="classId", example="classId")
	int classId;
	@ApiModelProperty(name="유저 비밀번호", example="your_password")
	String password;
	@ApiModelProperty(name="유저 전화번호", example="your_phone")
	String phone;
	@ApiModelProperty(name="유저 주소", example="your_address")
	String address;
	@ApiModelProperty(name="반 급훈", example="classMotto")
	String classMotto;
}
