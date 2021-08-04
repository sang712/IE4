package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 로그인 API ([POST] /login) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserLoginPostResponse")
public class StudentLoginPostRes extends UserLoginPostRes{
	@ApiModelProperty(name="STUDENT SNUM", example="13")
	int snum;

	public static StudentLoginPostRes of(int id, int classId, String position, String name, int snum, String profileImgUrl, String accessToken) {
		StudentLoginPostRes res = new StudentLoginPostRes();
		res.setId(id);
		res.setClassId(classId);
		res.setPosition(position);
		res.setSnum(snum);
		res.setName(name);
		res.setProfileImgUrl(profileImgUrl);
		res.setAccessToken(accessToken);
		return res;
	}
}