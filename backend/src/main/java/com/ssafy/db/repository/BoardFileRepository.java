package com.ssafy.db.repository;


import com.ssafy.db.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Integer>  {
    Optional<BoardFile> findByBoardId(int boardId);
}
