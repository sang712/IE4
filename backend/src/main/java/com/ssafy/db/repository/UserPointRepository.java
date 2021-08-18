package com.ssafy.db.repository;

import com.ssafy.db.entity.UserPoint;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserPointRepository extends JpaRepository<UserPoint, Integer> {
    @Query(value = "SELECT u.name " +
            "FROM user_point up left outer join user u on up.user_id = u.id " +
            "where u.class_id = :classId " +
            "group by up.user_id " +
            "order by sum(up.point) desc limit 3", nativeQuery = true)
    Optional<List<String>> findAllBy(@Param("classId") int classId);

    @Query("SELECT up.id from UserPoint up where up.user.id = :userId")
    Optional<List<Integer>> findIdByUserId(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query("DELETE from UserPoint up where up.id in :ids")
    void deleteAllByIds(@Param("ids") List<Integer> ids);

}
