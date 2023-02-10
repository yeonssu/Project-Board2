package com.project.board.controller;

import com.project.board.dto.BoardDto;
import com.project.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    @GetMapping("/board/write")
    public String boardWriteForm() {
        return "boardwrite";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(BoardDto boardDto, Model model) {
        boardService.boardWrite(boardDto);

        model.addAttribute("message", "글 작성이 완료되었습니다");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model) {
        model.addAttribute("list", boardService.boardList());
        return "boardlist";
    }

    @GetMapping("/board/view") //localhost:8080/board/view?id=1 형식으로 들어오면 id에 1을 넘겨준다
    public String boardView(Model model, Long id) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardview";
    }

    @GetMapping("/board/delete")
    public String boardDelete(Long id, Model model) {
        boardService.boardDelete(id);

        model.addAttribute("message", "게시글이 삭제되었습니다");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model) {
        model.addAttribute("board", boardService.boardView(id));
        return "boardmodify";
    }

    @PostMapping("/board/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, BoardDto boardDto, Model model){

        BoardDto boardTemp = boardService.boardView(id);
        boardTemp.setTitle(boardDto.getTitle());
        boardTemp.setContent(boardDto.getContent());

        boardService.boardUpdate(boardTemp, id);

        model.addAttribute("message", "글 수정이 완료되었습니다");
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

}
