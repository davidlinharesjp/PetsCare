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

import br.com.petsCare.entities.Pet;
import br.com.petsCare.service.PetService;

@RestController
@RequestMapping(value = "/pets")
public class PetResource {

	@Autowired
	private PetService petService;

	@RequestMapping(value = "/findAll")
	@GetMapping
	public ResponseEntity<List<Pet>> findAll() {
		List<Pet> supliList = petService.findAll();
		return ResponseEntity.ok().body(supliList);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pet> findById(@PathVariable Long id) {
		Pet pet = petService.findById(id);
		return ResponseEntity.ok().body(pet);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Pet> update(@PathVariable Long id, @RequestBody Pet pet) {
		pet = petService.update(id, pet);
		return ResponseEntity.ok().body(pet);
	}

	@PostMapping
	public ResponseEntity<Pet> insert(@RequestBody Pet pet) {
		pet = petService.insert(pet);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/pet/{id}").buildAndExpand(pet.getId())
				.toUri();
		return ResponseEntity.created(uri).body(pet);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		petService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
