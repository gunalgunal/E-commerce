package com.ecommerce.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id",referencedColumnName="category_id")
	private CategoryItem category;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	
	public ProductEntity()
	{
		
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getId()
	{
		return id;
	}
	public void setName(String name)
	{
		this.name=name;
	}
	public String getName()
	{
		return name;
	}
	public void setCategory(CategoryItem category)
	{
		this.category=category;
	}
	public CategoryItem getCategory()
	{
		return category;
	}
	public void setPrice(double price)
	{
		this.price=price;
	}
	public double getPrice()
	{
		return price;
	}
	public void setWeight(double weight)
	{
		this.weight=weight;
	}
	public double getWeight()
	{
		return weight;
	}
	public void setDescription(String description)
	{
		this.description=description;
	}
    public String getDescription()
    {
    	return description;
    }
    public void setImageName(String name)
    {
    	this.imageName=name;
    }
    public String getImageName()
    {
    	return imageName;
    }
}
