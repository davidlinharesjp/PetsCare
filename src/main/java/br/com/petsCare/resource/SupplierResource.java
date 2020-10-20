package br.com.petsCare.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.petsCare.entities.Supplier;
import br.com.petsCare.service.SupplierService;

@RestController
@RequestMapping(value = "/suplier")
public class SupplierResource {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "/findAll")
	@GetMapping
	public ResponseEntity<List<Supplier>> findAll() {
		List<Supplier> categories = supplierService.findAll();
		return ResponseEntity.ok().body(categories);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Supplier> findById(@PathVariable Long id) {
		Supplier supplier = supplierService.findById( id);
		return ResponseEntity.ok().body(supplier);
	}
}
