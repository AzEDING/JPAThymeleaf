package com.setproject.domain.user.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserReqDto {
    private String email;
    private String password;
    private String name;
    private String cellphone;
    private String gender;

    public UserReqDto(String email, String password, String name, String cellphone, String gender) {
        this.email = email;
        this.password = password;
        this.name= name;
        this.cellphone = cellphone;
        this.gender = gender;

    }
}
