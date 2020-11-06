package br.com.castelo.projetojovemcientista.exception;

public class UsuarioNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public UsuarioNotFoundException(Long id) {
		super("Não foi possível encontrar o usuário " + id);
	}
	
	public UsuarioNotFoundException(String nick) {
		super("Não foi possível encontrar o usuário " + nick);
	}

}