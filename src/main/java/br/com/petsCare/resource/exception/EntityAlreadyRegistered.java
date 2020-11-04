package br.com.petsCare.resource.exception;

public class EntityAlreadyRegistered extends Exception{

	private static final long serialVersionUID = 1L;
	
	public EntityAlreadyRegistered (String msg) {
		super("Cadastro já existente: " + msg);
	}


}
