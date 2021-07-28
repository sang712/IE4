package com.ssafy.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 회원가입 API ([POST] /register) 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ApiModel("StudentRegisterPostRequest")
public class StudentRegisterPostReq {
	@ApiModelProperty(name="유저 ID", example="ssafy_web")
	String loginId;
	@ApiModelProperty(name="유저 Password", example="your_password")
	String password;
	@ApiModelProperty(name="유저 이름", example="your_name")
	String name;
	@ApiModelProperty(name="유저 번호", example="your_phone")
	String phone;
	@ApiModelProperty(name="배정 반", example="your_classId")
	int classId;
	@ApiModelProperty(name="유저 주소", example="your_address")
	String address;
	@ApiModelProperty(name="유저 프로필사진", example="your_profile_img")
	String profileImgUrl;
	@ApiModelProperty(name="유저 배정번호", example="your_num")
	int snum;
	@ApiModelProperty(name="유저 성별", example="your_sex")
	String sex;
	@ApiModelProperty(name="부모님 번호", example="parent_phone")
	String parentPhone;
	@ApiModelProperty(name="비밀번호 질문", example="password_question")
	String passwordQuestion;
	@ApiModelProperty(name="비밀번호 질문 답", example="password_answer")
	String passwordAnswer;
}
