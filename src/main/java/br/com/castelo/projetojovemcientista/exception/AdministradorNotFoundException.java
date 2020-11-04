package br.com.castelo.projetojovemcientista.exception;

public class AdministradorNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public AdministradorNotFoundException(Long id) {
		super("Não foi possível encontrar o administrador " + id);
	}

}