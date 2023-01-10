package com.setproject.domain.board.entity.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentResDto {
	private Long commentId;
	@JsonProperty(value = "cContent")
	private String cContent;
	private LocalDateTime createDate;
	private LocalDateTime updateDate;
	private Long userId;
	private String userName;
	
	@Builder
	public CommentResDto(Long commentId, String cContent, LocalDateTime createDate, LocalDateTime updateDate, Long userId, String userName) {
		this.commentId = commentId;
		this.cContent = cContent;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.userId = userId;
		this.userName = userName;
	}
}
