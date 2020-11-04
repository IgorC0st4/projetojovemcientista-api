package br.com.castelo.projetojovemcientista.exception;

public class ResultadoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ResultadoNotFoundException(Long id) {
		super("Não foi possível encontrar o resultado " + id);
	}

}