package com.ssafy.api.response;

import com.ssafy.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 요청에대한 기본 응답값(바디) 정의.
 */
@Getter
@Setter
@ApiModel("BaseUserResponseBody")
public class BaseUserResponseBody {
	@ApiModelProperty(name="응답 아이디", example = "200")
	Integer id;
	@ApiModelProperty(name="응답 메시지", example = "Fail")
	String message;

	public BaseUserResponseBody() {}

	public BaseUserResponseBody(String message){
		this.message = message;
	}

	public BaseUserResponseBody(Integer id){
		this.id = id;
	}

	public BaseUserResponseBody(Integer id, String message){
		this.id = id;
		this.message = message;
	}
	public static BaseUserResponseBody of(String message) {
		BaseUserResponseBody body = new BaseUserResponseBody();
		body.message = message;
		return body;
	}

	public static BaseUserResponseBody of(Integer id) {
		BaseUserResponseBody body = new BaseUserResponseBody();
		body.id = id;
		return body;
	}

	public static BaseUserResponseBody of(Integer id, String message) {
		BaseUserResponseBody body = new BaseUserResponseBody();
		body.id = id;
		body.message = message;
		return body;
	}
}
