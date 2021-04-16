package br.com.petsCare.entities.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.petsCare.entities.Address;
import br.com.petsCare.entities.Order;
import br.com.petsCare.entities.Pet;
import br.com.petsCare.entities.Profile;
import br.com.petsCare.entities.User;

public class UserInsertDTO {

	private Long id;

	private String name;

	private String email;

	private String phone;

	private Address address;

	private String cpfCnpj;

	private Boolean isAdmin = false;

	private List<Order> orders;

	private List<Profile> profiles = new ArrayList<>();

	private Set<Pet> pets = new HashSet<>();

	public UserInsertDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.address = user.getAddress();
		this.cpfCnpj = user.getCpfCnpj();
		addProfiles(user.getProfiles());
		checkProfile(user.getProfiles());
		addPets(user.getPets());
	}

	private void addPets(Set<Pet> pets) {
		if(pets != null && !pets.isEmpty() && pets.size() > 0) {
			this.pets.addAll(pets);			
		}
	}

	private void addProfiles(List<Profile> profiles) {
		this.profiles.addAll(profiles);
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public Set<Pet> getPets() {
		return pets;
	}

	public static List<UserInsertDTO> convert(List<User> list) {
		return list.stream().map(UserInsertDTO::new).collect(Collectors.toList());
	}

}
