package com.buildsoft.plantatree.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildsoft.plantatree.message.response.BaseResponse;
import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.CartItem;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.service.CartService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("")
	public Cart findOne() {
		return cartService.findOne();
	}
	
	@GetMapping("/items")
	public ResponseEntity<Object> findItems() {
		List<CartItem> items = cartService.findItems();
		return BaseResponse.generateResponse("Success!", HttpStatus.OK, items);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Object> addToCart(@Valid @RequestBody CartItem item) {
		try {
			CartItem result;
			result = cartService.addToCart(item);
			return BaseResponse.generateResponse("Successfully added data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@DeleteMapping("/item/{id}")
	public ResponseEntity<Object> removeItem(@PathVariable String id) {
		try {
			CartItem result = cartService.removeItem(id);
			return BaseResponse.generateResponse("Successfully deleted data!", HttpStatus.OK, null);
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);	
		}
	}
}
