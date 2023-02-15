package com.ecommerce.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping(path={"/","/home"})
	public String homePage()
	{
		return "index";
	}
	@GetMapping("/shop")
	public ModelAndView shoppingCart()
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("shop");
		view.addObject("categories",categoryService.getAllCategory());
		view.addObject("products",productService.getAllProduct());
		return view;
	}
	@GetMapping("/shop/category/{id}")
	public ModelAndView categoryById(@PathVariable int id)
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("shop");
		view.addObject("categories",categoryService.getAllCategory());
		view.addObject("products",productService.getProductsByCategoryId(id));
		return view;
	}
	@GetMapping("/shop/viewproduct/{id}")
	public ModelAndView viewProductDetails(@PathVariable int id)
	{
		ModelAndView view=new ModelAndView();
		view.setViewName("viewProduct");
		view.addObject("product",productService.getById(id));
		return view;
	}

}
