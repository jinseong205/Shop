package com.shop.demo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data						//Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

	private String username;	//user_id
	
	private String password;	//user_pw
	
	private String name;
	
	private String email;		
	
	private String addr;
	
}
	
