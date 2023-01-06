package com.setproject.domain.board.entity.dto;

import java.time.LocalDateTime;

import com.setproject.domain.board.entity.Board;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardResDto {
	private Long boardId;
	private String bTitle;
	private String bContent;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private Long userId;
	private String userName;

	@Builder
	public BoardResDto(Long boardId, String bTitle, String bContent, LocalDateTime createDate, LocalDateTime updateDate, Long userId, String userName) {
		this.boardId = boardId;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userId = userId;
		this.userName = userName;
	}

	public BoardResDto(Board board) {
		this.boardId = board.getBoardId();
		this.bTitle = board.getBTitle();
		this.bContent = board.getBContent();
		this.createDate = board.getCreateDate();
		this.updateDate = board.getUpdateDate();
		this.userId = board.getUser().getUserId();
		this.userName = board.getUser().getName();
	}

}
