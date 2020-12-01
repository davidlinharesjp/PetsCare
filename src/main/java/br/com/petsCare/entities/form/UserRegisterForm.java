package br.com.petsCare.entities.form;

public class UserRegisterForm {

	private Long id;
	private String name;
	private String email;
	private String key_password;

	public UserRegisterForm(Long id, String name, String email, String key_password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.key_password = key_password;
	}

	public UserRegisterForm() {
		super();
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public String getKey_password() {
		return key_password;
	}

	public void setKey_password(String key_password) {
		this.key_password = key_password;
	}

}
