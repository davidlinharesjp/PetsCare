package br.com.pestCare.petsCare.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.pestCare.petsCare.entities.User;
import br.com.pestCare.petsCare.entities.dto.UserDTO;
import br.com.pestCare.petsCare.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(value="/findAll")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		List<UserDTO> usersDTO = list.stream().map(UserDTO:: new).collect(Collectors.toList());
		return ResponseEntity.ok().body(usersDTO);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}
	
	
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User user){
		user = userService.insert(user);
		//return ResponseEntity.ok().body(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(user);
	}
	
	@DeleteMapping(value = "/{id}") 
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id , @RequestBody User user){
		user = userService.update(id, user);
		return ResponseEntity.ok().body(user);
	}

}
