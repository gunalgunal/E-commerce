package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.CategoryItem;

public interface CategoryRepository extends JpaRepository<CategoryItem,Integer>{

}
