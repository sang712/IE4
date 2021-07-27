package com.ssafy.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.db.entity.QUser;
import com.ssafy.db.entity.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 유저 모델 관련 디비 쿼리 생성을 위한 구현 정의.
 */
@Repository
public class UserRepositorySupport {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    QUser qUser = QUser.user;

    public Optional<User> findUserByUserId(String userId) {
        User user = jpaQueryFactory.select(qUser).from(qUser)
                .where(qUser.userId.eq(userId)).fetchOne();
        if(user == null) return Optional.empty();
        return Optional.ofNullable(user);
    }


//    user 정보 업데이트
//    public int findByUserId(String userId, UserUpdatePatchReq updateInfo) {
//
//        long ret = jpaQueryFactory.update(qUser)
//                .set(qUser.department, updateInfo.getDepartment())
//                .set(qUser.position, updateInfo.getPosition())
//                .set(qUser.name, updateInfo.getName())
//                .where(qUser.userId.eq(userId))
//                .execute();
//
//        System.out.println("Querydsl updateUser >>>>>>>>>>> " + ret);
//
//        return (int) ret;
//    }
//
//    // user 탈퇴
//    public int deleteUser(String userId) {
//        long ret = jpaQueryFactory.delete(qUser).where(qUser.userId.eq(userId)).execute();
//        System.out.println("delete!!! >>>>>>>>>>> " + ret);
//
//        return (int) ret;
//    }

}
