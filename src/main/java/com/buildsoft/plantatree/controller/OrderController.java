package com.buildsoft.plantatree.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildsoft.plantatree.message.response.BaseResponse;
import com.buildsoft.plantatree.model.Order;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("")
	public ResponseEntity<Object> orders() {
		List<Order> orders = orderService.findAll();
		return BaseResponse.generateResponse("Success!", HttpStatus.OK, orders);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> create(@Valid @RequestBody Order order) {
		try {
			Order result = orderService.create(order);
			return BaseResponse.generateResponse("Successfully added data!", HttpStatus.CREATED, result);
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
}
