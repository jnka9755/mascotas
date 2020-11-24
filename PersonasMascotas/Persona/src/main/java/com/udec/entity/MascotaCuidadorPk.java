package com.udec.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class MascotaCuidadorPk implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "id_mascota", nullable = false)
	private Mascota mascota;
	
	@ManyToOne
	@JoinColumn(name = "id_cuidador", nullable = false)
	private Cuidador cuidador;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuidador == null) ? 0 : cuidador.hashCode());
		result = prime * result + ((mascota == null) ? 0 : mascota.hashCode());
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
		MascotaCuidadorPk other = (MascotaCuidadorPk) obj;
		if (cuidador == null) {
			if (other.cuidador != null)
				return false;
		} else if (!cuidador.equals(other.cuidador))
			return false;
		if (mascota == null) {
			if (other.mascota != null)
				return false;
		} else if (!mascota.equals(other.mascota))
			return false;
		return true;
	}
}
