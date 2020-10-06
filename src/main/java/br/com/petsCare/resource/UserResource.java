package br.com.petsCare.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.petsCare.entities.User;
import br.com.petsCare.entities.dto.UserDTO;
import br.com.petsCare.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/findAll")
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(UserDTO.convert(list));
	}

	@RequestMapping(value = "/findAllPagination")
	@GetMapping
	@Cacheable(value = "listUsersPagination")//USER CACHE EM METODOS QE NÃO SERÃO MUITO ALTERADOS (updates , insert ou delete)
	public ResponseEntity<Page<UserDTO>> findAllPagination(@RequestParam(required = false) String searchExpression,
			@PageableDefault(sort = "id", direction = Direction.DESC,page = 1, size = 10) Pageable pagination) {
		Page<User> pageUsers = null;
		if (searchExpression != null && !searchExpression.equals("")) {
			pageUsers = userService.findAllPagination(pagination, searchExpression);
			return ResponseEntity.ok().body(UserDTO.convertPagination(pageUsers));
		} else {
			pageUsers = userService.findAllPagination(pagination);
			return ResponseEntity.ok().body(UserDTO.convertPagination(pageUsers));
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {

		// User user1 = userService.getOne(id);
		User user = userService.findById(id);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping
	@CacheEvict(value = "listUsersPagination", allEntries = true)
	public ResponseEntity<UserDTO> insert(@RequestBody User user) {
		user = userService.insert(user);
		/*
		 * Pode se usar o Bem validation para verificar se o usuario esta preenchido
		 * 
		 * Com @valid nos parametros recebido e no POJO FORM coloca o @length o @empty
		 * o @NotNull
		 */
		// return ResponseEntity.ok().body(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/user/{id}").buildAndExpand(user.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new UserDTO(user));
	}

	@DeleteMapping(value = "/{id}")
	@CacheEvict(value = "listUsersPagination", allEntries = true)
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		userService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	@CacheEvict(value = "listUsersPagination", allEntries = true)
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
		user = userService.update(id, user);
		return ResponseEntity.ok().body(user);
	}

}