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
	User updateTeacher(TeacherUpdatePatchReq teacherUpdateInfo, int id, int classId);
	int deleteUser(String loginId);
	User getUserByLoginId(String loginId);
	Student getStudentByUserId(int UserId);
}
