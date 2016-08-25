package com.niit.shoppingcart.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.UserDetailsDAO;


@Controller
public class HomeController<UserDetails, CategoryDAO, UserDAO, Category> {
	
	private static final com.niit.shoppingcart.model.UserDetails UserDetails = null;

	Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	UserDetails userDetails;

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private UserDetailsDAO userDAO;

	@Autowired
	private Category category;

	private UserDetailsDAO userDetailsDAO;

	//     ${categoryList}
	@RequestMapping("/")
	public ModelAndView onLoad(HttpSession session) {
		log.debug("Starting of the method onLoad");
		ModelAndView mv = new ModelAndView("/home");
		session.setAttribute("category", category);
		session.setAttribute("categoryList", categoryDAO);
		log.debug("Ending of the method onLoad");
		return mv;
	}
	
  // ${successMessage}
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute UserDetails userDetails) {
		userDetailsDAO.saveOrUpdate(UserDetails);
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("succesmessage","you are succesfully register");
		
		return mv;
		
	}

	@RequestMapping("/registerHere")
	public ModelAndView registerHere() {
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("userDetails", userDetails);
		mv.addObject("isUserClickedRegisterHere", "true");
		return mv;
	}

	@RequestMapping("/loginHere")
	public ModelAndView loginHere() {
		ModelAndView mv = new ModelAndView("/home");
		mv.addObject("userDetails", new com.niit.shoppingcart.model.UserDetails());
		mv.addObject("isUserClickedLoginHere", "true");
		return mv;
	}



}
