package com.setproject.common.model.jenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ERole {
    ROLE_USER(1, "user", "유저"),
    ROLE_ADMIN(2, "admin", "관리자");

    private final Integer id;
    private final String title;
    private final String desc;
}
