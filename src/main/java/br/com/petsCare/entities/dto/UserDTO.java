package br.com.petsCare.entities.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.petsCare.entities.Address;
import br.com.petsCare.entities.Pet;
import br.com.petsCare.entities.Profile;
import br.com.petsCare.entities.User;

public class UserDTO {

	private Long id;
	private String name;
	private String email;
	private String cpfCnpf;
	private String phone;
	private Address address;
	private Set<Pet> pets = new HashSet<>();
	private Boolean isAdmin;

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpfCnpf = user.getCpfCnpj();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.pets.addAll(user.getPets());
		checkProfile(user.getProfiles());
	}

	private void checkProfile(List<Profile> profiles) {
		this.isAdmin = profiles.stream().anyMatch(p -> p.getName().equals("ADMIN"));
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

	public String getCpfCnpf() {
		return cpfCnpf;
	}

	public void setCpfCnpf(String cpfCnpf) {
		this.cpfCnpf = cpfCnpf;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public void setPets(Set<Pet> pets) {
		this.pets = pets;
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
