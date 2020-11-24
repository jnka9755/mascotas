package com.udec.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.Mascota;
import com.udec.entity.Persona;
import com.udec.entity.Rol;
import com.udec.exception.ArgumentRequiredException;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;
import com.udec.repository.IPersonaRepo;
import com.udec.repository.IViewPersonaRepo;
import com.udec.service.IPersonaService;
import com.udec.view.PersonasDireccionMascotas;

@Service
public class PersonaServiceImp implements IPersonaService, UserDetailsService {

	@Autowired
	private IPersonaRepo repo;
	@Autowired
	private IViewPersonaRepo repoView;
	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Override
	public List<Persona> listarTodos() {

		List<Persona> personas = repo.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
		return personas;
	}

	@Override
	public List<Persona> listarTodosNombre(String nombre) {

		List<Persona> personas = repo.findByNombre(nombre);
		return personas;
	}

	@Override
	public List<Mascota> listarMascotasPorPersona(int idPersona) {
		List<Mascota> mascotas = repo.buscarMascotasPorPersona(idPersona);
		return mascotas;
	}

	@Override
	public Persona listarPorMascota(String nombreMascota) {

		Persona persona = repo.buscarPorMascota(nombreMascota);
		if (persona == null)
			throw new NotFoundModelException("Persona no encontrada");
		return persona;
	}

	@Transactional
	@Override
	public void guardar(Persona persona) {

		Persona per = repo.buscarPersonaCedula(persona.getDocumento());
		Rol rol = new Rol();
		rol.setId(2);
		if (per == null) {

			if (persona.getMascotas() != null) {
				for (Mascota mascota : persona.getMascotas()) {
					mascota.setPersona(persona);
				}
			}
			persona.setClave(bcrypt.encode(persona.getClave()));
			persona.getDireccion().setPersona(persona);
			persona.setRol(rol);
			
			repo.save(persona);
		} else {
			throw new FoundModelException("Esta persona ya se encuentra registrada");
		}
	}

	@Override
	public void eliminar2(int id) {

		Persona persona = repo.findById(id).orElseThrow(() -> new NotFoundModelException("Persona no encontrada"));
		if (persona.getMascotas().isEmpty()) {
			repo.deleteById(id);
		} else {
			throw new FoundModelException("Debe eliminar primero los registros de las mascotas de esta persona");
		}
	}

	@Transactional
	@Override
	public void editar(Persona dtoPersona) {

		if (dtoPersona.getId() == null) {
			throw new ArgumentRequiredException("id Persona es requerido");
		}
		Persona persona = repo.findById(dtoPersona.getId())
				.orElseThrow(() -> new NotFoundModelException("Persona no encontrada"));
		Persona per = repo.buscarPersonaIdCedula(dtoPersona.getId(), dtoPersona.getDocumento());
		if (per != null) {
			throw new FoundModelException("Este documento ya se encuentra registrado");
		}

		persona.setNombre(dtoPersona.getNombre());
		persona.setApellido(dtoPersona.getApellido());
		persona.setDocumento(dtoPersona.getDocumento());
		persona.setEdad(dtoPersona.getEdad());
		persona.setCorreo(dtoPersona.getCorreo());
		persona.setFechaNacimiento(dtoPersona.getFechaNacimiento());
		persona.setNick(dtoPersona.getNick());
		persona.setClave(dtoPersona.getClave());
		persona.setEstado(dtoPersona.isEstado());
		persona.setDireccion(dtoPersona.getDireccion());
		persona.getDireccion().setId(dtoPersona.getId());;
		

		repo.save(persona);
	}

	@Override
	public Page<Persona> listarPaginado(Integer page, Integer size) {

		return repo.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "nombre").ascending()));
	}

	@Override
	public Persona listar(Integer id) {

		Persona persona = repo.findById(id).orElseThrow(() -> new NotFoundModelException("Persona no encontrada"));
		return persona;
	}

	@Override
	public void eliminar(Integer id) {

		this.listar(id);
		repo.deleteById(id);
	}

	@Override
	public Page<PersonasDireccionMascotas> cargarView(int page, int size) {
		
		return repoView.listarView(PageRequest.of(page, size));
	}

	@Override
	public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
		
		Persona persona = repo.findByNick(nick);
		
		if(persona == null)
			throw new NotFoundModelException("Usuario o contraseña incorrectos");		
		if (persona.isEstado() == false)
			throw new FoundModelException("Usuario se escuentra inactivo");
		
		List<GrantedAuthority> roles = new ArrayList<>();
		roles.add(new SimpleGrantedAuthority(persona.getRol().getNombre()));
		
		UserDetails ud = new User(persona.getNick(), persona.getClave(), roles);
		
		return ud;
	}
}