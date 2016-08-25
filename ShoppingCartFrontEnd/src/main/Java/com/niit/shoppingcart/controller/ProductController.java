package com.niit.shoppingcart.controller;

import org.h2.store.fs.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Category;
import com.niit.shoppingcart.model.Product;
import com.niit.shoppingcart.model.Supplier;
import com.niit.shoppingcart.util.FileUtil;



@Controller
public class ProductController {

	@Autowired(required = true)
	private ProductDAO productDAO;
	
	@Autowired(required =true)
	private CategoryDAO categoryDAO;
	
	@Autowired(required = true)
	private SupplierDAO supplierDAO;
	
	private String path = "C:\\Users\\lenovo\\Desktop\\sample project 1\\front page";
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public String listProducts(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("category", new Category());
		model.addAttribute("supplier", new Supplier());
		model.addAttribute("productList", this.productDAO.list());
		return "product";
	}
	
	// For add and update product both
	@RequestMapping(value = "/product/add", method = RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product) {
	
		Category category = categoryDAO.getByName(( (Category) product.getCategory()).getName());
		// supplierDAO.saveOrUpdate(category): // why to save??
		
		Supplier supplier = supplierDAO.getByName((  (Supplier) product.getSupplier()).getName());
		// supplierDAO.saveOrUpdate(supplier): // Why to save??
		
		product.setCategory(category);
		product.setSupplier(supplier);
		
		product.setCategory_id(category.getId());
		product.setSupplier_id(supplier.getId());
		productDAO.saveOrUpdate(product);
		
		MultipartFile file = (MultipartFile) product.getImage();
		
		FileUtil.upload(path, file,product.getId()+".jpg");
		return"redirect:/products";
		
		
		
	
	
	}
	
}
