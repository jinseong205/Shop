package com.shop.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.demo.dto.OrderItemDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.Order;
import com.shop.demo.entity.OrderItem;
import com.shop.demo.entity.User;
import com.shop.demo.repository.ItemRepository;
import com.shop.demo.repository.OrderRepository;
import com.shop.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

	private final ItemRepository itemRepository;
	private final UserRepository userRepository;
	private final OrderRepository orderRepository;
	
	public Long order(OrderItemDto orderItemDto, User user) throws Exception {
		Item item = itemRepository.findById(orderItemDto.getItemId())
				.orElseThrow(() -> new Exception("해당 상품을 찾을 수 없습니다. itemdId : " + orderItemDto.getItemId()));
		
		List<OrderItem> orderItemList = new ArrayList<>();
		
		OrderItem orderItem = OrderItem.createOrderItem(item, orderItemDto.getCount());
		orderItemList.add(orderItem);
		
		Order order = Order.createOrder(user, orderItemList);	
		orderRepository.save(order);	
		
		return order.getId();
		}
}
