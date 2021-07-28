package com.ssafy.api.response;

import com.ssafy.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("BaseUserResponse")
public class BaseUserRes {
	@ApiModelProperty(name="User ID")
	String userId;
	@ApiModelProperty(name="User Password")
	String password;
	@ApiModelProperty(name="User Name")
	String name;
	@ApiModelProperty(name="User Department")
	String department;
	@ApiModelProperty(name="User Position")
	String position;

	public static BaseUserRes of(User user) {
		BaseUserRes res = new BaseUserRes();
		res.setUserId(user.getLoginId());
		res.setPassword(user.getPassword());
		res.setName(user.getName());
		res.setDepartment(user.getDepartment());
		res.setPosition(user.getPosition());
		return res;
	}
}
