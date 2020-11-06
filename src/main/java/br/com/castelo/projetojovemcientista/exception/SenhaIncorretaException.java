package br.com.castelo.projetojovemcientista.exception;

public class SenhaIncorretaException  extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public SenhaIncorretaException() {
		super("Senha incorreta");
	}

}