package com.niit.shoppingcart.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.model.Category;

public class CategoryController{
	
	@Autowired
	private Category category;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String listCategories(Model model){
		
		model.addAttribute("category",category);
		model.addAttribute("categoryList",this.categoryDAO.list());
		return "category";
	}
	@RequestMapping(value = "/category/add",method = RequestMethod.POST)
	public String addCategory(@ModelAttribute("category")Category category){
categoryDAO	.saveOrUpdate(category);
	return "category";
	}
	@RequestMapping("category/remove/{id}")
	public String deleteCategory(@PathVariable("id") String id, Model model){
	category	 = categoryDAO .get(id);
	return "category";
	}
}
  