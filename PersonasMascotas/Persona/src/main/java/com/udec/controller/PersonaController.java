package com.udec.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udec.entity.Mascota;
import com.udec.entity.Persona;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;
import com.udec.service.IPersonaService;
import com.udec.view.PersonasDireccionMascotas;

@PreAuthorize("hasAuthority('Administrador')")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService service;

	@GetMapping("/buscar")
	public ResponseEntity<List<Persona>> buscar() {
		
		List<Persona> listaPersona = service.listarTodos();
		return new ResponseEntity<List<Persona>>(listaPersona, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{page}/{size}")
	public ResponseEntity<Page<Persona>> buscarPaginado(@PathVariable int page, @PathVariable int size) {
		
		Page<Persona> listaPersona = service.listarPaginado(page, size);
		return new ResponseEntity<Page<Persona>>(listaPersona, HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Persona> buscar(@PathVariable (required = true)  int id) throws NotFoundModelException{
		
		Persona persona = service.listar(id);
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@GetMapping("/buscar/{nombre}")
	public ResponseEntity<List<Persona>> buscar(@PathVariable String nombre) {
		
		List<Persona> listaPersona = service.listarTodosNombre(nombre);
		return new ResponseEntity<List<Persona>>(listaPersona, HttpStatus.OK);
	}
	
	@GetMapping("/buscarPorMascota/{nombreMascota}")
	public ResponseEntity<Persona> buscarPorMascota(@PathVariable String nombreMascota) {
		
		Persona persona = service.listarPorMascota(nombreMascota);
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@GetMapping("/buscarMascotaPersona/{idPersona}")
	public ResponseEntity<List<Mascota>> buscarMascotasPersona (@PathVariable int idPersona){
		
		List<Mascota> mascotas = service.listarMascotasPorPersona(idPersona);
		return new ResponseEntity<List<Mascota>>(mascotas, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> guargar (@Valid @RequestBody Persona persona) throws FoundModelException{
	
		service.guardar(persona);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable (required = true) int id) throws NotFoundModelException{
		
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);	
	}
	
	@DeleteMapping("/eliminar2/{id}")
	public ResponseEntity<Object> eliminar2(@PathVariable (required = true) int id) throws NotFoundModelException{
		
		service.eliminar2(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);	
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Persona persona) throws NotFoundModelException{
		
		service.editar(persona);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@GetMapping("/view/{page}/{size}")
	public ResponseEntity<Page<PersonasDireccionMascotas>> cargarVista (@PathVariable int page, @PathVariable int size){
		
		Page<PersonasDireccionMascotas> listaPersona = service.cargarView(page, size);
		return new ResponseEntity<Page<PersonasDireccionMascotas>>(listaPersona, HttpStatus.OK);
	}
}