package com.buildsoft.plantatree.controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildsoft.plantatree.dto.CategoryDto;
import com.buildsoft.plantatree.dto.PagingResponse;
import com.buildsoft.plantatree.message.request.CategoryForm;
import com.buildsoft.plantatree.message.response.BaseResponse;
import com.buildsoft.plantatree.service.CategoryService;
import com.buildsoft.plantatree.model.Category;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("")
	public ResponseEntity<Object> createOrUpdate(@Valid  @RequestBody Category c) {
		try {
			Category result = categoryService.createCategory(c);
			return BaseResponse.generateResponse("Successfully added data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@DeleteMapping("")
	public ResponseEntity<Object> deleteCategory(@RequestParam(value="id") Long id) {
		try {
			Category cat = categoryService.findById(id);
			categoryService.delete(cat);
			return BaseResponse.generateResponse("Successfully deleted data!", HttpStatus.OK, cat);	
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	}
	
	@GetMapping("")
	public PagingResponse<Category> findAll(CategoryDto request) {
		return categoryService.findAll(request);
	}
	
}
