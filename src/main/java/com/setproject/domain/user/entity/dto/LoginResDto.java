package com.setproject.domain.user.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginResDto {
	private Long userId;
	private String email;
	private String name;
	private String cellphone;
	private String gender;
	private String role;
	
	@Builder
	public LoginResDto(Long id, String email, String name, String cellphone, String gender, String role) {
		this.userId = id;
		this.email = email;
		this.name = name;
		this.cellphone = cellphone;
		this.gender = gender;
		this.role = role;
	}
}
