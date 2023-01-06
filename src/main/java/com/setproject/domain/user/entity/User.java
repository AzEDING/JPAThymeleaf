package com.setproject.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.setproject.common.config.BaseDateEntity;
import com.setproject.common.model.jenum.EGender;
import com.setproject.common.model.jenum.ERole;
import com.setproject.domain.user.entity.dto.UserUpdateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_user")
public class User extends BaseDateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", columnDefinition = "BIGINT COMMENT '사용자 아이디'")
	private Long userId;

	@Column(name = "email", nullable = false, columnDefinition = "NVARCHAR(30) COMMENT '사용자 이메일'")
	@NotNull(message = "이메일을 입력해주세요")
	private String email;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@Column(name = "password", nullable = false, columnDefinition = "NVARCHAR(250) COMMENT '비밀번호'")
	@NotNull(message = "비밀번호를 입력해주세요")
	private String password;
	
	@Column(name = "name", columnDefinition = "NVARCHAR(30) COMMENT '이름'")
	private String name;
	
	@Column(name = "cellphone", columnDefinition = "NVARCHAR(30) COMMENT '휴대번호'")
	private String cellphone;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", columnDefinition = "NVARCHAR(1) COMMENT '성별'")
	private EGender gender;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", columnDefinition = "NVARCHAR(10) COMMENT '권한'")
	private ERole role;
	
	@Column(name = "isdel", nullable = false)
	@ColumnDefault("0")
	private Boolean isdel;

	@Column(name = "deleteDate", columnDefinition = "DATETIME(3)")
	private LocalDateTime deleteDate;

	public void setRole(ERole role) {
		this.role = role;
	}

	public void setUpdate(UserUpdateDto dto) {
		if (dto.getName() != null)
			this.name = dto.getName();
		if (dto.getCellphone() != null)
			this.cellphone = dto.getCellphone();
		if (dto.getGender() != null)
			this.gender = dto.getGender();
	}

	public void setDel() {
		this.isdel = true;
		this.deleteDate = LocalDateTime.now();
	}
}
