package com.shop.demo.user;

import java.sql.Timestamp;
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
@Table(name="users")
public class User {


	@Id	//pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// project에 연결된 db의 넘버링 전략을 따라간다.
	private Long id;	//sequnce //auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username;	//user_id

	@Column(nullable = false, length = 100) // hash (비밀번호 암호화)
	private String password;	//user_pw
	
	@Column( length = 50) 
	private String email;		
	
	//@ColumnDefault("'user'")
	//@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 100) // hash (비밀번호 암호화)
	private String roles;	//Enum을 쓰는게 좋음 //admin, user, manager
	
	@Column( length = 50) 
	private String provider;

	@Column( length = 50) 
	private String providerId;
	

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
	
	@CreationTimestamp	//시간이 자동으로 입력
	private Timestamp createDate;

	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
	
	@Builder
	public User(String username, String password, String email, String roles, String provider, String providerId,
			Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
		this.provider = provider;
		this.providerId = providerId;
		this.createDate = createDate;
	}

}