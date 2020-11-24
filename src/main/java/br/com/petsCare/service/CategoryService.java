package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.Category;
import br.com.petsCare.repository.CategoryRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

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
	
	public Category insert(Category category) {
		return categoryRepository.save(category);
	}

	public void delete(Long id) {
		try {
			categoryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public Category update(Category category) {
		try {
			return categoryRepository.save(category);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(category.getId());
		}
	}
}
