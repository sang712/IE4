package com.ssafy.db.repository;

import com.ssafy.db.entity.EduClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EduClassRepository extends JpaRepository<EduClass, Integer> {
    Optional<EduClass> findEduClassById(int eduClassId);

    @Query("SELECT e.conferenceUrl FROM EduClass e WHERE e.id = :id")
    Optional<String> findByEduClassId(@Param("id")int eduClassId);

    Optional<EduClass> findById(int eduClassId);
}
