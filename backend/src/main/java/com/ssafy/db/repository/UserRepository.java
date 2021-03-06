package com.ssafy.db.repository;

import com.ssafy.api.response.EduClassMem;
import com.ssafy.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // 아래와 같이, Query Method 인터페이스(반환값, 메소드명, 인자) 정의를 하면 자동으로 Query Method 구현됨.
    Optional<User> findUserById(int id);
    Optional<User> findUserByLoginId(String loginId);

    boolean existsByLoginId(String loginId);

    //class_id로 teacherName찾기 (그냥 name만 찾아오면 학생꺼랑 여러개 반환되서)
    @Query("SELECT u.name FROM User u WHERE u.classId = :classId AND u.position = '교사'")
    Optional<String> findTeacherNameByClassId(@Param("classId") int classId);


    //class_id로 teacherProfileImgUrl 찾기
    @Query("SELECT u.profileImgUrl FROM User u WHERE u.classId = :classId AND u.position = '교사'")
    Optional<String> findTeacherProfileImgUrlByClassId(@Param("classId")int classId);


    //id로 profileImgUrl 찾기
    @Query("SELECT u.profileImgUrl FROM User u WHERE u.id = :id")
    Optional<String> findById(@Param("id")int id);

    // snum으로 user_id 찾기
    @Query("SELECT s.user.id FROM Student s WHERE s.snum = :snum")
    Optional<List<Integer>> findBySnum(@Param("snum") int snum);

    // snum으로 찾은 user_id와 name, phone을 가지고 LoginId 찾기
    @Query("SELECT u.loginId FROM User u WHERE u.id in :ids AND u.name = :name AND u.phone = :phone")
    Optional<String> findByIdsAndNameAndPhone(@Param("ids")List<Integer> idList, @Param("name")String name, @Param("phone")String phone);

    // passwordQuestion과 passwordAnswer으로 user_id 찾기
    @Query("SELECT s.user.id FROM Student s WHERE s.passwordQuestion = :passwordQuestion AND s.passwordAnswer = :passwordAnswer")
    Optional<List<Integer>> findByPasswordQuestionAndPasswordAnswer(@Param("passwordQuestion")String passwordQuestion, @Param("passwordAnswer")String passwordAnswer);

    // passwordQuestion과 passwordAnswer로 찾은 user_id로 user의 password찾기
    @Query("SELECT u.password FROM User u WHERE u.id in :ids AND u.loginId = :loginId")
    Optional<String> findByIdsAndLoginId(@Param("ids")List<Integer> idList, @Param("loginId")String loginId);

//    @Query("SELECT u.name, u.profileImgUrl FROM User u WHERE u.classId = :classId AND u.position = :position ORDER BY u.name")    --> 이건 왜 안되는 건지?
    @Query(value = "SELECT u.name, u.profile_img_url FROM user u WHERE u.class_id = :classId AND u.position = :position ORDER BY u.name", nativeQuery = true)
    Optional<List<EduClassMem>> findByClassIdAndPositionOrderByName(@Param("classId")int classId, @Param("position")String position);



}