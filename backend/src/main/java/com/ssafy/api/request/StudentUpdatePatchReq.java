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
public class StudentUpdatePatchReq {
	@ApiModelProperty(name="유저 비밀번호", example="your_password")
	String password;
	@ApiModelProperty(name="유저 전화번호", example="your_phone")
	String phone;
	@ApiModelProperty(name="유저 주소", example="your_address")
	String address;
	@ApiModelProperty(name="유저 프로필", example="your_profile_img_url")
	String profileImgUrl;
	@ApiModelProperty(name="부모님 전화번호", example="parent_phone")
	String parentPhone;
	@ApiModelProperty(name="비밀전호 질문답", example="your_password_answer")
	String passwordAnswer;
}
