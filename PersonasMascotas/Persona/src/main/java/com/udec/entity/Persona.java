package com.udec.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "persona")
public class Persona implements Serializable{
	
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
	
	@Column(name = "documento", nullable = false)
	@Min(value = 1000000,message = "El campo documento debe tener un digito minimo de 8 caracteres")
	private Integer documento;
	
	@Column(name = "edad", nullable = false)
	@Min(value= 18, message = "El campo edad debe ser mayor a 18")
	private Integer edad;
	
	@Column(name = "correo", nullable = false)
	@Size(min=5, max=40)
	@Email(message = "digite un correo valido")
	private String correo;
	
	@NotNull(message = "El campo fecha es requerido")
	@Column(name = "fecha_nacimiento", nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	private LocalDate fechaNacimiento;
	
	@NotNull(message = "Nick es requerido")
	@Size(min =3, max =24, message = "Longitud de nick fuera de rago")
	@Column(name = "nick", unique = true ,nullable = false)
	private String nick;
	
	@NotNull(message = "Clave es requerido")
	@Column(columnDefinition = "TEXT", name = "clave", unique = true, nullable = false)
	private String clave;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Mascota> mascotas;
	
	@NotNull(message = "Direccion es obligatorio")
	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Direccion direccion;
		
	@ManyToOne
	@JoinColumn(name = "idRol", nullable = false, foreignKey = @ForeignKey(name = "FK_rol"))
	private Rol rol;

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

	public Integer getDocumento() {
		return documento;
	}

	public void setDocumento(Integer documento) {
		this.documento = documento;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<Mascota> getMascotas() {
		return mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}
}