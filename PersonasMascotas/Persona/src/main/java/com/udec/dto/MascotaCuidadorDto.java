package com.udec.dto;

import java.io.Serializable;

public class MascotaCuidadorDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private MascotaDto mascota;
	
	private CuidadorDto cuidador;
	
	private String infoAdicional;

	public MascotaDto getMascota() {
		return mascota;
	}

	public void setMascota(MascotaDto mascota) {
		this.mascota = mascota;
	}

	public CuidadorDto getCuidador() {
		return cuidador;
	}

	public void setCuidador(CuidadorDto cuidador) {
		this.cuidador = cuidador;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}
}
