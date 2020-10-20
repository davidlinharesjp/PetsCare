package br.com.petsCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}
