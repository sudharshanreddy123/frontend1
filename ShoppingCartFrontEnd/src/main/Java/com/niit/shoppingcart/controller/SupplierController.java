package com.niit.shoppingcart.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.model.Supplier;



public class SupplierController {
	private static Logger log = LoggerFactory.getLogger(SupplierController.class);
	@Autowired
	private Supplier supplier;
	@Autowired
	private SupplierDAO supplierDAO;

	@RequestMapping(value = "/suppliers", method = RequestMethod.GET)
	public String liStSuppliers(Model model) {
		log.debug("Starting of the method");
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierList", this.supplierDAO.list());
		return "category";
	}

	@RequestMapping(value = "/supplier/add", method = RequestMethod.POST)
	public String addSupplier(@ModelAttribute("supplier") Supplier supplier){
	supplierDAO.saveOrUpdate(supplier);
		return "supplier";
	}
	
	@RequestMapping("supplier/remove/{id}")
	public String deleteSupplier(@PathVariable("id") String id, ModelMap model) throws Exception{
	
	supplierDAO.delete(id);
	return "supplier";
	}
	@RequestMapping("supplier/edit/{id}")
	public String editSupplier(@PathVariable("id") String id,Model model){
		supplier= supplierDAO.get(id);
		model.addAttribute("supplier", supplier);
		model.addAttribute("supplierList", supplierDAO.list());
		return "supplier";
	}
}