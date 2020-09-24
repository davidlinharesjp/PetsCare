package br.com.pestCare.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.petsCare.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
