package com.shop.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.demo.dto.OrderDto;
import com.shop.demo.dto.OrderHistDto;
import com.shop.demo.dto.OrderItemDto;
import com.shop.demo.entity.Item;
import com.shop.demo.entity.ItemImg;
import com.shop.demo.entity.Order;
import com.shop.demo.entity.OrderItem;
import com.shop.demo.entity.User;
import com.shop.demo.repository.ItemImgRepository;
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
	private final ItemImgRepository itemImgRepository;

	public Long order(OrderDto orderItemDto, User user) throws Exception {
		Item item = itemRepository.findById(orderItemDto.getItemId())
				.orElseThrow(() -> new Exception("해당 상품을 찾을 수 없습니다. itemdId : " + orderItemDto.getItemId()));

		List<OrderItem> orderItemList = new ArrayList<>();

		OrderItem orderItem = OrderItem.createOrderItem(item, orderItemDto.getCount());
		orderItemList.add(orderItem);

		Order order = Order.createOrder(user, orderItemList);
		orderRepository.save(order);

		return order.getId();
	}

	
	@Transactional(readOnly = true)
	public Page<OrderHistDto> getOrderList(User user, Pageable pageable) {
		List<Order> orders = orderRepository.findOrders(user.getId(), pageable);
		Long totalCount = orderRepository.countOrder(user.getId());

		List<OrderHistDto> orderHistDtos = new ArrayList<>();

		for (Order order : orders) {
			OrderHistDto orderHistDto = new OrderHistDto(order);
			List<OrderItem> orderItems = order.getOrderItems();
			for(OrderItem orderItem : orderItems) {
				ItemImg itemImg = itemImgRepository.findByItemIdAndRepImgYn(orderItem.getItem().getId(), "Y");
				OrderItemDto orderItemDto = new OrderItemDto(orderItem, itemImg.getImgUrl());
				orderHistDto.addOrderItemDto(orderItemDto);		
				}
			orderHistDtos.add(orderHistDto);
		}

		return new PageImpl<OrderHistDto>(orderHistDtos, pageable, totalCount);
	}
}
