package com.shop.demo.repository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.demo.dto.CartItemDto;
import com.shop.demo.entity.Cart;
import com.shop.demo.entity.CartItem;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepoitory;
	private final CartItemRepository cartItemRepository;

	public Long addCart(CartItemDto cartItemDto, User user) throws Exception {

		Item item = itemRepository.findById(cartItemDto.getItemId())
				.orElseThrow(() -> new Exception("상품 정보를 찾을 수 없습니다."));

		Cart cart = cartRepoitory.findByUserId(user.getId());

		CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

		if (savedCartItem != null) {
			savedCartItem.addCount(cartItemDto.getCount());
			return savedCartItem.getId();
		} else {
			CartItem cartItem = CartItem.createCatrtItem(cart, item, cartItemDto.getCount());
			cartItemRepository.save(cartItem);
			return cartItem.getId();
		}
	}

}
