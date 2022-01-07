package com.rik;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	@GetMapping("/list")
	public String viewOrder(Model model) {
		List<Order> listOrders = service.listAll();
		model.addAttribute("listOrders", listOrders);
		return "listOrder";
	}
	
	@RequestMapping("/new")
	public String showNewProductPage(Model model) {
	    Order order = new Order();
	    model.addAttribute("order", order);	    	    	    
	    return "addOrder";
	}
	
	@RequestMapping("/save")
	public String saveOrder(@ModelAttribute("order") Order order) {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
	    order.setCreateAt(ts);
		service.save(order);
		return "redirect:/list";
	}
	
	@RequestMapping("/edit/{id}")
	public ModelAndView showEditProductPage(@PathVariable(name = "id") int id) {
	    ModelAndView mav = new ModelAndView("editOrder");
	    Order order = service.get(id);
	    mav.addObject("order", order);
	     
	    return mav;
	}
	
	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name = "id") int id) {
	    service.delete(id);
	    return "redirect:/list";       
	}
	
	@RequestMapping("/order")
	public String getOrderById(@RequestParam(name = "id") int id, Model model) {
		List<Order> listOrders = service.getOrderById(id);
		model.addAttribute("listOrders", listOrders);
		return "listOrder";
	}
}
