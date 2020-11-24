package com.udec.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.udec.entity.Mascota;
import com.udec.entity.Persona;
import com.udec.view.PersonasDireccionMascotas;



public interface IPersonaService extends AbstractFacade<Persona, Integer>{

	public List<Persona> listarTodos();
	//public Page<Persona> listarPaginado(int page, int size);
	//public Persona listar(int id);
	public List<Persona> listarTodosNombre(String nombre);
	public Persona listarPorMascota(String nombreMascota);
	public List<Mascota> listarMascotasPorPersona(int idPersona);	
	//public void guardar (Persona persona);
	//public void eliminar (int id);
	public void eliminar2 (int id);
	//public void editar (Persona persona);
	public Page<PersonasDireccionMascotas> cargarView(int page, int size);
}