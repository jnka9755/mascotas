package com.udec.dto;

import java.io.Serializable;

public class CuidadorDto implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String nombre;
	
	private String apellido;
	
	private String cargo;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
