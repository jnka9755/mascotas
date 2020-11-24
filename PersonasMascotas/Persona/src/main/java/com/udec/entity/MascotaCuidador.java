package com.udec.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "mascota_cuidador")
@IdClass(MascotaCuidadorPk.class)
public class MascotaCuidador {

	@Id
	private Mascota mascota;
	
	@Id
	private Cuidador cuidador;
	
	@Column(name = "info_adicional", length = 50)
	private String infoAdicional;

	public Mascota getMascota() {
		return mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

	public Cuidador getCuidador() {
		return cuidador;
	}

	public void setCuidador(Cuidador cuidador) {
		this.cuidador = cuidador;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

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
		MascotaCuidador other = (MascotaCuidador) obj;
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
