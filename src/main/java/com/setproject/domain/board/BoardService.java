package com.setproject.domain.board;

import com.setproject.common.advice.exception.ObjectNotFoundException;
import com.setproject.common.advice.exception.UserNotFoundException;
import com.setproject.domain.board.entity.*;
import com.setproject.domain.board.entity.dto.BoardDetailResDto;
import com.setproject.domain.board.entity.dto.BoardReqDto;
import com.setproject.domain.board.entity.dto.BoardResDto;
import com.setproject.domain.board.entity.dto.BoardUpdateDto;
import com.setproject.domain.user.entity.User;
import com.setproject.domain.user.entity.UserRepository;
import com.setproject.domain.board.entity.dto.CommentReqDto;
import com.setproject.domain.board.entity.dto.CommentResDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping(value = "/v1")
public class BoardService {
	private final UserRepository userRepository;
	private final BoardRepository boardRepository;
	private final BoardQueryRepository boardQueryRepository;
	private final CommentRepository commentRepository;

	@Transactional
	public void postBoard(BoardReqDto dto, HttpServletRequest request, HttpServletResponse response) {
		try {
			User user = userRepository.findById(Long.valueOf(String.valueOf(request.getSession().getAttribute("userId")))).orElseThrow(UserNotFoundException::new);
			Board board = Board.builder()
					.bTitle(dto.getBTitle())
					.bContent(dto.getBContent())
					.user(user)
					.isdel(false)
					.build();
			 boardRepository.save(board);
			response.sendRedirect("/v1/home");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}


	@Transactional(readOnly = true)
	public BoardResDto getBoard(Long boardId) {
		Board board = boardRepository.findByBoardIdAndIsdel(boardId, false).orElseThrow(ObjectNotFoundException::new);
//		List<Comment> comments = boardQueryRepository.getComments(boardId);
		return BoardResDto.builder()
				.boardId(board.getBoardId())
				.bTitle(board.getBTitle())
				.bContent(board.getBContent())
				.createDate(board.getCreateDate())
				.updateDate(board.getUpdateDate())
				.userId(board.getUser().getUserId())
				.userName(board.getUser().getName())
				.build();
	}

	@Transactional(readOnly = true)
	public Page<BoardResDto> getBoardList(Pageable pageable) {
		return boardQueryRepository.getBoardPage(pageable);
	}

	@Transactional
	public void updateBoard(Long boardId, BoardUpdateDto dto, HttpServletResponse response) {
		try {
			Board board = boardRepository.findById(boardId).orElseThrow(ObjectNotFoundException::new);
			board.updateBoard(dto);
			response.sendRedirect("/v1/board/"+boardId+"/detail");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Transactional
	public void deleteBoard(Long boardId, HttpServletResponse response) {
		try {
			Board board = boardRepository.findByBoardIdAndIsdel(boardId, false).orElseThrow(ObjectNotFoundException::new);
			board.deleteBoard();
			response.sendRedirect("/v1/home");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional(readOnly = true)
	public List<CommentResDto> getCommentList(Long boardId) {
		return boardQueryRepository.getComments(boardId);
		
	}
	
	@Transactional
	public void postComment(Long boardId, CommentReqDto commentReqDto, HttpServletRequest request, HttpServletResponse response) {
		try {
			Board board = boardRepository.findByBoardIdAndIsdel(boardId, false).orElseThrow(ObjectNotFoundException::new);
			User user = userRepository.findByUserIdAndIsdel(Long.valueOf(String.valueOf(request.getSession().getAttribute("userId"))), false).orElseThrow(UserNotFoundException::new);
			Comment comment = Comment.builder().cContent(commentReqDto.getCContent()).board(board).isdel(false).build();
			comment.setUser(user);
			commentRepository.save(comment);
			response.sendRedirect("/v1/board/" + boardId + "/detail");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Transactional
	public void updateComment(Long cid, CommentReqDto commentReqDto) {
		Comment comment = commentRepository.findByCommentIdAndIsdel(cid, false).orElseThrow(ObjectNotFoundException::new);
		comment.updateComment(commentReqDto.getCContent());
	}
	
	@Transactional
	public void delComment(Long cid, HttpServletResponse response) {
		try {
			Comment comment = commentRepository.findByCommentIdAndIsdel(cid, false).orElseThrow(ObjectNotFoundException::new);
			comment.delComment();
			response.sendRedirect("/v1/board/" + comment.getBoard().getBoardId());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
