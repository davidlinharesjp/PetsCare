package br.com.petsCare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByState_Initials(String initials);

	

}
