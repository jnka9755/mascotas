package com.udec.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.udec.entity.Direccion;
import com.udec.exception.NotFoundModelException;
import com.udec.service.IDireccionService;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/direccion")
public class DireccionController {

	@Autowired
	private IDireccionService service;
	
	@PutMapping("/editar")
	public ResponseEntity<Object> editar(@Valid @RequestBody Direccion direccion) throws NotFoundModelException{
		
		service.editar2(direccion);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
