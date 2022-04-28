package com.buildsoft.plantatree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.buildsoft.plantatree.dto.CategoryDto;
import com.buildsoft.plantatree.dto.PagingResponse;
import com.buildsoft.plantatree.message.request.CategoryForm;
import com.buildsoft.plantatree.message.response.BaseResponse;
import com.buildsoft.plantatree.model.Category;
import com.buildsoft.plantatree.repository.CategoryRepository;
import com.buildsoft.plantatree.util.PageUtils;

@Service
public class CategoryService extends BaseService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Category findById(Long id) {
		 return categoryRepository
	                .findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO_CATEGORY_BY_ID"));
	}
	
	public PagingResponse<Category> findAll(CategoryDto request) {
        Pageable pageable = PageUtils.getPage(request);
        Page<Category> result = Page.empty();
       
        result = categoryRepository.findAll(pageable);

        return PagingResponse.of(result);
    }

	@Transactional
	public Category createCategory(Category data) {
		checkAdminAccess();
		
		return categoryRepository.save(data);
	}
	
	@Transactional
	public Category delete(Category cat) {
		checkAdminAccess();
	
		categoryRepository.delete(cat);
		return cat;
	}
}
