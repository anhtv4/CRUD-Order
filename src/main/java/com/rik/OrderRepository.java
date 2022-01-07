package com.rik;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface OrderRepository extends JpaRepository<Order, Long>{
	
	@Query(value = "select o.id, o.create_at, o.total, o.user_id, o.status, od.product_id "
			+ "from order_details od "
			+ "join orders o on od.order_id = o.id "
			+ "where od.product_id = ?1", nativeQuery = true)
	List<Order> getOrderById(long id);
}
