package br.com.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
