package br.com.petsCare.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petsCare.entities.Supplier;
import br.com.petsCare.repository.SupplierRepository;
import br.com.petsCare.service.exception.DatabaseException;
import br.com.petsCare.service.exception.ResourceNotFoundException;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;

	public List<Supplier> findAll() {
		return supplierRepository.findAll();
	}

	public Page<Supplier> findAllPagination(Pageable pagination, String searchExpression) {
		return supplierRepository.findByNameContainsIgnoreCase(searchExpression, pagination);
	}

	public Supplier findById(Long id) {
		Optional<Supplier> opSupplier = supplierRepository.findById(id);
		return opSupplier.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Supplier getOne(long id) {
		try {
			return supplierRepository.getOne(id);
		} catch (ResourceNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	};

	public void delete(Long id) {
		try {
			supplierRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Supplier update(Long id, Supplier newSupplier) {
		try {
			Supplier supplier = supplierRepository.getOne(id);
			updateDate(supplier, newSupplier);
			return supplierRepository.save(supplier);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	}

	private void updateDate(Supplier supplier, Supplier newSupplier) {
		supplier.setName(newSupplier.getName());
		supplier.setCompanyName(newSupplier.getCompanyName());
		supplier.setPhone(newSupplier.getPhone());
		supplier.setCpfCnpj(newSupplier.getCpfCnpj());
		supplier.setAddress(newSupplier.getAddress());

	}
}
