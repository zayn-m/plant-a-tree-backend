package com.buildsoft.plantatree.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
	Cart findByUser(User user);
	List<Cart> findByUserOrderByCreatedAtDesc(User user);
}
