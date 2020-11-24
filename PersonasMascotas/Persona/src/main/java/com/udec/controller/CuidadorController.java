package com.udec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.udec.entity.Cuidador;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;
import com.udec.service.ICuidadorService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/cuidadores")
public class CuidadorController {

	@Autowired
	private ICuidadorService service;
	
	@GetMapping("/buscar/{page}/{size}")
	public ResponseEntity<Page<Cuidador>> buscarPaginado(@PathVariable int page, @PathVariable int size) {
		
		Page<Cuidador> listaCuidadores = service.listarPaginado(page, size);
		return new ResponseEntity<Page<Cuidador>>(listaCuidadores, HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Cuidador> buscar(@PathVariable (required = true)  int id) throws NotFoundModelException{
		
		Cuidador cuidador = service.listar(id);
		return new ResponseEntity<Cuidador>(cuidador, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> guargar (@Valid @RequestBody Cuidador cuidador) throws FoundModelException{
	
		service.guardar(cuidador);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
		
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable (required = true) int id) throws NotFoundModelException{
		
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);	
	}
	

	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Cuidador cuidador) throws NotFoundModelException{
		
		service.editar(cuidador);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
