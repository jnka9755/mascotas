package com.udec.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.udec.entity.Cuidador;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.NotFoundModelException;
import com.udec.repository.ICuidadorRepo;
import com.udec.service.ICuidadorService;

@Service
public class CuidadorServiceImp implements ICuidadorService{

	@Autowired
	private ICuidadorRepo repo; 
	
	@Override
	public Page<Cuidador> listarPaginado(Integer page, Integer size) {
		
		return repo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nombre").ascending()));
	}

	@Override
	public Cuidador listar(Integer id) {

		Cuidador cuidador = repo.findById(id).orElseThrow(() -> new NotFoundModelException("Cuidador no encontrado"));
		return cuidador;
	}

	@Override
	public void guardar(Cuidador entity) {
		
		repo.save(entity);
	}

	@Override
	public void eliminar(Integer id) {
		
		boolean existe = repo.existsById(id); 	
		if(existe)
			repo.deleteById(id);
		else
			throw new NotFoundModelException("Cuidador no encontrado");
	}

	@Override
	public void editar(Cuidador entity) {
		
		if(entity.getId() == null)
			throw new ArgumentRequiredException("id de Cuidador requerido");
		Cuidador cuidador = repo.findById(entity.getId()).orElseThrow(()-> new NotFoundModelException("Cuidador no encontrado"));
		
		cuidador.setNombre(entity.getNombre());
		cuidador.setApellido(entity.getApellido());
		cuidador.setCargo(entity.getCargo());
		
		repo.save(cuidador);			
	}
}
