package com.niit.shoppingcart.controller;

import java.util.Locale.Category;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Product;


@Controller
public class AdminController {
	
	@Autowired
	private Product product;

	@Autowired
	private Supplier supplier;

	@Autowired
	private Category category;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	
	@Autowired
	private ProductDAO productDAO;

	
	@RequestMapping("/manageCategories")
	public ModelAndView categories() {
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("category", category);
		mv.addObject("isAdminClickedCategories", "true");
		mv.addObject("categoryList", categoryDAO.list());
		return mv;
	}

	@RequestMapping("/manageProducts")
	public ModelAndView suppliers() {
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("product", product);
		mv.addObject("isAdminClickedProducts", "true");
		mv.addObject("productList", productDAO.list());
		return mv;
	}

	@RequestMapping("/manageSuppliers")
	public ModelAndView products() {
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("supplier", supplier);
		mv.addObject("isAdminClickedSuppliers", "true");
		mv.addObject("supplierList", supplierDAO.list());
		return mv;
	}

}
