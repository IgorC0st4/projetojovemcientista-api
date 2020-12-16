package br.com.castelo.projetojovemcientista.model;

public class Relatorio {
	private int fase;
	private int parametroInferior;
	private int parametroSuperior;
	private int tempoFinal;
	private int erros;
	private String nick;
	private int idade;
	private int escolaridade;
	private String sexo;

	public Relatorio() {
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public int getParametroInferior() {
		return parametroInferior;
	}

	public void setParametroInferior(int parametroInferior) {
		this.parametroInferior = parametroInferior;
	}

	public int getParametroSuperior() {
		return parametroSuperior;
	}

	public void setParametroSuperior(int parametroSuperior) {
		this.parametroSuperior = parametroSuperior;
	}

	public int getTempoFinal() {
		return tempoFinal;
	}

	public void setTempoFinal(int tempoFinal) {
		this.tempoFinal = tempoFinal;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		this.erros = erros;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public int getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(int escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + fase;
		result = prime * result + ((nick == null) ? 0 : nick.hashCode());
		result = prime * result + tempoFinal;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relatorio other = (Relatorio) obj;
		if (fase != other.fase)
			return false;
		if (nick == null) {
			if (other.nick != null)
				return false;
		} else if (!nick.equals(other.nick))
			return false;
		if (tempoFinal != other.tempoFinal)
			return false;
		return true;
	}

}
