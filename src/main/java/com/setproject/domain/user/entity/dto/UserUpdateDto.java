package com.setproject.domain.user.entity.dto;

import com.setproject.common.model.jenum.EGender;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserUpdateDto {
    private String name;
    private String cellphone;
    private EGender gender;
}
