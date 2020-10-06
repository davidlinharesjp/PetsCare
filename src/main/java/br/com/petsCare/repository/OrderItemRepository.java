package br.com.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
