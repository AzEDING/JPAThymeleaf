package com.setproject.domain.board.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.ColumnDefault;

import com.setproject.common.config.BaseDateEntity;
import com.setproject.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_comment")
public class Comment extends BaseDateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id", columnDefinition = "BIGINT COMMENT '댓글 아이디'")
	private Long commentId;

	@Size(min = 2)
	@Column(name = "c_content", columnDefinition = "NVARCHAR(100) COMMENT '댓글 내용'")
	private String cContent;
	
	@ManyToOne
	@JoinColumn(name = "board_id")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "isdel", nullable = false)
	@ColumnDefault("0")
	private Boolean isdel;
	
	@Column(name = "deleteDate", columnDefinition = "DATETIME(3)")
	private LocalDateTime deleteDate;
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public void delComment() {
		this.isdel = true;
		this.deleteDate = LocalDateTime.now();
	}
	
	public void updateComment(String cContent) {
		this.cContent = cContent;
	}
}
