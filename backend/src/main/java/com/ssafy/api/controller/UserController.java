package com.ssafy.api.controller;

import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.api.request.UserDeleteReq;
import com.ssafy.api.response.BaseUserResponseBody;
import com.ssafy.api.response.StudentRes;
import com.ssafy.api.response.TeacherRes;
import com.ssafy.api.service.EduClassService;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.response.UserRes;
import com.ssafy.api.service.UserService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 유저 관련 API 요청 처리를 위한 컨트롤러 정의.
 */
@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;

	@Autowired
	EduClassService eduClassService;


	@GetMapping()
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<UserRes> getUserInfo(@ApiIgnore Authentication authentication) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String loginId = userDetails.getUsername();
		User user = userService.getUserByLoginId(loginId);

		if(user.getPosition().equals("교사")){
			EduClass eduClass = eduClassService.getEduClassByEduClassId(user.getClassId());

			return ResponseEntity.status(200).body(TeacherRes.of(user,eduClass));

		}
		else{ //(position == "학생")
			System.out.println("user.getId() : " + user.getId());
			Student student = userService.getStudentByUserId(user.getId());

			return ResponseEntity.status(200).body(StudentRes.of(user, student));
		}
	}

	// 1. 로그인 할 때 기본 이미지 url 넣어주기 => 프로필 수정했다가 다시 아무것도 설정안하면? DB에 아무것도 저장이 안될텐데
	// => 기본이미지 설정하기 버튼 필요?
	// 2. 프론트에서 null일 때 기본이미지 넣어주기

	@PostMapping("/student")
	@ApiOperation(value = "학생정보 수정", notes = "학생정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> update(
			@ApiParam(value="회원 정보", required = true) StudentUpdatePatchReq updateInfo, MultipartHttpServletRequest request) {

		Student student = userService.updateStudent(updateInfo, request);

		if(student != null)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of(student.getUser().getId()));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Student Update Fail"));
	}

	@PostMapping("/teacher")
	@ApiOperation(value = "교사정보 수정", notes = "교사정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> update(
			@ApiParam(value="회원 정보", required = true) TeacherUpdatePatchReq updateInfo, MultipartHttpServletRequest request) {

		User user = userService.updateTeacher(updateInfo, request);

		if(user != null)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of(user.getId()));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Teacher Update Fail"));
	}

	@PostMapping()
	@ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 204, message = "삭제 성공"),
					@ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> delete(
			@RequestBody @ApiParam(value="학생 정보", required = true) UserDeleteReq deleteInfo) {

		User user = userService.getUserById(deleteInfo.getId());

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(deleteInfo.getPassword(), user.getPassword())){
			userService.deleteUser(user);

			return ResponseEntity.status(204).body(BaseUserResponseBody.of("Delete Success"));
		}else{
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Delete Fail"));
		}
	}



}
