package br.com.petsCare.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.petsCare.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	@Query("SELECT t FROM User t WHERE t.name LIKE %:expression% OR t.phone LIKE %:expression% OR t.email LIKE %:expression% ")
	List<User> searchExpressionUsers(@Param("expression") String expression);
	
	Page<User> findByNameContainsIgnoreCase(String name,Pageable pagination);
	
	Optional<User> findByEmail(String email);
	

}
