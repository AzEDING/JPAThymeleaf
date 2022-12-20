package com.setproject.common.config;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Past;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseDateEntity {

    @Past
    @CreationTimestamp
    @Column(name = "create_date", nullable = false, columnDefinition = "DATETIME(3)")
    private LocalDateTime createDate;

    @Past
    @UpdateTimestamp
    @Column(name = "update_date", nullable = false, columnDefinition = "DATETIME(3)")
    private LocalDateTime updateDate;
}
