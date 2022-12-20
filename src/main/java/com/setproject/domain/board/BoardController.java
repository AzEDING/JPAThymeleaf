package com.setproject.domain.board;

import com.setproject.domain.board.entity.dto.BoardReqDto;
import com.setproject.domain.board.entity.dto.BoardUpdateDto;
import com.setproject.domain.board.entity.dto.CommentReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class BoardController {
	private final BoardService boardService;

	@GetMapping(value = "/home")
	public ModelAndView getHome(@PageableDefault(sort ="createDate", direction = Direction.DESC) Pageable pageable) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("boardPage", boardService.getBoardList(pageable));
		modelAndView.addObject("maxPage", 10);
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@GetMapping(value = "/board")
	public ModelAndView getBoardPost() {
		BoardReqDto boardReqDto = new BoardReqDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("boardReqDto", boardReqDto);
		modelAndView.setViewName("postBoard");
		return modelAndView;
	}

	@GetMapping(value = "/board/{boardId}")
	public ModelAndView getBoard(@PathVariable Long boardId) {
		CommentReqDto commentReqDto = new CommentReqDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board", boardService.getBoard(boardId));
		modelAndView.addObject("comment", commentReqDto);
		modelAndView.setViewName("getBoard");
		return modelAndView;
	}

	@GetMapping(value = "/board/{boardId}/update")
	public ModelAndView updateBoard(@PathVariable Long boardId) {
		BoardUpdateDto boardUpdateDto = new BoardUpdateDto();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("board", boardService.getBoard(boardId));
		modelAndView.addObject("boardId", boardId);
		modelAndView.addObject("boardUpdateDto", boardUpdateDto);
		modelAndView.setViewName("updateBoard");
		return modelAndView;
	}
	
	@PostMapping(value = "/board")
	public void postBoard(@Valid @ModelAttribute BoardReqDto boardReqDto, HttpServletRequest request, HttpServletResponse response) {
		boardService.postBoard(boardReqDto, request, response);
	}
	
	@PostMapping(value = "/board/{boardId}/update")
	public void updateBoard(@PathVariable Long boardId, @Valid @ModelAttribute BoardUpdateDto boardUpdateDto, HttpServletResponse response) {
		boardService.updateBoard(boardId, boardUpdateDto, response);
	}
	
	@PostMapping(value = "/board/{boardId}/delete")
	public void delBoard(@PathVariable Long boardId, HttpServletResponse response) {
		boardService.deleteBoard(boardId, response);
	}

	@PostMapping(value = "/board/{boardId}/comment")
	public void postComment(@PathVariable Long boardId, @Valid @ModelAttribute CommentReqDto commentReqDto, HttpServletRequest request, HttpServletResponse response) {
		boardService.postComment(boardId, commentReqDto, request, response);
	}
	
	@PostMapping(value = "comment/{cid}/update")
	public void updateComment(@PathVariable Long cid, @Valid @ModelAttribute CommentReqDto commentReqDto) {
		boardService.updateComment(cid, commentReqDto);
	}
	
	@GetMapping(value = "/comment/{cid}/delete")
	public void delComment(@PathVariable Long cid, HttpServletResponse response) {
		boardService.delComment(cid, response);
	}

}
