package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;
	
	public List<ProductEntity> getAllProduct()
	{
		return repository.findAll();
	}
	
	public void saveProduct(ProductEntity product)
	{
		repository.save(product);
	}
	public void deleteProductById(int id)
	{
		repository.deleteById(id);
	}
	public ProductDto getProductById(int id)
	{
		ProductEntity product=repository.findById(id).get();
		ProductDto dto=new ProductDto(product.getId(),product.getName(),product.getPrice()
				,product.getWeight(),product.getDescription(),product.getImageName(),product.getCategory().getId());
		return dto;
	}
	public List<ProductEntity> getProductsByCategoryId(int id)
	{
		return repository.findByCategoryId(id);
	}
	
	public ProductEntity getById(int id)
	{
		return repository.findById(id).get();
	}

}
