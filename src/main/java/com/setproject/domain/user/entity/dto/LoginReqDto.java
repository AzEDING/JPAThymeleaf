package com.setproject.domain.user.entity.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginReqDto {
    private String email;
    private String password;

    @Builder
    public LoginReqDto(String email, String password) {
    	this.email = email;
    	this.password = password;
    }
}
