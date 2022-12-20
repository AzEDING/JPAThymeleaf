package com.setproject.domain.board.entity.dto;

import com.setproject.domain.board.entity.Comment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class BoardDetailResDto {
    private Long boardId;
    private String bTitle;
    private String bContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String userName;
    private Long userId;
    private List<Comment> comments;

    @Builder
    public BoardDetailResDto(Long boardId, String bTitle, String bContent, LocalDateTime createDate, LocalDateTime updateDate, String userName, Long userId, List<Comment> comments) {
        this.boardId = boardId;
        this.bTitle = bTitle;
        this.bContent = bContent;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.userId =userId;
        this.userName = userName;
        this.comments = comments;
    }
}
