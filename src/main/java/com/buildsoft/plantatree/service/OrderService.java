package com.buildsoft.plantatree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.CartItem;
import com.buildsoft.plantatree.model.Order;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.CartItemRepository;
import com.buildsoft.plantatree.repository.CartRepository;
import com.buildsoft.plantatree.repository.OrderRepository;

@Service
public class OrderService extends BaseService {
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartService cartService;
	
	public Order create(Order order) {
		User user = getLoggedInUser();
		Long total = (long) 0;
		List<CartItem> items = cartItemRepository.findAllByIdIn(order.getCartItemsIds());
		
		for (int i=0; i<items.size(); i++) {
			total = total + items.get(i).getPlant().getPrice();
		}
		
		Order orderObj = new Order(user, order.getStripeToken(), order.getCartItemsIds(), total, order.getAddress(), order.getSortCode());
		Order createdOrder =  orderRepository.save(orderObj);
		cartService.create(user);
		return createdOrder;
		
	}
	
	public List<Order> findAll() {
		return orderRepository.findByUser(getLoggedInUser());
	}
}
