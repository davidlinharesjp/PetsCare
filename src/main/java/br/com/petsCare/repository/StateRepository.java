package br.com.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.State;

public interface StateRepository extends JpaRepository<State, Long> {

}
