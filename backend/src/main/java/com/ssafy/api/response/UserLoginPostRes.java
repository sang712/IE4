package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;

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
public class UserLoginPostRes {
	@ApiModelProperty(name="USER ID", example="21")
	int id;
	@ApiModelProperty(name="Class ID", example="101")
	int classId;
	@ApiModelProperty(name="USER POSITION", example="학생")
	String position;
	@ApiModelProperty(name="USER NAME", example="김싸피")
	String name;
	@ApiModelProperty(name="USER ProfileImgUrl", example="~")
	String profileImgUrl;
	@ApiModelProperty(name="JWT 인증 토큰", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;
	
	public static UserLoginPostRes of(int id, int classId, String position, String name, String profileImgUrl, String accessToken) {
		UserLoginPostRes res = new UserLoginPostRes();
		res.setId(id);
		res.setClassId(classId);
		res.setPosition(position);
		res.setName(name);
		res.setProfileImgUrl(profileImgUrl);
		res.setAccessToken(accessToken);
		return res;
	}
}
