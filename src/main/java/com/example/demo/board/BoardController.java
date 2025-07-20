package com.example.demo.board;

import com.example.demo.User.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/board")
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String boardCrate(
            BoardRequestDto boardRequestDto,
            BindingResult bindingResult,
            Principal principal
    ) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(bindingResult.hasErrors()) {
            return "redirect:/board/fail";
        }

        boardService.addItem(boardRequestDto, user);

        return "redirect:/board/boardList";
    }

    @GetMapping("boardList")
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model){
        Page<BoardDto> boardlist = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < boardlist.getTotalPages()) ? startPage + blockLimit -1 : boardlist.getTotalPages();
        model.addAttribute("boardlist", boardlist);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "board_list";
    }
}
