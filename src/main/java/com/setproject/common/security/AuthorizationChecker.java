package com.setproject.common.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import com.setproject.common.advice.exception.ObjectNotFoundException;
import com.setproject.domain.board.entity.Board;
import com.setproject.domain.board.entity.BoardRepository;
import com.setproject.domain.user.entity.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthorizationChecker {
	private final BoardRepository boardRepository;

	public boolean boardIdCheck(HttpServletRequest request, Long boardId) {
		Board board = boardRepository.findByBoardIdAndIsdel(boardId, false).orElseThrow(ObjectNotFoundException::new);
		Long userId = Long.valueOf(request.getSession().getAttribute("userId").toString());
		String role = request.getSession().getAttribute("userRole").toString();
		if (!board.getUser().getUserId().equals(userId)) {
			return false;
		} else if (role.equals("ROLE_ADMIN")) {
			return true;
		}
		return true;
	}
}
