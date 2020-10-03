package br.com.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
