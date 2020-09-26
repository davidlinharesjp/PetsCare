package br.com.pestCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
