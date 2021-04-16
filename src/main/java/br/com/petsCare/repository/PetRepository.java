package br.com.petsCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petsCare.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	@Query("SELECT s FROM Pet s WHERE s.id = :id")
	Pet findByID (@Param("id") Long id);

}
