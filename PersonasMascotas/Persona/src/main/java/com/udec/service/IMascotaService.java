package com.udec.service;

import java.util.List;

import com.udec.dto.MascotaCuidadorDto;
import com.udec.entity.Mascota;
import com.udec.entity.MascotaCuidador;

public interface IMascotaService extends AbstractFacade<Mascota, Integer>{

	public void guardarMascotaCuidador(MascotaCuidadorDto mascotaCuidador);
	
	public List<MascotaCuidador> listarMascotaCuidador (Integer idMascota);
}
