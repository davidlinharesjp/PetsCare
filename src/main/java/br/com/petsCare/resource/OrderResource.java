package br.com.petsCare.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petsCare.entities.Order;
import br.com.petsCare.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@RequestMapping(value="/findAll")
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {

		Order order = orderService.findById(id);
		return ResponseEntity.ok().body(order);
	}

}
