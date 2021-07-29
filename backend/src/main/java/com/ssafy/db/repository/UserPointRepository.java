package com.ssafy.db.repository;

import com.ssafy.db.entity.UserPoint;
import org.kurento.client.internal.server.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserPointRepository extends JpaRepository<UserPoint, Integer> {
    @Query(value = "SELECT u.name " +
            "FROM User_point up left outer join User u on up.user_id = u.id " +
            "where u.class_id = :classId " +
            "group by up.user_id " +
            "order by sum(up.point) desc limit 3", nativeQuery = true)
    Optional<List<String>> findAllBy(@Param("classId") int classId);
}
