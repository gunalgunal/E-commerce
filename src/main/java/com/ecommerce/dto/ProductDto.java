package com.ecommerce.dto;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ecommerce.entity.CategoryItem;

public class ProductDto {
	private int id;
	private String name;
	
	
	private int categoryId;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
	public ProductDto()
	{
		
	}
	
	public ProductDto(int id,String name,double price
			,double weight,String description,String imageName,int categoryId)
	{
		this.id=id;
		this.name=name;
		
		this.price=price;
		this.weight=weight;
		this.description=description;
		this.imageName=imageName;
		this.categoryId=categoryId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
}
