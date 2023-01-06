package com.setproject.domain.board.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;
import com.setproject.common.config.BaseDateEntity;
import com.setproject.domain.board.entity.dto.BoardUpdateDto;
import com.setproject.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_board")
public class Board extends BaseDateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id", columnDefinition = "BIGINT COMMENT '게시글 아이디'")
	private Long boardId;

	@Size(min = 2, message = "최소 2자 이상")
	@Column(name = "b_title", nullable = false, columnDefinition = "NVARCHAR(50) COMMENT '게시글 제목'")
	@NotBlank(message = "제목을 입력해주세요 (2자 이상)")
	private String bTitle;

	@Size(min = 2, message = "최소 2자 이상")
	@Column(name = "b_cotent", nullable = false, columnDefinition = "NVARCHAR(200) COMMENT '게시글 내용'")
	@NotBlank(message = "내용을 입력해주세요 (2자 이상)")
	private String bContent;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	private List<Comment> comments;
	
	@Column(name = "isdel", nullable = false)
	@ColumnDefault("0")
	private Boolean isdel;
	
	@Column(name = "deleteDate", columnDefinition = "DATETIME(3)")
	private LocalDateTime deleteDate;
	
	public void updateBoard(BoardUpdateDto dto) {
		this.bTitle = dto.getBTitle();
		this.bContent = dto.getBContent();
	}
	
	public void deleteBoard() {
		this.isdel = true;
		this.deleteDate = LocalDateTime.now();
	}
}
