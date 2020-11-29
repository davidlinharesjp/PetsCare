package br.com.petsCare.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.petsCare.entities.Product;
import br.com.petsCare.entities.dto.ProductDTO;
import br.com.petsCare.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductResource {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/findAll")
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		return ResponseEntity.ok().body(products);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}

	@PostMapping(value = "/insert")
	public ResponseEntity<Product> insert(@RequestBody Product product) {
		product = productService.insert(product);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/product/{id}").buildAndExpand(product.getId())
				.toUri();
		return ResponseEntity.created(uri).body(product);
	}

	@PutMapping(value = "/insertImg/{id}")
	public ResponseEntity<ProductDTO> insertImg(@PathVariable Long id, @RequestBody MultipartFile img) {
		ProductDTO productDTO = productService.insertImg(img, id);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/product/{id}")
				.buildAndExpand(productDTO.getId()).toUri();
		return ResponseEntity.created(uri).body(productDTO);
	}

	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = "listProductsPagination", allEntries = true)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@CacheEvict(value = "listProductsPagination", allEntries = true)
	public ResponseEntity<Product> update(@RequestBody Product product) {
		product = productService.update(product);
		return ResponseEntity.ok().body(product);
	}

}
