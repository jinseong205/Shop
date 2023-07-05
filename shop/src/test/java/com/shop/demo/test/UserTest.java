package com.shop.demo.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shop.demo.entity.User;
import com.shop.demo.repository.UserRepository;
import com.shop.demo.test.custom.WithMockCustomUser;

@SpringBootTest
@Transactional
public class UserTest {

	@Autowired
	UserRepository userRepository;

	@PersistenceContext
	EntityManager em;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	@DisplayName("--- Auditing 테스트 ---")
	@WithMockCustomUser
	//@WithMockUser(username = "jinseong", roles= {"USER", "ADMIN"})
	public void auditingTest() {

		User newUser = User.builder().username("gomawoomi").password(bCryptPasswordEncoder.encode("0000")).name("정진성")
				.email("gomawoomi@gmail.com").roles("ROLE_USER").build();
 
		userRepository.save(newUser);

		em.flush();
		em.clear();

		
		User user = userRepository.findById(newUser.getId()).orElseThrow(EntityNotFoundException::new);

		System.out.println("crtDt : " + user.getCrtDt());
		System.out.println("updtDt : " + user.getUpdtDt());
		System.out.println("create By : " + user.getCrtName());
		System.out.println("modify By : " + user.getUpdtName());

	}

}
