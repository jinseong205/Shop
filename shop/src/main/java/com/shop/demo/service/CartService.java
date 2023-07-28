package com.shop.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.demo.dto.CartDetailDto;
import com.shop.demo.dto.CartItemDto;
import com.shop.demo.dto.CartOrderDto;
import com.shop.demo.dto.OrderDto;
import com.shop.demo.entity.Cart;
import com.shop.demo.entity.CartItem;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.User;
import com.shop.demo.repository.CartItemRepository;
import com.shop.demo.repository.CartRepository;
import com.shop.demo.repository.ItemRepository;
import com.shop.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartService {

	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	private final CartItemRepository cartItemRepository;
	private final OrderService orderService;

	public Long addCart(CartItemDto cartItemDto, User user) throws Exception {

		Item item = itemRepository.findById(cartItemDto.getItemId())
				.orElseThrow(() -> new Exception("상품 정보를 찾을 수 없습니다."));

		Cart cart = cartRepository.findByUserId(user.getId());

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

	@Transactional(readOnly = true)
	public List<CartDetailDto> getCartList(User user) throws Exception {

		List<CartDetailDto> cartDetailDtoList = new ArrayList<>();

		Cart cart = cartRepository.findByUserId(user.getId());

		if (cart == null)
			return cartDetailDtoList;

		cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
		return cartDetailDtoList;

	}

	public boolean validateCartItem(Long cartItemId, User user) throws Exception {
		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new Exception("장바구니 정보를 찾을 수 없습니다." + cartItemId));
		User savedUser = cartItem.getCart().getUser();

		if (user.getUsername().equals(savedUser.getUsername()))
			return true;
		return false;
	}

	@Transactional(readOnly = true)
	public void updateCartItem(Long cartItemId, int count) throws Exception {

		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new Exception("장바구니 정보를 찾을 수 없습니다. " + cartItemId));
		cartItem.updateCount(count);
		return;

	}

	@Transactional(readOnly = true)
	public void deleteCartItem(Long cartItemId) throws Exception {

		CartItem cartItem = cartItemRepository.findById(cartItemId)
				.orElseThrow(() -> new Exception("장바구니 정보를 찾을 수 없습니다." + cartItemId));
		cartItemRepository.delete(cartItem);
		return;

	}

	public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, User user) throws Exception {

		List<OrderDto> orderDtoList = new ArrayList<>();
		for (CartOrderDto cartOrderDto : cartOrderDtoList) {
			CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
					.orElseThrow(() -> new Exception("장바구니 정보를 찾을 수 없습니다."));

			OrderDto orderDto = new OrderDto();
			orderDto.setItemId(cartItem.getId());
			orderDto.setCount(cartItem.getCount());
			orderDtoList.add(orderDto);	
			}

		Long orderId = orderService.orders(orderDtoList, user);
		
		for(CartOrderDto cartOrderDto : cartOrderDtoList) {
			CartItem cartItem = cartItemRepository.findById(cartOrderDto.getCartItemId())
					.orElseThrow(() -> new Exception("장바구니 정보를 찾을 수 없습니다."));
					cartItemRepository.delete(cartItem);				
		}
		
		return orderId;	
	}

}
