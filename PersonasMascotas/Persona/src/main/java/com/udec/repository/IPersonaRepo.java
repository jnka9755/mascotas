package com.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udec.entity.Mascota;
import com.udec.entity.Persona;

@Repository
public interface IPersonaRepo extends JpaRepository<Persona, Integer>{

	@Query("SELECT p FROM Persona p WHERE p.documento = :documento")
	public Persona buscarPersonaCedula(Integer documento);
	
	@Query("SELECT p FROM Persona p WHERE p.id <> :id and p.documento = :documento")
	public Persona buscarPersonaIdCedula(Integer id, Integer documento);
	
	@Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre,'%'))")
	public List<Persona> findByNombre(String nombre);
	
	@Query("SELECT COUNT(p) FROM Persona p WHERE p.id= :id")
	public Integer buscaPersonaId(Integer id);
	
	public Persona findByMascotasNombreIgnoreCase(String nombreMascota);	
	
	@Query("SELECT p FROM Persona p JOIN p.mascotas m WHERE m.nombre = :nombreMascota")
	public Persona buscarPorMascota(String nombreMascota);
	
	@Query("SELECT m FROM Persona p JOIN p.mascotas m WHERE m.persona.id = :idPersona")
	public List<Mascota> buscarMascotasPorPersona(int idPersona);
	
	public Persona findByNick(String nick);
}