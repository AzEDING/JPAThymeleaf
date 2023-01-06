package com.setproject.domain.board.entity;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.setproject.domain.board.entity.dto.BoardResDto;
import com.setproject.domain.board.entity.dto.CommentResDto;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.setproject.domain.board.entity.QBoard.board;
import static com.setproject.domain.user.entity.QUser.user;
import static com.setproject.domain.board.entity.QComment.comment;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;

	public Page<BoardResDto> getBoardPage(Pageable pageable) {
		List<BoardResDto> content = jpaQueryFactory
				.select(Projections.constructor(BoardResDto.class,
					board.boardId,
					board.bTitle,
					board.bContent,
					board.createDate,
					board.updateDate,
					board.user.userId,
					board.user.name.as("userName")
				))
				.from(board)
				.where(board.isdel.eq(false))
				.orderBy(board.createDate.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		return new PageImpl<>(content, pageable, content.size());
	}

	public List<CommentResDto> getComments(Long boardId) {
		return jpaQueryFactory.select(Projections.constructor(CommentResDto.class,
				comment.commentId,
				comment.cContent,
				comment.createDate,
				comment.updateDate,
				comment.user.userId,
				comment.user.name.as("userName")
				))
				.from(comment)
				.where((comment.board.boardId.eq(boardId)).and(comment.isdel.eq(false)))
				.orderBy(comment.createDate.asc())
				.fetch();
	}
}
