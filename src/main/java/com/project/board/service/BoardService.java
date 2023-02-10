package com.project.board.service;

import com.project.board.dto.BoardDto;
import com.project.board.entity.BoardEntity;
import com.project.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    //게시글 작성 처리
    public void boardWrite(BoardDto boardDto) {
        BoardEntity boardEntity = BoardEntity.toBoardEntity(boardDto);
        boardRepository.save(boardEntity);
    }

    //게시글 리스트 처리
    public List<BoardDto> boardList() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDtoList.add(BoardDto.toBoardDto(boardEntity));
        }
        return boardDtoList;
    }

    //특정 게시글 불러오기
    public BoardDto boardView(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            return BoardDto.toBoardDto(optionalBoardEntity.get());
        }else{
            return null;
        }
    }

    //게시글 수정
    @Transactional
    public void boardUpdate(BoardDto boardDto, Long id) {
        BoardEntity board = boardRepository.findById(id).get();
        board.toUpdateBoardEntity(boardDto);
    }

    //특정 게시글 삭제
    public void boardDelete(Long id){
        boardRepository.deleteById(id);
    }
}
