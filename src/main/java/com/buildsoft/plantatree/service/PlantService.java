package com.buildsoft.plantatree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.buildsoft.plantatree.dto.PagingResponse;
import com.buildsoft.plantatree.dto.PlantDto;
import com.buildsoft.plantatree.model.Category;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.CategoryRepository;
import com.buildsoft.plantatree.repository.PlantRepository;
import com.buildsoft.plantatree.util.PageUtils;

@Service
public class PlantService extends BaseService  {
	@Autowired
	private PlantRepository plantRepo;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Plant findById(Long id) {
		 return plantRepo
	                .findById(id)
	                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO_PLANT_BY_ID"));
	}
	
	public PagingResponse<Plant> findAll(PlantDto request) {
		Pageable pageable = PageUtils.getPage(request);
        Page<Plant> result = Page.empty();
       
        result = plantRepo.findAll(pageable);

        return PagingResponse.of(result);
	}
	
	public List<Plant> getByKeyword(String keyword) {
		return plantRepo.findByKeyword(keyword);
	}
	
	
	public Plant createOrUpdate(Plant plant) {
		checkAdminAccess();
		
		if (plant.getId() != null) {
			return plantRepo.save(plant);
		}
		
		Category cat = categoryRepository.findById(plant.getCategory_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "NO_CATEGORY_BY_ID"));
		
		Plant newPlant = new Plant(plant.getName(), plant.getDescription(), cat, plant.getPrice(), plant.getStockLeft(), plant.getImageUrl());
		return plantRepo.save(newPlant);
	}
	

	@Transactional
	public Plant delete(Plant plant) {
		checkAdminAccess();

		plantRepo.delete(plant);
		return plant;
	}
	
	public List<Plant> findByIds(List<Long> ids) {
		return plantRepo.findAllByIdIn(ids);
	}
}
