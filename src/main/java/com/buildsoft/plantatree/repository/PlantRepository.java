package com.buildsoft.plantatree.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.buildsoft.plantatree.model.Plant;

@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {
	List<Plant> findAllByIdIn(List<Long> ids);
}
