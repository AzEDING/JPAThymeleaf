//package com.studysetting.domain.user.entity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Getter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "tb_role")
//public class Role {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id", columnDefinition = "BIGINT COMMENT '권한 아이디'")
//    private Long roleId;
//
//	@Column(name = "role_name", nullable = false, columnDefinition = "NVARCHAR(10) COMMENT '권한명'")
//	@NotNull
//	private String roleName;
//
//	@OneToOne
//	@JoinColumn(name = "user_id", columnDefinition = "BIGINT COMMENT '사용자 아이디'")
//	private User user;
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//}
