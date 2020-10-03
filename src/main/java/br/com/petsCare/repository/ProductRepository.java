package br.com.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
