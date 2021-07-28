package com.ssafy.api.controller;

import com.ssafy.api.request.UserUpdatePatchReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ssafy.api.request.StudentRegisterPostReq;
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

	@GetMapping("/{userId}")
	@ApiOperation(value = "아이디 중복체크", notes = "아이디를 중복체크한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
			@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 409, message = "아이디 중복"),
			@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> checkDuplicate(
			@PathVariable @ApiParam(value="아이디 정보", required = true) String loginId) {

		User user = userService.getUserByLoginId(loginId);

		if(user == null){
			return ResponseEntity.status(201).body(BaseResponseBody.of(201, "이용 가능한 ID 입니다."));
		}else{
			return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 사용자 ID 입니다."));
		}
	}

	@GetMapping("/me")
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

		return ResponseEntity.status(200).body(UserRes.of(user));
	}

	@PatchMapping("/{loginId}")
	@ApiOperation(value = "회원정보 수정", notes = "회원정보를 수정한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 401, message = "인증 실패"),
					@ApiResponse(code = 404, message = "사용자 없음"), @ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> update(
			@PathVariable @ApiParam(value="회원 정보", required = true) String loginId, @RequestBody @ApiParam(value="회원 아이디", required = true) UserUpdatePatchReq updateInfo) {

		User user = userService.updateUser(updateInfo, loginId);

		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	@DeleteMapping("/{userId}")
	@ApiOperation(value = "회원정보 삭제", notes = "회원정보를 삭제한다..")
	@ApiResponses({ @ApiResponse(code = 200, message = "성공"), @ApiResponse(code = 204, message = "삭제 성공"),
					@ApiResponse(code = 401, message = "인증 실패"), @ApiResponse(code = 404, message = "사용자 없음"),
					@ApiResponse(code = 500, message = "서버 오류") })
	public ResponseEntity<? extends BaseResponseBody> delete(
			@PathVariable @ApiParam(value="회원 정보", required = true) String loginId) {

		int deleteResult = userService.deleteUser(loginId);

		if(deleteResult == 1)
			return ResponseEntity.status(204).body(BaseResponseBody.of(204, "삭제 성공"));
		else
			return ResponseEntity.status(404).body(BaseResponseBody.of(404, "사용자가 존재하지 않습니다."));
	}



}
