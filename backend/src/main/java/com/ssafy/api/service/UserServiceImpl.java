package com.ssafy.api.service;

import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import com.ssafy.db.repository.EduClassRepository;
import com.ssafy.db.repository.StudentRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ssafy.api.request.StudentRegisterPostReq;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.UserRepository;
import com.ssafy.db.repository.UserRepositorySupport;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	public Student updateStudent(StudentUpdatePatchReq studentUpdateInfo, int id, MultipartHttpServletRequest request) {
		Student student = studentRepository.findByUserId(id).get();
		User user = userRepository.findUserById(id).get();

		user.setPassword(passwordEncoder.encode(studentUpdateInfo.getPassword()));
		user.setPhone(studentUpdateInfo.getPhone());
		user.setAddress(studentUpdateInfo.getAddress());


		String uploadFolder = "profile";
		String uploadPath = request.getServletContext().getRealPath("/");       // uploadPath가 실행될때마다 계속 새로 생성되는 곳으로 바뀌기 때문에 서버에 올리기 전에 path 수정필요!
		String saveUrl = "";

		try {
			MultipartFile file = request.getFile("file");

			File uploadDir = new File(uploadPath + File.separator + uploadFolder);
			if(!uploadDir.exists()) uploadDir.mkdir();

			String fileUrl = userRepository.findById(id).orElse(null);

			// fileUrl이 null이 아니라면 (이미 시간표 이미지가 업로드 되어 있다면) 기존의 이미지 삭제 후 업로드
			// fileUrl이 null이라면 그냥 바로 업로드 ㄱㄱ
			if(fileUrl != null) {
				File deleteFile = new File(uploadPath + File.separator, fileUrl);       // fileUrl <- 지울 파일의 url 가져오기 respository!!
				if(deleteFile.exists()) deleteFile.delete();
			}

			//
			String fileName = file.getOriginalFilename();

			// Random File Id
			UUID uuid = UUID.randomUUID();

			// file extension
			String extension = FilenameUtils.getExtension(fileName);
			String savingFileName = uuid + "." + extension;
			File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

			System.out.println("파일 경로!!!>>> " + destFile);

			file.transferTo(destFile);

			saveUrl = uploadFolder + "/" + savingFileName;

		} catch(IOException e) {
			e.printStackTrace();

		}

		user.setProfileImgUrl(saveUrl);


		if(userRepository.save(user) != null) {
			student.setParentPhone(studentUpdateInfo.getParentPhone());
			student.setPasswordAnswer(studentUpdateInfo.getPasswordAnswer());
			return studentRepository.save(student);
		}
		else return null;
	}

	@Override
	public User updateTeacher(TeacherUpdatePatchReq teacherUpdateInfo, int id, int classId) {
		User user = userRepository.findUserById(id).get();

		user.setPassword(passwordEncoder.encode(teacherUpdateInfo.getPassword()));
		user.setPhone(teacherUpdateInfo.getPhone());
		user.setAddress(teacherUpdateInfo.getAddress());
		user.setProfileImgUrl(teacherUpdateInfo.getProfileImgUrl());

		EduClass eduClass = eduClassRepository.findEduClassById(classId).get();
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
	public boolean checkIdDuplicate( String loginId) {
		return userRepository.existsByLoginId(loginId);
	}

	@Override
	public User getUserByLoginId(String loginId) {
		// 디비에 유저 정보 조회 (loginId 를 통한 조회).
		User user = userRepository.findUserByLoginId(loginId).orElse(null);

		return user;
	}

	@Override
	public Student getStudentByUserId(int userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Student student = studentRepository.findByUserId(userId).orElse(null);

		return student;
	}

	@Override
	public String findLoginId(String name, int snum, String phone){
		List<Integer> userIdList = new LinkedList<>();

		userIdList.addAll(userRepository.findBySnum(snum).orElse(null));

		String loginId = userRepository.findByIdsAndNameAndPhone(userIdList, name, phone).orElse(null);

		return loginId;
	}

	@Override
	public String findPassword(String loginId, String passwordQuestion, String passwordAnswer){
		List<Integer> userIdList = new LinkedList<>();

		userIdList.addAll(userRepository.findByPasswordQuestionAndPasswordAnswer(passwordQuestion, passwordAnswer).orElse(null));

		String password = userRepository.findByIdsAndLoginId(userIdList, loginId).orElse(null);

		return password;
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
