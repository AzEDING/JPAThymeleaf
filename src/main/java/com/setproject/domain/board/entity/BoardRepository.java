package com.setproject.domain.board.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	Optional<Board> findByBoardIdAndIsdel(Long boardId, Boolean isdel);
}
