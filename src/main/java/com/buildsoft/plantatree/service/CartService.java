package com.buildsoft.plantatree.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.CartItem;
import com.buildsoft.plantatree.model.Category;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.CartItemRepository;
import com.buildsoft.plantatree.repository.CartRepository;

@Service
public class CartService extends BaseService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private PlantService plantService;
	
	public Cart findOne() {
		User user = getLoggedInUser();
		List<Cart> cart = cartRepository.findByUserOrderByCreatedAtDesc(user);
//		ArrayList<Long> plantIds = new ArrayList();
//		List<CartItem> cartItems = cartItemRepository.findByCart(cart);
//		System.out.println(cartItems);
//		cartItems.forEach(item -> {
//			System.out.println(item.getPlant().getId());
//			plantIds.add(item.getPlant().getId());
//		});
//		List<Plant> plants = plantService.findByIds(plantIds);
//		cart.setItems(cartItems);
		
		return cart.get(0);
	}
	
	public List<CartItem> findItems() {
		User user = getLoggedInUser();
		Cart cart = this.findOne();
		return cartItemRepository.findByCart(cart);
	}
	
	public Cart create(User user) {
		Cart cart = new Cart(user);
		return cartRepository.save(cart);
	}
	
	public CartItem addToCart(CartItem item) {
		User user = getLoggedInUser();
		Cart cart = this.findOne();
		Plant plant = plantService.findById(item.getPlantId());
		CartItem cartItem = null;
		cartItem = cartItemRepository.findByCartAndPlant(cart, plant);
		
		if (cartItem != null) {
			cartItem.setQuantity(cartItem.getQuantity() + 1);
			cartItemRepository.save(cartItem);
		} else {
			Long quantity = (long) 1;
			cartItem = new CartItem(cart, plant, quantity);
		}
		
		return cartItemRepository.save(cartItem);
	}
	
	public CartItem removeItem(String id) {
		User user = getLoggedInUser();
		CartItem item = cartItemRepository.getById(Long.parseLong(id));
		cartItemRepository.delete(item);
		return item;
	}
}
