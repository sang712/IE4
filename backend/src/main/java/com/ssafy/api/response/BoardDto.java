package com.ssafy.api.response;

import com.ssafy.db.entity.Board;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BoardDto")
public class BoardDto {
    @ApiModelProperty(name="게시판 글 type", example="학습자료")
    String boardType;
    @ApiModelProperty(name="게시판 작성자 ID", example="3")
    int userId;
    @ApiModelProperty(name="게시판 작성자 이름", example="이효진")
    String userName;
    @ApiModelProperty(name="게시판 글 제목", example="boardTest")
    String title;
    @ApiModelProperty(name="게시판 글 내용", example="example_content")
    String content;
    @ApiModelProperty(name="게시물 학급 정보", example="603")
    int classId;


    public Board toEntity(){
        Board build = Board.builder()
                .boardType(boardType)
                .userId(userId)
                .userName(userName)
                .classId(classId)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(String boardType, int userId, String userName, int classId, String title, String content){
        this.boardType = boardType;
        this.userId = userId;
        this.userName = userName;
        this.classId = classId;
        this.title = title;
        this.content = content;
    }
//    public static BoardDto of(Board board) {
//        BoardDto dto = new BoardDto;
//        dto.setBoardType(board.getBoardType());
//        dto.setUserId(board.getUserId());
//        dto.setUserName(board.getUserName());
//        dto.setClassId(board.getClassId());
//        dto.setTitle(board.getTitle());
//        dto.setContent(board.getContent());
//        return dto;
//    }
}
