package com.setproject.domain.user.entity;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class UserQueryRepository {
	private final JPAQueryFactory jpaQueryFactory;
	
	
}
