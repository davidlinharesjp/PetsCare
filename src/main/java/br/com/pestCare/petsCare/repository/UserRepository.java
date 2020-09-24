package br.com.pestCare.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.petsCare.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
