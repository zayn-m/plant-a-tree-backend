package com.buildsoft.plantatree.repository;

import org.springframework.stereotype.Repository;

import com.buildsoft.plantatree.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
