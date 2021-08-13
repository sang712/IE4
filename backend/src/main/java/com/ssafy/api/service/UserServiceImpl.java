package com.ssafy.api.service;

import com.ssafy.api.request.StudentUpdatePatchReq;
import com.ssafy.api.request.TeacherUpdatePatchReq;
import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.EduClass;
import com.ssafy.db.entity.Student;
import com.ssafy.db.entity.UserPoint;
import com.ssafy.db.repository.*;
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
	UserPointRepository userPointRepository;

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
	public Student updateStudent(StudentUpdatePatchReq studentUpdateInfo, MultipartHttpServletRequest request) {
		Student student = studentRepository.findByUserId(studentUpdateInfo.getId()).get();
		User user = userRepository.findUserById(studentUpdateInfo.getId()).get();

		System.out.println(studentUpdateInfo.getPassword() + "type" + studentUpdateInfo.getPassword().getClass().getName());

		if(!studentUpdateInfo.getPassword().equals("null")) {
			System.out.println("if문 안으로 들어옴");
			user.setPassword(passwordEncoder.encode(studentUpdateInfo.getPassword()));
		}
		if(studentUpdateInfo.getPhone() != null) user.setPhone(studentUpdateInfo.getPhone());
		if(studentUpdateInfo.getAddress() != null) user.setAddress(studentUpdateInfo.getAddress());

		if(request.getFile("profileImgUrl") != null) {
			String uploadFolder = "profileImg";
			String uploadPath = "/home/ubuntu/ie4_upload";

//		String uploadPath = request.getServletContext().getRealPath("/");       // uploadPath가 실행될때마다 계속 새로 생성되는 곳으로 바뀌기 때문에 서버에 올리기 전에 path 수정필요!
			String saveUrl = "";

			try {
				MultipartFile file = request.getFile("profileImgUrl");

				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if(!uploadDir.exists()) uploadDir.mkdir();

				String fileUrl = userRepository.findById(studentUpdateInfo.getId()).orElse(null);

				// fileUrl이 null이 아니라면 (이미 시간표 이미지가 업로드 되어 있다면) 기존의 이미지 삭제 후 업로드
				// fileUrl이 null이라면 그냥 바로 업로드 ㄱㄱ
				if(fileUrl != null) {
					File deleteFile = new File(uploadPath + File.separator, fileUrl);       // fileUrl <- 지울 파일의 url 가져오기 respository!!
					if(deleteFile.exists()) deleteFile.delete();
				}
				String fileName = file.getOriginalFilename();

				// Random File Id
				UUID uuid = UUID.randomUUID();

				// file extension
				String extension = FilenameUtils.getExtension(fileName);
				String savingFileName = uuid + "." + extension;
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

				System.out.println("파일 경로!!!>>> " + destFile);

				file.transferTo(destFile);

				saveUrl = "ie4_upload/" + uploadFolder + "/" + savingFileName;

			} catch(IOException e) {
				e.printStackTrace();
			}

			user.setProfileImgUrl(saveUrl);
		}

		User saveUser = userRepository.save(user);

		if(saveUser != null) {
			student.setParentPhone(studentUpdateInfo.getParentPhone());
			student.setPasswordAnswer(studentUpdateInfo.getPasswordAnswer());
			return studentRepository.save(student);
		}
		else return null;
	}

	@Override
	public User updateTeacher(TeacherUpdatePatchReq teacherUpdateInfo, MultipartHttpServletRequest request) {
		User user = userRepository.findUserById(teacherUpdateInfo.getId()).get();

		if(teacherUpdateInfo.getPassword().equals("null")) user.setPassword(passwordEncoder.encode(teacherUpdateInfo.getPassword()));
		user.setPhone(teacherUpdateInfo.getPhone());
		user.setAddress(teacherUpdateInfo.getAddress());

		if(request.getFile("profileImgUrl") != null) {
			String uploadFolder = "profileImg";
			String uploadPath = "/home/ubuntu/ie4_upload";

//		String uploadPath = request.getServletContext().getRealPath("/");       // uploadPath가 실행될때마다 계속 새로 생성되는 곳으로 바뀌기 때문에 서버에 올리기 전에 path 수정필요!
			String saveUrl = "";

			try {
				MultipartFile file = request.getFile("profileImgUrl");

				File uploadDir = new File(uploadPath + File.separator + uploadFolder);
				if(!uploadDir.exists()) uploadDir.mkdir();

				String fileUrl = userRepository.findById(teacherUpdateInfo.getId()).orElse(null);

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

				saveUrl = "ie4_upload/" + uploadFolder + "/" + savingFileName;

			} catch(IOException e) {
				e.printStackTrace();
			}

			user.setProfileImgUrl(saveUrl);
		}

		if(userRepository.save(user) != null) {
			EduClass eduClass = eduClassRepository.findEduClassById(teacherUpdateInfo.getClassId()).get();
			eduClass.setClassMotto(teacherUpdateInfo.getClassMotto());

			if(eduClassRepository.save(eduClass) == null) return null;
		}


		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		List<Integer> userPointIdList = userPointRepository.findIdByUserId(user.getId()).orElse(null);
		if(userPointIdList != null)userPointRepository.deleteAllByIds(userPointIdList);

		Student student = studentRepository.findByUserId(user.getId()).orElse(null);
		if(student != null) studentRepository.delete(student);

		userRepository.delete(user);
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
	public User getUserById(int id){
		User user = userRepository.findUserById(id).orElse(null);

		return user;
	}

	@Override
	public Student getStudentByUserId(int userId) {
		// 디비에 유저 정보 조회 (userId 를 통한 조회).
		Student student = studentRepository.findByUserId(userId).orElse(null);

		return student;
	}

	@Override
	public String getTeacherNameByClassId(int classId){
		String name = userRepository.findTeacherNameByClassId(classId).orElse(null);

		return name;
	}

	@Override
	public String getTeacherProfileImgUrl(int classId){
		String profileImgUrl = userRepository.findTeacherProfileImgUrlByClassId(classId).orElse(null);

		return profileImgUrl;
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
