package com.rik;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
	@Autowired
	private OrderRepository repo;
	
	public List<Order> listAll(){
		return repo.findAll();
	}
	
	public void save(Order order) {
		repo.save(order);
	}
	
	public Order get(long id) {
		return repo.findById(id).get();
	}
	
	public void delete(long id) {
		repo.deleteById(id);
	}
	
	public List<Order> getOrderById(long id) {
		return repo.getOrderById(id);
	}
}
