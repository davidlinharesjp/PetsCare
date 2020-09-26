package br.com.pestCare.entities.dto;

import br.com.pestCare.entities.User;

public class UserDTO {

	private Long id;
	private String nome;
	private String email;

	public UserDTO(User user ) {
		this.id = user.getId();
		this.nome = user.getName();
		this.email = user.getEmail();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

}
