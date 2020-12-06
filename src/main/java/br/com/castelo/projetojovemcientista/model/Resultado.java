package br.com.castelo.projetojovemcientista.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Resultado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "tempo_final")
	private int tempoFinal;
	private int erros;
	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	@ManyToOne
	@JoinColumn(name = "id_nivel")
	private Nivel nivel;

	public Resultado() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Nivel getNivel() {
		return nivel;
	}

	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	@Override
	public String toString() {
		return "Resultado [id=" + id + ", tempoFinal=" + tempoFinal + ", erros=" + erros + ", usuario=" + usuario
				+ ", nivel=" + nivel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Resultado other = (Resultado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
