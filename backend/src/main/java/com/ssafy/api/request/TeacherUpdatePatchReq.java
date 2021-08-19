package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

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
