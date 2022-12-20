package com.setproject.common.model.jenum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EGender {
    M(1, "Male", "남성"),
    F(2, "Female", "여성");

    private final Integer id;
    private final String title;
    private final String desc;
}
