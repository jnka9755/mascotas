package com.udec.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "personas_direccion_mascotas")
public class PersonasDireccionMascotas {	
	
	@Id
	private Integer id;		
	private String nombre;	
	private String apellido;	
	private String correo;	
	private String barrio;	
	private String descripcion;
	@Column(name = "total_mascotas")
	private Integer totalMascotas;
		
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
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getBarrio() {
		return barrio;
	}
	public void setBarrio(String barrio) {
		this.barrio = barrio;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getTotalMascotas() {
		return totalMascotas;
	}
	public void setTotalMascotas(Integer totalMascotas) {
		this.totalMascotas = totalMascotas;
	}
}
