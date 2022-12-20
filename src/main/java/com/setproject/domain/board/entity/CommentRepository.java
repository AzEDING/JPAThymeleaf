package com.setproject.domain.board.entity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByBoardAndIsdel(Long boardId, Boolean isdel);
	Optional<Comment> findByCommentIdAndIsdel(Long commentId, Boolean isdel);

}
