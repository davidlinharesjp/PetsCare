package br.com.pestCare.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pestCare.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
