package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.Product;
import br.com.petsCare.repository.ProductRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public Product findById(Long id) {
		Optional<Product> opProduct = productRepository.findById(id);
		return opProduct.get();
	}
	
	public Product insert(Product product ) {
		try {
			return productRepository.save(product);
		}catch (EntityExistsException e) {
			throw new EntityExistsException( product.getName());
		}
	}


	
	public Product update(Product product) {
		try {
			return productRepository.save(product);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(product.getId());
		}
	}
	
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	

}
