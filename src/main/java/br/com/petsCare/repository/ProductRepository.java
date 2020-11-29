package br.com.petsCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petsCare.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByName(String name);
	
	@Query("SELECT p FROM Product p WHERE id = :id")
	Product findByID(@Param("id") Long id);

}
