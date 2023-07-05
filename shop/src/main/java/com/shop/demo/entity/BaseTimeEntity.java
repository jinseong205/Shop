package com.shop.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
public abstract class BaseTimeEntity {
	

	/* 생성일, 수정일 */ 
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime CRT_DT;
	
	@LastModifiedDate
	private LocalDateTime UPDT_DT;
	
	
}
