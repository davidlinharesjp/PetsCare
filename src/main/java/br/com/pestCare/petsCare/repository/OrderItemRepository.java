package br.com.pestCare.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.petsCare.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
