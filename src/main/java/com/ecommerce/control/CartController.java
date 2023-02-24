package com.ecommerce.control;


import com.ecommerce.entity.ProductEntity;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Controller
public class CartController {

    private static ArrayList<ProductEntity> cart=new ArrayList<>();
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("addToCart/{id}")
    public String cardAdding(@PathVariable int id)
    {
       cart.add(productRepository.findById(id).get());
       return "redirect:/shop";
    }


}
