package com.ssafy.api.response;

import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원(선생님) 본인 정보 조회 API ([GET] /api/users/id) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("TeacherResponse")
public class TeacherRes extends UserRes{
    @ApiModelProperty(name="Class Motto")
    String classMotto;

    public static TeacherRes of(User user, EduClass eduClass) {
        TeacherRes res = new TeacherRes();

        res.setId(user.getId());
        res.setPosition(user.getPosition());
        res.setLoginId(user.getLoginId());
        res.setPassword(user.getPassword());
        res.setName(user.getName());
        res.setSex(user.getSex());
        res.setPhone(user.getPhone());
        res.setAddress(user.getAddress());
        res.setProfileImgUrl(user.getProfileImgUrl());

        res.setClassMotto(eduClass.getClassMotto());


        return res;
    }
}
