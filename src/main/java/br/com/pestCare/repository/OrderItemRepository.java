package br.com.pestCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
