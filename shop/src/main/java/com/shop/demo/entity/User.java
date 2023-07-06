package com.shop.demo.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data						//Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity						//User Class가 자동으로 Oracle에 생성
@Table(name="rs")
public class User extends BaseEntity{


	@Id	//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// project에 연결된 db의 넘버링 전략을 따라간다.
	@Column(name="user_id")
	private Long id;	//sequnce //auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;	//user_id

	@Column(nullable = false, length = 100) // hash (비밀번호 암호화)
	private String password;	//user_pw
	
	private String name;
	
	private String addr;
	
	@Column(length = 50) 
	private String email;		
	
	
	//@ColumnDefault("'user'")
	@Column(nullable = false, length = 100) // hash (비밀번호 암호화)
	private String roles;	//Enum을 쓰는게 좋음 //admin, user, manager
	
	//예비속성
	@Column( length = 50) 
	private String attr1;
	
	@Column( length = 50) 
	private String attr2;
	
	@Column( length = 50) 
	private String attr3;
	
	@Column( length = 50) 
	private String attr4;
	
	@Column( length = 50) 
	private String attr5;
	
	@Column( length = 50) 
	private String attr6;
	
	@Column( length = 50) 
	private String attr7;
	
	@Column( length = 50) 
	private String attr8;
	
	@Column( length = 50) 
	private String attr9;
	
	@Column( length = 50) 
	private String attr10;
	
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	

}