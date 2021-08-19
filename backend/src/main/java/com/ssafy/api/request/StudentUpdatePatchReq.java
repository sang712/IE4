package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Getter
@Setter
@ApiModel("UserUpdatePatchRequest")
public class StudentUpdatePatchReq {
	@ApiModelProperty(name="id", example="id")
	int id;
	@ApiModelProperty(name="유저 비밀번호", example="your_password")
	String password;
	@ApiModelProperty(name="유저 전화번호", example="your_phone")
	String phone;
	@ApiModelProperty(name="유저 주소", example="your_address")
	String address;
	@ApiModelProperty(name="부모님 전화번호", example="parent_phone")
	String parentPhone;
	@ApiModelProperty(name="비밀전호 질문답", example="your_password_answer")
	String passwordAnswer;
}
