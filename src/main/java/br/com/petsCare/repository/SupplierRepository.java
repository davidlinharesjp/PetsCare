package br.com.petsCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
