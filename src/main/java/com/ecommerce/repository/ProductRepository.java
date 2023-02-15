package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity,Integer> {
	
       List<ProductEntity> findByCategoryId(int id);
}
