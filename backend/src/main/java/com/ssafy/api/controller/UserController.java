package com.ssafy.api.controller;

import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.api.response.BaseUserResponseBody;
import com.ssafy.api.response.StudentRes;
import com.ssafy.api.response.TeacherRes;
import com.ssafy.api.service.EduClassService;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

	@GetMapping("/{userId}")
	@ApiOperation(value = "아이디 중복체크", notes = "아이디를 중복체크한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "아이디 중복"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> checkDuplicate(
			@PathVariable @ApiParam(value="아이디 정보", required = true) String loginId) {

		User user = userService.getUserByLoginId(loginId);

		if(user == null)
			return ResponseEntity.status(201).body(BaseUserResponseBody.of(user.getId(), "이용가능한 ID 입니다."));
		else
			return ResponseEntity.status(409).body(BaseUserResponseBody.of("이미 존재하는 ID입니다."));
	}

	@GetMapping()
	@ApiOperation(value = "회원 본인 정보 조회", notes = "로그인한 회원 본인의 정보를 응답한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<UserRes> getUserInfo(
			@ApiIgnore Authentication authentication,
		   	@RequestParam @ApiParam(value="회원 아이디", required = true) int id,
			@RequestParam @ApiParam(value="배정 반", required = true) String position) {
		/**
		 * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
		 * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.
		 */
		SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
		String loginId = userDetails.getUsername();

		if(position == "교사"){
			User user = userService.getUserByLoginId(loginId);
			EduClass eduClass = eduClassService.getEduClassByEduClassId(user.getClassId());

			return ResponseEntity.status(200).body(TeacherRes.of(user,eduClass));

		}
		else{ //(position == "학생")
			User user = userService.getUserByLoginId(loginId);
			Student student = userService.getStudentByUserId(user.getId());

			return ResponseEntity.status(200).body(StudentRes.of(user, student));
		}


	}

	@PatchMapping("/{id}")
	@ApiOperation(value = "학생정보 수정", notes = "학생정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> update(
			@PathVariable @ApiParam(value="회원 아이디", required = true) int id,
			@RequestBody @ApiParam(value="학생 정보", required = true) StudentUpdatePatchReq updateInfo) {

		Student student = userService.updateStudent(updateInfo, id);

		if(student != null)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of(student.getUser().getId()));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Student Update Fail"));
	}

	@PatchMapping()
	@ApiOperation(value = "교사정보 수정", notes = "교사정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> update(
			@RequestParam @ApiParam(value="회원 아이디", required = true) int id,
			@RequestParam @ApiParam(value="배정 반", required = true) int classId,
			@RequestBody @ApiParam(value="회원 정보", required = true) TeacherUpdatePatchReq updateInfo) {

		User user = userService.updateTeacher(updateInfo, id, classId);

		if(user != null)
			return ResponseEntity.status(200).body(BaseUserResponseBody.of(user.getId()));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Teacher Update Fail"));
	}

	@DeleteMapping("/{userId}")
	@ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 204, message = "삭제 성공"),
					@ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseUserResponseBody> delete(
			@PathVariable @ApiParam(value="회원 정보", required = true) String loginId) {

		int deleteResult = userService.deleteUser(loginId);

		if(deleteResult == 1)
			return ResponseEntity.status(204).body(BaseUserResponseBody.of("Delete Success"));
		else
			return ResponseEntity.status(400).body(BaseUserResponseBody.of("Delete Fail"));
	}



}
