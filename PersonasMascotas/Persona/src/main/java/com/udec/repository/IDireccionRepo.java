package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.Direccion;

@Repository
public interface IDireccionRepo extends JpaRepository<Direccion, Integer>{

	@Modifying
	@Transactional
	@Query(value="UPDATE direccion d SET barrio = ?, descripcion = ? WHERE persona_id= ?", nativeQuery = true)
	public void editarDireccion(String descripcion, String barrio, Integer id_persona);
	
	@Query("SELECT COUNT(d) FROM Direccion d WHERE d.persona.id = :id_persona ")
	public int buscarPersonaId(int id_persona);
}
