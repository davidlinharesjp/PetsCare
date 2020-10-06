package br.com.petsCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petsCare.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
