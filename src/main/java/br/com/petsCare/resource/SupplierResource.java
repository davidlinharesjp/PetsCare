package br.com.petsCare.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.petsCare.entities.Supplier;
import br.com.petsCare.entities.dto.SupplierDTO;
import br.com.petsCare.service.SupplierService;

@RestController
@RequestMapping(value = "/suplier")
public class SupplierResource {

	@Autowired
	private SupplierService supplierService;

	@RequestMapping(value = "/findAll")
	@GetMapping
	public ResponseEntity<List<Supplier>> findAll() {
		List<Supplier> supliList = supplierService.findAll();
		return ResponseEntity.ok().body(supliList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Supplier> findById(@PathVariable Long id) {
		Supplier supplier = supplierService.findById(id);
		return ResponseEntity.ok().body(supplier);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Supplier> update(@PathVariable Long id, @RequestBody Supplier supplier) {
		supplier = supplierService.update(id, supplier);
		return ResponseEntity.ok().body(supplier);
	}

	@PostMapping
	public ResponseEntity<SupplierDTO> insert(@RequestBody Supplier supplier) {
		supplier = supplierService.insert(supplier);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/supplier/{id}").buildAndExpand(supplier.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new SupplierDTO(supplier));
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		supplierService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
