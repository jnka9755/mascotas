package com.udec.dto;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonaDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	@NotNull(message = "El campo nombre es obligatorio")
	@Size(min= 3 , max = 15, message = "Nombre debe tener 3 y 15 caracteres")
	private String nombre;
	@NotNull(message = "El campo apellido es obligatorio")
	@Size(min= 3 , max = 15, message = "Nombre debe tener 3 y 15 caracteres")
	private String apellido;
	@Min(value = 1000000,message = "El campo documento debe tener un digito minimo de 8 caracteres")
	private int documento;
	@Min(value= 18, message = "El campo edad debe ser mayor a 18")
	private int edad;
		
	public PersonaDto() {
		
	}
	
	public PersonaDto(int id, String nombre, String apellido, int documento, int edad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.edad = edad;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getDocumento() {
		return documento;
	}
	public void setDocumento(int documento) {
		this.documento = documento;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
}
