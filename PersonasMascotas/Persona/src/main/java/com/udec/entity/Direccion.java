package com.udec.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table (name= "direccion")
public class Direccion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@NotNull
	@Size(min= 3 , max = 50, message = "La descripción debe ser entre 3 y 50 carácteres")
	@Column(name="descripcion", nullable = false, length = 50)
	private String descripcion; 
	
	@NotNull
	@Size(min= 3 , max = 30, message = "El barrio debe ser entre 3 y 15 carácteres")
	@Column(name="barrio", nullable = false, length = 50)
	private String barrio;
	
	@OneToOne
	@MapsId
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Persona persona;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getBarrio() {
		return barrio;
	}

	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	@JsonIgnore
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	
}
