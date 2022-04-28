package com.buildsoft.plantatree.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.buildsoft.plantatree.dto.CategoryDto;
import com.buildsoft.plantatree.dto.PagingResponse;
import com.buildsoft.plantatree.dto.PlantDto;
import com.buildsoft.plantatree.message.response.BaseResponse;
import com.buildsoft.plantatree.model.Category;
import com.buildsoft.plantatree.model.Plant;
import com.buildsoft.plantatree.service.PlantService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/plants")
public class PlantController extends BaseController {

	@Autowired
	private PlantService plantService;
	
	@GetMapping("")
	public PagingResponse<Plant> findAll(PlantDto request) {
		return plantService.findAll(request);
	}
	
	@GetMapping("{id}")
	public Plant findOne(@PathVariable Long id) {
		return plantService.findById(id);
	}
	
	
	@PostMapping("")
	@Secured({ROLE_ADMIN})
	public ResponseEntity<Object> createOrUpdate(@Valid @RequestBody Plant p) {
		try {
			Plant result;
			result = plantService.createOrUpdate(p);
			return BaseResponse.generateResponse("Successfully added data!", HttpStatus.OK, result);
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
		}
	}
	
	@DeleteMapping("")
	@Secured({ROLE_ADMIN})
	public ResponseEntity<Object> delete(@RequestParam(value="id") Long id) {
		try {
			Plant plant = plantService.findById(id);
			plantService.delete(plant);
			return BaseResponse.generateResponse("Successfully deleted data!", HttpStatus.OK, plant);	
		} catch (Exception e) {
			return BaseResponse.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
		}
		
	}
}
