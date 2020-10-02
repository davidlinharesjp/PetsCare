package br.com.pestCare.entities.dto;

public class TokenDTO {

	private String token;
	private String string;

	public TokenDTO(String token, String string) {
		this.token = token;
		this.string = string;
	}

	public String getToken() {
		return token;
	}

	public String getString() {
		return string;
	}


	
}
