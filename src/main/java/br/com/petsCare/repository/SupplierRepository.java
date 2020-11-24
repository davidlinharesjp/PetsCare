package br.com.petsCare.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petsCare.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

	@Query("SELECT t FROM Supplier t WHERE t.name LIKE %:expression% OR t.phone LIKE %:expression%")
	List<Supplier> searchExpressionSuppliers(@Param("expression") String expression);
	
	Page<Supplier> findByNameContainsIgnoreCase(String name,Pageable pagination);
}
