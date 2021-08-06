package com.ssafy.api.controller;

import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.api.response.*;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepositorySupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * 인증 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/login")
public class AuthController {
	@Autowired
	UserService userService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@PostMapping()
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.") 
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
        @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
        @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
	public ResponseEntity<UserLoginPostRes> login(
			@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
		String loginId = loginInfo.getLoginId();
		String password = loginInfo.getPassword();

		User user = userService.getUserByLoginId(loginId);
		// 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
		if(passwordEncoder.matches(password, user.getPassword())) {
			if(user.getPosition().equals("학생")){
				Student student = userService.getStudentByUserId(user.getId());
				String teacherName = userService.getTeacherNameByClassId(user.getClassId());
				System.out.println(teacherName);
				return ResponseEntity.ok(StudentLoginPostRes.of(
						user.getId(), user.getClassId(), user.getPosition(), user.getName(), student.getSnum(), user.getProfileImgUrl(), user.getSex(), teacherName, JwtTokenUtil.getToken(loginId)));
			}else{ // 선생님
				// 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
				return ResponseEntity.ok(UserLoginPostRes.of(
						user.getId(), user.getClassId(), user.getPosition(), user.getName(), user.getProfileImgUrl(), user.getSex(), JwtTokenUtil.getToken(loginId)));
			}
		}
		// 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(0,0, null, null, null, null, null));
	}

	@GetMapping("/findId")
	@ApiOperation(value = "아이디 찾기", notes = "<strong>이름과 배정번호와 전화번호</strong>를 통해 로그인 아이디를 찾는다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Map<String, String>> findMyLoginId(
			@RequestParam @ApiParam(value="학생 이름", required = true) String name,
			@RequestParam @ApiParam(value="학생 배정번호", required = true) int snum,
			@RequestParam @ApiParam(value="학생 전화번호", required = true) String phone) {
		String loginId = userService.findLoginId(name, snum, phone);
		Map<String, String> map = new HashMap<String, String>() {
			{
				put("loginId", loginId);
			}
		};

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}


	@GetMapping("/findPw")
	@ApiOperation(value = "비밀번호 찾기", notes = "<strong>이름과 배정번호와 전화번호</strong>를 통해 로그인 비밀번호를 찾는다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<Map<String, String>> findMyLoginId(
			@RequestParam @ApiParam(value="회원 로그인 아이디", required = true) String loginId,
			@RequestParam @ApiParam(value="회원 비밀번호 질문", required = true) String passwordQuestion,
			@RequestParam @ApiParam(value="회원 비밀번호 질문 답", required = true) String passwordAnswer) {
		String password = userService.findPassword(loginId, passwordQuestion, passwordAnswer);
		Map<String, String> map = new HashMap<String, String>() {
			{
				put("password", password);
			}
		};

		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
}
