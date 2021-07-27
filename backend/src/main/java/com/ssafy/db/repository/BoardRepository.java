package com.ssafy.db.repository;

import com.ssafy.db.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    Optional<Board> findBoardById(int boardId);

    @Override
    Page<Board> findAll(Pageable pageable);
    // 검색 기능
//    Page<Board> findByTitleContaining(String title, Pageable pageable);
//    Page<Board> findByUserNameContaining(String username, Pageable pageable);
}
