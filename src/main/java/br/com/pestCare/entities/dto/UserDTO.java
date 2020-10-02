package br.com.pestCare.entities.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.pestCare.entities.User;

public class UserDTO {

	private Long id;
	private String name;
	private String email;

	public UserDTO(User user ) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public static List<UserDTO> convert(List<User> list) {
		return list.stream().map(UserDTO:: new).collect(Collectors.toList());
	}
	
	public static Page<UserDTO> convertPagination(Page<User> list) {
		return list.map(UserDTO::new);
	}


}
