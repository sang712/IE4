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
    Optional<User> findByLoginId(String loginId);

    //id로 profileImgUrl 찾기
    @Query("SELECT u.profileImgUrl FROM User u WHERE u.id = :id")
    Optional<String> findById(@Param("id")int id);

    // snum으로 user_id 찾기
    @Query("SELECT s.user.id FROM Student s WHERE s.snum = :snum")
    Optional<List<Integer>> findBySnum(@Param("snum") int snum);

    @Query("SELECT u.loginId FROM User u WHERE u.id in :ids AND u.name = :name AND u.phone = :phone")
    Optional<String> findByIdsAndNameAndPhone(@Param("ids")List<Integer> idList, @Param("name")String name, @Param("phone")String phone);

//    @Query(value = "SELECT u.login_id FROM User u WHERE u.id = :id IN (SELECT user_id FROM student WHERE snum=:snum) AND name = :name AND phone = :phone", nativeQuery = true)
//    Optional<String> findByIdAndSnumAndPhone(@Param("name")String name, @Param("snum")int snum, @Param("phone")String phone);

//    @Query("SELECT u.name, u.profileImgUrl FROM User u WHERE u.classId = :classId AND u.position = :position ORDER BY u.name")    --> 이건 왜 안되는 건지?
    @Query(value = "SELECT u.name, u.profile_img_url FROM User u WHERE u.class_id = :classId AND u.position = :position ORDER BY u.name", nativeQuery = true)
    Optional<List<EduClassMem>> findByClassIdAndPositionOrderByName(@Param("classId")int classId, @Param("position")String position);



}