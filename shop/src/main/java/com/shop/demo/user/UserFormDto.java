package com.shop.demo.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data						//Getter Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFormDto {

	private String username;	//user_id
	
	private String password;	//user_pw
	
	private String name;
	
	private String email;		
	
	private String addr;
	
	private String roles;
	
	public List<String> getRoleList(){
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		return new ArrayList<>();
	}
}
	
