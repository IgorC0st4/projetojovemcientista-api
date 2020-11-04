package br.com.castelo.projetojovemcientista.exception;

public class NivelNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public NivelNotFoundException(Long id) {
		super("Não foi possível encontrar o nível " + id);
	}

}