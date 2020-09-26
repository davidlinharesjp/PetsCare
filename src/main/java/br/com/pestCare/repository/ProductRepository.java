package br.com.pestCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
