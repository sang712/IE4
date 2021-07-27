package com.ssafy.db.repository;

import com.ssafy.db.entity.EduClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EduClassRepository extends JpaRepository<EduClass, Integer> {
    Optional<EduClass> findEduClassById(int eduClassId);
}
