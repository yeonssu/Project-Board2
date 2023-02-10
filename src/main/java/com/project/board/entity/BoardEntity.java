package com.project.board.entity;

import com.project.board.dto.BoardDto;
import com.project.board.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="board_table")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    public static BoardEntity toBoardEntity(BoardDto boardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(boardDto.getTitle());
        boardEntity.setContent(boardDto.getContent());
        return boardEntity;
    }
    public void toUpdateBoardEntity(BoardDto boardDto){
        this.title = boardDto.getTitle();
        this.content = boardDto.getContent();
    }
}
