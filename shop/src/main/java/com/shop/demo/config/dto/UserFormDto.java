package com.shop.demo.config.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data						//Getter Setter
public class UserFormDto {

	@NotBlank(message = "아이디는 필수 입력 값잆니다.")
	@Length(min=8, max=20, message = "아이디는 8자 이상 20자 이하로 입력하세요.")
	private String username;	
	
	@NotBlank(message = "비밀번호는 필수 입력 값잆니다.")
	@Length(min=8, max=20, message = "패스워드는 8자 이상 20자 이하로 입력하세요.")

	private String password;	
	
	private String name;
	
	private String email;
	
	@NotBlank(message = "주소는 필수 입력 값잆니다.")
	private String addr;
	

}
	
