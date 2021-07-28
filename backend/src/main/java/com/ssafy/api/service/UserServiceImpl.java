package com.ssafy.api.service;

import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import com.ssafy.db.repository.EduClassRepository;
import com.ssafy.db.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;

/**
 *	유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	EduClassRepository eduClassRepository;
	
	@Autowired
	UserRepositorySupport userRepositorySupport;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Student createStudent(StudentRegisterPostReq studentRegisterInfo) {
		Student student = new Student();
		User user = new User();

		user.setLoginId(studentRegisterInfo.getLoginId());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPosition("학생");
		user.setPassword(passwordEncoder.encode(studentRegisterInfo.getPassword()));
		user.setName(studentRegisterInfo.getName());
		user.setPhone(studentRegisterInfo.getPhone());
		user.setClassId(studentRegisterInfo.getClassId());
		user.setAddress(studentRegisterInfo.getAddress());
		user.setProfileImgUrl(studentRegisterInfo.getProfileImgUrl());
		user.setSex(studentRegisterInfo.getSex());

		if(userRepository.save(user) != null){
			student.setUser(user);
			student.setSnum(studentRegisterInfo.getSnum());
			student.setParentPhone(studentRegisterInfo.getParentPhone());
			student.setPasswordQuestion(studentRegisterInfo.getPasswordQuestion());
			student.setPasswordAnswer(studentRegisterInfo.getPasswordAnswer());
			return studentRepository.save(student);
		}
		else return null;
	}

	@Override
	public Student updateStudent(StudentUpdatePatchReq studentUpdateInfo, int id) {
		Student student = studentRepository.findByUserId(id).get();
		User user = userRepository.findById(id).get();

		user.setPassword(passwordEncoder.encode(studentUpdateInfo.getPassword()));
		user.setPhone(studentUpdateInfo.getPhone());
		user.setAddress(studentUpdateInfo.getAddress());
		user.setProfileImgUrl(studentUpdateInfo.getProfileImgUrl());

		if(userRepository.save(user) != null) {
			student.setParentPhone(studentUpdateInfo.getParentPhone());
			student.setPasswordAnswer(studentUpdateInfo.getPasswordAnswer());
			return studentRepository.save(student);
		}
		else return null;
	}

	@Override
		public User updateTeacher(TeacherUpdatePatchReq teacherUpdateInfo, int id, int classId) {
			User user = new User();

			user.setPassword(passwordEncoder.encode(teacherUpdateInfo.getPassword()));
			user.setPhone(teacherUpdateInfo.getPhone());
			user.setAddress(teacherUpdateInfo.getAddress());
			user.setProfileImgUrl(teacherUpdateInfo.getProfileImgUrl());

			EduClass eduClass = new EduClass();
			eduClass.setClassMotto(teacherUpdateInfo.getClassMotto());

			if(eduClassRepository.save(eduClass) == null) return null;

			return userRepository.save(user);
	}

	@Override
	public int deleteUser(String loginId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Optional<User> user = userRepositorySupport.findUserByLoginId(loginId);

		int result = 0; // 0 : fail, 1 : success

		if(user.isPresent()){
			result = 1;
			userRepository.delete(user.get());
		}

		return result;

	}

	@Override
	public User getUserByLoginId(String loginId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		User user = userRepositorySupport.findUserByLoginId(loginId).orElse(null);

		return user;
	}


//	@Override
//	public int checkUserId(String userId) {
//		// 디비에 유저 ID 조회
//		User user = userRepositorySupport.findUserByUserId(userId).get();
//
//		if(user != null) return 1;
//		else return 0;
//	}
}
