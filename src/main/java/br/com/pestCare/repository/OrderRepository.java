package br.com.pestCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
