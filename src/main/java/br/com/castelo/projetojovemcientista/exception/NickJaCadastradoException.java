package br.com.castelo.projetojovemcientista.exception;

public class NickJaCadastradoException  extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public NickJaCadastradoException(String nick) {
		super("Já há um registro com o nick " + nick);
	}

}
