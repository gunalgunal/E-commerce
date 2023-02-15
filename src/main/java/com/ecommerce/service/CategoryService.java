package com.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.entity.CategoryItem;
import com.ecommerce.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public void addCategory(CategoryItem category)
	{
		repository.save(category);
	}
	
	public List<CategoryItem> getAllCategory()
	{
		return repository.findAll();
	}
	
	public CategoryItem getById(int id)
	{
		return repository.findById(id).get();
	}
	public void updateTheItem(CategoryItem category)
	{
		repository.save(category);
	}
	public void deleteCategoryById(int id)
	{
		repository.deleteById(id);
	}

}
