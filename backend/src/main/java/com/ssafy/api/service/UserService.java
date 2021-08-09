package com.ssafy.api.service;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.User;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의.
 */
public interface UserService {
	Student createStudent(StudentRegisterPostReq studentRegisterInfo);
	Student updateStudent(StudentUpdatePatchReq studentUpdateInfo, int id, MultipartHttpServletRequest request);
	User updateTeacher(TeacherUpdatePatchReq teacherUpdateInfo, int id, int classId, MultipartHttpServletRequest request);
	void deleteUser(User user);

	boolean checkIdDuplicate(String loginId);

	User getUserByLoginId(String loginId);
	Student getStudentByUserId(int UserId);
	String getTeacherNameByClassId(int classId);
	String getTeacherProfileImgUrl(int classId);

	String findLoginId(String name, int snum, String phone);
	String findPassword(String loginId, String passwordQuestion, String passwordAnswer);
}
