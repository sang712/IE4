package com.ssafy.api.response;

import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

/**
 * 우리 반 보기 API ([GET] /api/class/myclass/{classId}) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("EduClassMemListResponse")
public class EduClassMemListRes {

    @ApiModelProperty(name="User Name")
    String name;
    @ApiModelProperty(name="User ProfileImgUrl")
    String profileImgUrl;

    public static List<EduClassMemListRes> of(List<EduClassMem> list) {
        List<EduClassMemListRes> res = new LinkedList<>();

        for(int i=0; i<list.size(); i++) {
            res.add(EduClassMemListRes.of(list.get(i)));
        }

        return res;
    }

    public static EduClassMemListRes of(EduClassMem eduClassMem) {
        EduClassMemListRes res = new EduClassMemListRes();
        res.setName(eduClassMem.getName());
        res.setProfileImgUrl(eduClassMem.getProfile_img_url());

        return res;
    }

}
