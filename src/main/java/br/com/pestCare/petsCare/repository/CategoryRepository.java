package br.com.pestCare.petsCare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.petsCare.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
