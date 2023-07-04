package com.shop.demo.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shop.demo.config.dto.UserFormDto;
import com.shop.demo.entity.Cart;
import com.shop.demo.entity.CartRepository;
import com.shop.demo.entity.User;
import com.shop.demo.repository.UserRepository;
import com.shop.demo.service.UserService;

@SpringBootTest
@Transactional
public class CartTest {

	@Autowired
	CartRepository cartRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@PersistenceContext
	EntityManager em;
	

	
	public User createUser() {
		UserFormDto userDto = new UserFormDto();
		
		userDto.setUsername("gomawoomi");
		userDto.setPassword("0000");
		userDto.setName("정진성");
		userDto.setEmail("goamwoomi@gmail.com");;
		
		return User.builder()
				.username(userDto.getUsername())
				.password(userDto.getPassword())
				.name(userDto.getName())
				.email(userDto.getEmail())
				.roles("ROLE_USER")
				.build();	
	}
	
	
	@Test
	@DisplayName("--- 장바구니 회원 매핑 테스트 ---")
	public void findCartAndUser() {
		
		User user = createUser();
		userRepository.save(user);
		
		Cart cart = new Cart();
		cart.setUser(user);
		cartRepository.save(cart);
		
		em.flush();
		em.clear();
		
		Cart savedCart = cartRepository.findById(cart.getId())
							.orElseThrow(EntityNotFoundException::new);
		assertEquals(savedCart.getUser().getId(), user.getId());
		
		
		
	
	}
}
