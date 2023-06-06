package com.shop.demo.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.shop.demo.config.auth.PrincipalDetails;

public class AuditAwareImpl implements AuditorAware<String>{

	@Override
	public Optional<String> getCurrentAuditor() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		String username = "";
		PrincipalDetails principalDetails;
		
		if(authentication != null ) {
			principalDetails = (PrincipalDetails)authentication.getPrincipal();
			username = principalDetails.getUsername();
		}
		
		return Optional.of(username);
		
	}
	
}
