package com.setproject.domain.board.entity.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardListResDto {
	@NotNull
	private List<BoardResDto> boards = new ArrayList<>();
	
	Long totalElements;
	
	Boolean last;
	
	Integer totalPages;
	
	Integer numberOfElements;
	
	public BoardListResDto(Page<BoardResDto> boardPage) {
		this.boards = boardPage.getContent();
		this.totalElements = boardPage.getTotalElements();
		this.last = boardPage.isLast();
		this.totalPages = boardPage.getTotalPages();
		this.numberOfElements = boardPage.getNumberOfElements();
	}

}
