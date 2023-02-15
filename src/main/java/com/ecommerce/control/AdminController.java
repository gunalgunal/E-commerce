package com.ecommerce.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.dto.ProductDto;
import com.ecommerce.entity.CategoryItem;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@Controller
public class AdminController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	//images save panrathuku path
	private static String uploadDir=System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	
	@GetMapping("/admin")
	public String adminPage()
	{
		return "adminHome";
	}
	// category section
	
	@GetMapping("/admin/categories")
	public ModelAndView categoriesView()
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("categories");
		view.addObject("categories", categoryService.getAllCategory());
		return view;
	}
	
	@GetMapping("/admin/categories/add")
	public ModelAndView addCategories()
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("categoriesAdd");
		view.addObject("category",new CategoryItem());
		return view;
	}
	
	@PostMapping("/admin/categories/add")
	public String saveTheCategories(CategoryItem category)
	{
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public ModelAndView updatePage(@PathVariable int id)
	{
		CategoryItem category=categoryService.getById(id);
		ModelAndView view=new ModelAndView();
		view.setViewName("categoriesAdd");
		view.addObject("category",category);
		return view;
	}
	
	@PostMapping("/admin/categories/update/{id}")
	public String updateTheValue(CategoryItem category)
	{
		categoryService.updateTheItem(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String delete(@PathVariable int id)
	{
		categoryService.deleteCategoryById(id);
		return "redirect:/admin/categories";
	}
	// product section
	
	@GetMapping("/admin/products")
	public ModelAndView productsPage()
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("products");
		view.addObject("products",productService.getAllProduct());
		return view;
	}
	@GetMapping("/admin/products/add")
	public ModelAndView productAddPage()
	{
		ModelAndView view=new ModelAndView();
		view.addObject("productDTO", new ProductDto());
		view.addObject("categories",categoryService.getAllCategory());
		view.setViewName("productsAdd");
		return view;
	}
	@PostMapping("/admin/products/add")
	public String addProduct(ProductDto productDTO,
			@RequestParam("productImage") MultipartFile file,
			@RequestParam("imgName") String imgName
			) throws IOException 
	{
		ProductEntity productEntity=new ProductEntity();
		productEntity.setId(productDTO.getId());
		productEntity.setName(productDTO.getName());
		
		productEntity.setCategory(categoryService.getById(productDTO.getCategoryId()));
		productEntity.setPrice(productDTO.getPrice());
		productEntity.setWeight(productDTO.getWeight());
		productEntity.setDescription(productDTO.getDescription());
		String imageUUID;
		if(!file.isEmpty()) 
		{
			imageUUID=file.getOriginalFilename();
			//image ah namma database la save pannama (/static/productImages) la save panrom
			//so we need to tell the path
			Path filenameAndPath=Paths.get(uploadDir,imageUUID);
		    Files.write(filenameAndPath, file.getBytes());
		}
		else
		{
			imageUUID=imgName;
			//update(/admin/product/update/{id}) la value set panrom(imgName)
		}
			
		productEntity.setImageName(imageUUID);
		productService.saveProduct(productEntity);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/delete/{id}")
	public String deleteTheProduct(@PathVariable int id)
	{
		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}
	@GetMapping("/admin/product/update/{id}")
	public ModelAndView productUpdatePage(@PathVariable int id)
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("productsAdd");
		view.addObject("productDTO",productService.getProductById(id));
		view.addObject("categories",categoryService.getAllCategory());
		return view;
	}

}
