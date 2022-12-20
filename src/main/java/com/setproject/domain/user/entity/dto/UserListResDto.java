package com.setproject.domain.user.entity.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.domain.Page;

import com.setproject.domain.user.entity.User;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserListResDto {
    @NotNull
    private List<User> users = new ArrayList<>();

    Long totalElements;

    Boolean last;

    Integer totalPages;

    Integer numberOfElements;

    public UserListResDto(Page<User> userPage) {
        for (User user : userPage) {
            users.add(user);
        }
        this.totalElements = userPage.getTotalElements();
        this.last = userPage.isLast();
        this.totalPages = userPage.getTotalPages();
        this.numberOfElements = userPage.getNumberOfElements();
    }
}
