package com.ssafy.api.controller;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.api.response.BaseUserResponseBody;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "회원가입 API", tags = {"Register"})
@RestController
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
        			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) StudentRegisterPostReq registerInfo) {
		
		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Student student = userService.createStudent(registerInfo);

		if(student != null)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of(student.getId()));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Register Fail"));
	}



}
