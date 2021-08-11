package com.ssafy.api.service;

import com.ssafy.api.request.BoardUpdatePatchReq;
import com.ssafy.db.entity.Board;
import com.ssafy.db.entity.BoardFile;
import com.ssafy.db.repository.BoardFileRepository;
import com.ssafy.db.repository.BoardRepository;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 *	게시판 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */

@Service("boardService")
public class BoardServiceImpl implements BoardService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    BoardFileRepository boardFileRepository;

    String uploadFolder = "boardfiles";
    String uploadPath = "C:" + File.separator + "Users" + File.separator + "multicampus"
            + File.separator + "Documents"
            + File.separator + "S05P13A601"
            + File.separator + "backend"
            + File.separator + "src"
            + File.separator + "main"
            + File.separator + "resources"
            + File.separator + "static";

    @Override
    public BoardFile insertBoard(MultipartFile files, Board board) throws IOException {
        System.out.println("------------------게시판 글 등록 시도-----------------" );
        Board newboard = boardRepository.save(board);

        String baseDir = "C:\\Users\\multicampus\\Documents\\boardfiles";
        File uploadDir = new File(uploadPath + File.separator + uploadFolder);
        if (!uploadDir.exists()) uploadDir.mkdir();

        int boardId = newboard.getId();
        String fileName = files.getOriginalFilename();

        //Random File Id
        UUID uuid = UUID.randomUUID();
        //file extension
        String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
        String savingFileName = uuid + "." + extension;
        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

        System.out.println("파일 경로" + destFile);
        files.transferTo(destFile);

        // Table Insert
        String boardFileUrl = uploadFolder + "/" + savingFileName;
        BoardFile boardFile = new BoardFile(boardId,files.getOriginalFilename(),files.getSize(),boardFileUrl );
        System.out.println("------------------------boardFileEntity: "+boardFile.toString() +"--------------");

        return boardFileRepository.save(boardFile.toEntity());
    }

    @Override
    public Board insertBoard(Board board) throws IOException {
        return boardRepository.save(board);
    }

    @Override
    public Board updateBoard(Board board) throws IOException {
        System.out.println("업데이트 할 board >>>>>> " + board.toEntity());
        int boardId = board.getId();
        System.out.println("업데이트 할 board Id >>>>>> " + boardId);
        Board updateboard = boardRepository.findById(boardId).get();
        updateboard.setTitle(board.getTitle());
        updateboard.setContent(board.getContent());
       return boardRepository.save(updateboard);
    }

    @Override
    public BoardFile updateBoard(MultipartFile files, Board board) throws IOException {
        int boardId = board.getId();
        Board updateboard = boardRepository.findById(boardId).get();
        updateboard.setTitle(board.getTitle());
        updateboard.setContent(board.getContent());
        boardRepository.save(updateboard);

        Optional<BoardFile> boardfile = boardFileRepository.findById(boardId);
        if (boardfile.isPresent()){
            System.out.println("boardfile >>>> 삭제시도!!!!!!!!! ");
            boardFileRepository.delete(boardfile.get());
        }
        String baseDir = "C:\\Users\\multicampus\\Documents\\boardfiles";
        File uploadDir = new File(uploadPath + File.separator + uploadFolder);

        String fileName = files.getOriginalFilename();
        UUID uuid = UUID.randomUUID();
        //file extension
        String extension = FilenameUtils.getExtension(fileName); // vs FilenameUtils.getBaseName()
        String savingFileName = uuid + "." + extension;
        File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);

        System.out.println("파일 경로" + destFile);
        files.transferTo(destFile);

        // Table Insert
        String boardFileUrl = uploadFolder + "/" + savingFileName;
        BoardFile boardFile = new BoardFile(boardId,fileName,files.getSize(),boardFileUrl );
        System.out.println("------------------------boardFileEntity: "+boardFile.toString() +"--------------");

        return boardFileRepository.save(boardFile.toEntity());
    }

    @Override
    public int deleteBoard(int boardId) {
        Optional<Board> board = boardRepository.findById(boardId);
        boardRepository.delete(board.get());
        return 1;
    }

    @Override
    public int deleteBoardFile(int boardId) {
        System.out.println("boardfile >>>> 삭제시도!!!!!!!!! ");
        Optional<BoardFile> boardfile = boardFileRepository.findById(boardId);
        System.out.println("boardfile >>>> " + boardfile);
        try {
            boardFileRepository.delete(boardfile.get());
            return 1;
        } catch (Exception e) {
            return 2;
        }
    }

    @Override
    public Board detailBoard(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        return board;
    }

    @Override
    public BoardFile detailBoardFile(int boardId) {
        try {
            BoardFile file = boardFileRepository.findByBoardId(boardId).get();
            return file;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Page<Board> boardPage(int classId, String boardType, Pageable pageRequest) {
        Page<Board> boardList = boardRepository.findByClassIdAndBoardType(classId, boardType, pageRequest);
        return boardList;
    }

    @Override
    public Page<Board> searchBoardPage(int classId, String boardType, Pageable pageRequest, String keykord) {
        Page<Board> boardList = boardRepository.findByClassIdAndBoardTypeAndTitleContaining(classId, boardType, keykord, pageRequest);
        return boardList;
    }
}
