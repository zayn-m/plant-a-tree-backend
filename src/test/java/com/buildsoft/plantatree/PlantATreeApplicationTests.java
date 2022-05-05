package com.buildsoft.plantatree;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import com.buildsoft.plantatree.model.Cart;
import com.buildsoft.plantatree.model.CartItem;
import com.buildsoft.plantatree.model.Category;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.CartItemRepository;
import com.buildsoft.plantatree.repository.CartRepository;
import com.buildsoft.plantatree.repository.CategoryRepository;
import com.buildsoft.plantatree.repository.PlantRepository;
import com.buildsoft.plantatree.repository.UserRepository;
import com.buildsoft.plantatree.service.AuthService;
import com.buildsoft.plantatree.service.BaseService;
import com.buildsoft.plantatree.service.CartService;
import com.buildsoft.plantatree.service.CategoryService;
import com.buildsoft.plantatree.service.PlantService;

@RunWith(SpringRunner.class)
@SpringBootTest
class PlantATreeApplicationTests {
	
	@InjectMocks
	private BaseService baseService;

	@Autowired
	private AuthService authService;
	
	@Autowired
	private PlantService plantService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private PlantRepository plantRepo;
	
	@MockBean
	private CategoryRepository categoryRepo;
	
	@MockBean
	private CartRepository cartRepo;
	
	@MockBean
	private CartItemRepository cartItemRepo;
	
	public User createTestUser() {
		return new User("Jon Doe", "jondoe", "jondoe@gmail.com", encoder.encode("12345"));
	}
	
	@Test
	public void createUserTest() {
		User user = this.createTestUser();
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user.getUsername(), "jondoe");
	}
	
	@Test
	public void createOrUpdateTest() {
		Category cat = new Category("Herbs", "Description...");
		when(categoryRepo.save(cat)).thenReturn(cat);
		assertEquals(cat, categoryService.createCategory(cat));
		Long price = (long) 10;
		Long stock = (long) 100;
		Plant plant = new Plant("Herb", "Description...", cat, price, stock, "https://imageurl-s3.com");
		when(categoryRepo.findById(cat.getId())).thenReturn(Optional.of(cat));
		when(plantRepo.save(plant)).thenReturn(plant);
	}
	
	@Test
	public void deletePlantTest() {
		Category cat = new Category("Herbs", "Description...");
		when(categoryRepo.save(cat)).thenReturn(cat);
		assertEquals(cat, categoryService.createCategory(cat));
		Long price = (long) 10;
		Long stock = (long) 100;
		Plant plant = new Plant("Herb", "Description...", cat, price, stock, "https://imageurl-s3.com");
		plantService.delete(plant);
		verify(plantRepo, times(1)).delete(plant);
	}
	
	@Test
	public void createCategoryTest() {
		User user = this.createTestUser();
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user.getUsername(), "jondoe");
		Cart cart = cartService.create(user);
		when(cartRepo.save(cart)).thenReturn(cart);
	}
	
	@Test
	public void findItemsByCartTest() {
		User user = this.createTestUser();
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user.getUsername(), "jondoe");
		Cart cart = cartService.create(user);
		when(cartRepo.save(cart)).thenReturn(cart);
		
		List<CartItem> items = null;
		when(cartItemRepo.findByCart(cart)).thenReturn(items);
		
	}
	
	@Test
	public void addItemToCartTest() {
		
	}

}
