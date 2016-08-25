package com.niit.shoppingcart.controller;


import javax.servlet.http.HttpSession;

import org.hibernate.annotations.RowId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.model.Cart;
import com.niit.shoppingcart.model.Product;

@Controller
public class CartController {

	@Autowired
	private CartDAO cartDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value="/myCart",method = RequestMethod.GET)
	public String myCart(Model model,HttpSession session){
		model.addAttribute("cart",new Cart());
		
		//get the logged-in user id
		String loggedInUserid = (String) session.getAttribute("loggedInUserId");
		//get the cart details based on loge -in user id
		model.addAttribute("cartlist",cartDAO.list(loggedInUserid));
		model.addAttribute("totalAmount",cartDAO.getTotalAmount(loggedInUserid));
		model.addAttribute("displayCart","true");
		return "/home";
	}
	
	//For add and update cart both
	@RequestMapping(value = "/mycart/add/{id}",method=RequestMethod.GET)
	public String addToCart(@PathVariable("id")String id, HttpSession session){
		//get the product based on product id
	
		Product product=productDAO.get(id);
		Cart cart=new Cart();
		cart.setPrice(product.getPrice());
		cart.setProductName(product.getName());
		cart.setQuantity(1);
		String loggedInUserid=(String) session.getAttribute("loggedInUserId");
		
		cart.setUserId(loggedInUserid);
		cart.setStatus('N');  //status is new.once it is dispatched,we can 
								//changed to 'D'
		cartDAO.saveOrUpdate(cart);
		//return "redirects:/views/home.jsp";
		return "redirect:/";
		}
	
	@RequestMapping("mmyCart/edit/{id}")
	public String editCart(@PathVariable("id") String id,Model model,HttpSession session){
	System.out.println("editCart");
	model.addAttribute("cart",this.cartDAO.get(id));
	String loggedInUserid = (String) session.getAttribute("loggedInUserId");
	model.addAttribute("listCarts",this.cartDAO.list(loggedInUserid));
	return "cart";
	
	}
}
