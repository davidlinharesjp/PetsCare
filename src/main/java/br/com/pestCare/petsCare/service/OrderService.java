package br.com.pestCare.petsCare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pestCare.petsCare.entities.Order;
import br.com.pestCare.petsCare.repository.OrderRepository;

@Service
public class OrderService  {

	@Autowired
	private OrderRepository orderRepository;
	
	public  List<Order> findAll(){
		return  orderRepository.findAll();
	}
	
	
	public Order findById(Long id) {
		Optional<Order> opOrder = orderRepository.findById(id);
		return opOrder.get();
	}
}
