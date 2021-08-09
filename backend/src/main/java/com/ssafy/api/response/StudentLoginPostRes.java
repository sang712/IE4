package com.ssafy.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 학생 로그인 API ([POST] /login) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("StudentLoginPostResponse")
public class StudentLoginPostRes extends UserLoginPostRes{
	@ApiModelProperty(name="STUDENT SNUM", example="13")
	int snum;
	@ApiModelProperty(name="Teacher Name", example="임아연")
	String teacherName;
	@ApiModelProperty(name="Teacher teacherProfileImgUrl", example="profile/teacher_601.jpg")
	String teacherProfileImgUrl;

	public static StudentLoginPostRes of(int id, int classId, String position, String name, int snum, String profileImgUrl, String teacherProfileImgUrl, String sex, String teacherName, String accessToken) {
		StudentLoginPostRes res = new StudentLoginPostRes();
		res.setId(id);
		res.setClassId(classId);
		res.setPosition(position);
		res.setSnum(snum);
		res.setName(name);
		res.setProfileImgUrl(profileImgUrl);
		res.setTeacherProfileImgUrl(teacherProfileImgUrl);
		res.setSex(sex);
		res.setTeacherName(teacherName);
		res.setAccessToken(accessToken);
		return res;
	}
}
