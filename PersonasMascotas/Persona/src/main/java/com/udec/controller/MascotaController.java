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

import com.udec.dto.MascotaCuidadorDto;
import com.udec.entity.Mascota;
import com.udec.entity.MascotaCuidador;
import com.udec.exception.FoundModelException;
import com.udec.exception.NotFoundModelException;
import com.udec.service.IMascotaService;

@PreAuthorize("hasAuthority('Usuario')")
@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/mascotas")
public class MascotaController {
	
	@Autowired
	private IMascotaService service;
	
	@GetMapping("/buscar/{page}/{size}")
	public ResponseEntity<Page<Mascota>> buscarPaginado(@PathVariable int page, @PathVariable int size) {
		
		Page<Mascota> listaMascotas = service.listarPaginado(page, size);
		return new ResponseEntity<Page<Mascota>>(listaMascotas, HttpStatus.OK);
	}
	
	@GetMapping("/listar/{id}")
	public ResponseEntity<Mascota> buscar(@PathVariable (required = true)  int id) throws NotFoundModelException{
		
		Mascota mascota = service.listar(id);
		return new ResponseEntity<Mascota>(mascota, HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Object> guargar (@Valid @RequestBody Mascota mascota) throws FoundModelException{
	
		service.guardar(mascota);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@PostMapping("/guardarMascotaCuidador")
	public ResponseEntity<Object> guargar (@RequestBody MascotaCuidadorDto mascotaCuidador) throws FoundModelException{
	
		service.guardarMascotaCuidador(mascotaCuidador);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	@GetMapping("/listarMascotasCuidador/{idMascota}")
	public ResponseEntity<List<MascotaCuidador>> listarMascotaCuidador(@PathVariable (required = true) int idMascota){
		
		List<MascotaCuidador> listaMascotas = service.listarMascotaCuidador(idMascota);
		return new ResponseEntity<List<MascotaCuidador>>(listaMascotas,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object> eliminar(@PathVariable (required = true) int id) throws NotFoundModelException{
		
		service.eliminar(id);
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);	
	}
	

	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Mascota mascota) throws NotFoundModelException{
		
		service.editar(mascota);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
