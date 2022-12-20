package com.setproject.domain.board.entity.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardReqDto {
	@NotNull
	private String bTitle;
	@NotNull
	private String bContent;
}
