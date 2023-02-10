package com.project.board.dto;

import com.project.board.entity.BoardEntity;
import com.project.board.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BoardDto {
    private Long id;
    private String title;
    private String content;

    public static BoardDto toBoardDto(BoardEntity boardEntity){
        BoardDto boardDto = new BoardDto();
        boardDto.setId(boardEntity.getId());
        boardDto.setTitle((boardEntity.getTitle()));
        boardDto.setContent(boardEntity.getContent());
        return boardDto;
    }
}
