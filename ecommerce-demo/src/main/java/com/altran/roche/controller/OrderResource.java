package com.altran.roche.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.altran.roche.model.Orders;
import com.altran.roche.model.Product;
import com.altran.roche.service.OrderService;

@RestController
public class OrderResource {

	@Autowired
	private OrderService orderService;
	
	@PostMapping("/orders")
	public Double createOrder(@RequestBody List<Product> products,@RequestParam String emailId ) {		
		return orderService.processOrder(products,emailId);
	}
	  
	@GetMapping("/orders")
	public List<Orders> getOrders(@RequestParam("from Date" ) String fromDate, @RequestParam("to Date") String toDate){
		return orderService.searchOrders(fromDate,toDate);
	}
}
