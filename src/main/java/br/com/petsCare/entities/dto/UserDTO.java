package br.com.petsCare.entities.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.petsCare.entities.Profile;
import br.com.petsCare.entities.User;

public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private Boolean isAdmin;

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		checkProfile(user.getProfiles());
	}

	private void checkProfile(List<Profile> profiles) {
		if (profiles != null) {
			this.isAdmin = profiles.stream().anyMatch(p -> p.getName().equals("ADMIN"));
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public static List<UserDTO> convert(List<User> list) {
		return list.stream().map(UserDTO::new).collect(Collectors.toList());
	}

	public static Page<UserDTO> convertPagination(Page<User> list) {
		return list.map(UserDTO::new);
	}

}
