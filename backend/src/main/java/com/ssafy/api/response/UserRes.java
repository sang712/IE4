package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 본인 정보 조회 API ([GET] /api/users/id) 요청에 대한 응답값 정의.
 */
@Getter
@Setter
@ApiModel("UserResponse")
public abstract class UserRes{
	@ApiModelProperty(name="User ID")
	int id;
	@ApiModelProperty(name="User position")
	String position;
	@ApiModelProperty(name="User LoginID")
	String loginId;
	@ApiModelProperty(name="User Password")
	String password;
	@ApiModelProperty(name="User Name")
	String name;
	@ApiModelProperty(name="User Sex")
	String sex;
	@ApiModelProperty(name="User Phone")
	String phone;
	@ApiModelProperty(name="User Address")
	String address;
	@ApiModelProperty(name="User profileImgUrl")
	String profileImgUrl;
}
