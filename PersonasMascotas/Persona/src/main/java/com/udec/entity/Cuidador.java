package com.udec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cuidador")
public class Cuidador implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "El campo nombre es obligatorio")
	@Size(min= 3 , max = 15)
	@Column(name = "nombre", nullable = false, length = 15)
	private String nombre;
	
	@NotNull(message = "El campo apellido es obligatorio")
	@Size(min= 3 , max = 15)
	@Column(name = "apellido", nullable = false, length = 15)
	private String apellido;
	
	@NotNull(message = "El campo cargo es obligatorio")
	@Size(min= 3 , max = 15)
	@Column(name = "cargo", nullable = false, length = 15)
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
