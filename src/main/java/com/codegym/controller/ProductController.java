package com.codegym.controller;


import com.codegym.model.Product;
import com.codegym.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping
    public ModelAndView listProducts() {
        ModelAndView mav = new ModelAndView("/product/list");
        mav.addObject("products", productService.findAll());
        return mav;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView mav = new ModelAndView("/product/create");
        mav.addObject("product", new Product());
        return mav;
    }

    @PostMapping("/create")
    public ModelAndView createProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView mav = new ModelAndView("/product/create");
        mav.addObject("product", new Product());
        mav.addObject("message", "Product created successfully");
        return mav;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView mav = new ModelAndView("/product/update");
            mav.addObject("product", product.get());
            return mav;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/edit")
    public ModelAndView editProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        ModelAndView mav = new ModelAndView("/product/update");
        mav.addObject("product", product);
        mav.addObject("message", "Product edited successfully");
        return mav;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ModelAndView mav = new ModelAndView("/product/delete");
            mav.addObject("product", product.get());
            return mav;
        } else {
            return new ModelAndView("/error_404");
        }
    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.remove(product.getId());
        return  "redirect:/products";
    }
}
