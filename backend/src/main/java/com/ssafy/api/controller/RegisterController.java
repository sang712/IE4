package com.ssafy.api.controller;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.api.response.BaseUserResponseBody;
import com.ssafy.api.service.UserService;
import com.ssafy.db.entity.Student;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "회원가입 API", tags = {"Register"})
@RestController
@RequestMapping("/api/sign-up")
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@PostMapping()
	@ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.") 
    @ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
        			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> register(
			@RequestBody @ApiParam(value="회원가입 정보", required = true) StudentRegisterPostReq registerInfo) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");

		//임의로 리턴된 User 인스턴스. 현재 코드는 회원 가입 성공 여부만 판단하기 때문에 굳이 Insert 된 유저 정보를 응답하지 않음.
		Student student = userService.createStudent(registerInfo);

		if(student != null)
			return new ResponseEntity<>(headers, HttpStatus.OK);
		else
			return new ResponseEntity<>(headers, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/{loginId}")
	@ApiOperation(value = "아이디 중복체크", notes = "아이디를 중복체크한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "아이디 중복"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> checkDuplicate(
			@PathVariable @ApiParam(value="아이디 정보", required = true) String loginId) {

		boolean result = userService.checkIdDuplicate(loginId);

		if(!result)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of("이용가능한 ID 입니다."));
		else
			return ResponseEntity.status(409).body(BaseUserResponseBody.of("이미 존재하는 ID입니다."));
	}

}
