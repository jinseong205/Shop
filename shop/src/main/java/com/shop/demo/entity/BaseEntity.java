package com.shop.demo.entity;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@EntityListeners(value = {AuditingEntityListener.class})
@MappedSuperclass
public class BaseEntity extends BaseTimeEntity{
	
	/* 등록자, 수정자 */ 
    @CreatedBy
    @Column(updatable = false)
    private String CRT_ID;

    @LastModifiedBy
    private String UPDT_ID;
}
