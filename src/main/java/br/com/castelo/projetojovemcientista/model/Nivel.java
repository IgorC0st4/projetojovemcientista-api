package br.com.castelo.projetojovemcientista.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Nivel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numero;
	@Column(name = "parametro_inferior")
	private String parametroInferior;
	@Column(name = "parametro_superior")
	private String parametroSuperior;

	public Nivel() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getParametroInferior() {
		return parametroInferior;
	}

	public void setParametroInferior(String parametroInferior) {
		this.parametroInferior = parametroInferior;
	}

	public String getParametroSuperior() {
		return parametroSuperior;
	}

	public void setParametroSuperior(String parametroSuperior) {
		this.parametroSuperior = parametroSuperior;
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
		Nivel other = (Nivel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Nivel [id=" + id + ", parametroInferior=" + parametroInferior + ", parametroSuperior="
				+ parametroSuperior + "]";
	}

}
