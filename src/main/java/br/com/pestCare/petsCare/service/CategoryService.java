package br.com.pestCare.petsCare.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pestCare.petsCare.entities.Category;
import br.com.pestCare.petsCare.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public List<Category> findAll() {
		return categoryRepository.findAll();
	}

	public Category findById(Long id) {
		Optional<Category> opCategory = categoryRepository.findById(id);
		return opCategory.get();

	}
}
