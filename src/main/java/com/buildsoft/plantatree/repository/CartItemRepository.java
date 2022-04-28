package com.buildsoft.plantatree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.CartItem;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.model.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	List<CartItem> findByCart(Cart cart);
	CartItem findByCartAndPlant(Cart cart, Plant plant);
	List<CartItem> findAllByIdIn(Long[] ids);
}
