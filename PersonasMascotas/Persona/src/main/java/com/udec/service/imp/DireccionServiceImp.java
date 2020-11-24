package com.udec.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udec.entity.Direccion;
import com.udec.exception.NotFoundModelException;
import com.udec.repository.IDireccionRepo;
import com.udec.service.IDireccionService;

@Service
public class DireccionServiceImp implements IDireccionService{

	@Autowired
	private IDireccionRepo repo;
	
	@Override
	public void editar(Direccion direccion) {

		if(repo.buscarPersonaId(direccion.getId()) > 0)
			repo.editarDireccion(direccion.getDescripcion(), direccion.getBarrio(), direccion.getId());
		else
			throw new NotFoundModelException("Persona no existe");
	}
	
	@Override
	public void editar2 (Direccion direccion) {
		
		if(repo.buscarPersonaId(direccion.getId()) > 0)
			repo.save(direccion);
		else
			throw new NotFoundModelException("Persona no existe");
	}	
}
