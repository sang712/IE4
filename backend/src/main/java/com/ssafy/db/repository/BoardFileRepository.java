package com.ssafy.db.repository;


import com.ssafy.db.entity.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Integer>  {
    //BoardFile save(BoardFile boardFile);
    //Optional<BoardFile> findBoardFileByBoardId(int boardId);
    //List<BoardFile> fileAllByBoardId(int boardId);
}
