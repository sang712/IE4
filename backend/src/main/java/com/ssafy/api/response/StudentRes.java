package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원(학생) 본인 정보 조회 API ([GET] /users/id) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public class StudentRes extends UserRes{
    @ApiModelProperty(name="User Snum")
    int snum;
    @ApiModelProperty(name="Parent Phone")
    String parentPhone;
    @ApiModelProperty(name="Password Question")
    String passwordQuestion;
    @ApiModelProperty(name="Password Answer")
    String passwordAnswer;

    public static StudentRes of(User user, Student student) {
        StudentRes res = new StudentRes();
        res.setId(user.getId());
        res.setPosition(user.getPosition());
        res.setLoginId(user.getLoginId());
        res.setPassword(user.getPassword());
        res.setName(user.getName());
        res.setSex(user.getSex());
        res.setPhone(user.getPhone());
        res.setAddress(user.getAddress());
        res.setProfileImgUrl(user.getProfileImgUrl());
        res.setSnum(student.getSnum());
        res.setParentPhone(student.getParentPhone());
        res.setPasswordQuestion(student.getPasswordQuestion());
        res.setPasswordAnswer(student.getPasswordAnswer());
        return res;
    }
}
