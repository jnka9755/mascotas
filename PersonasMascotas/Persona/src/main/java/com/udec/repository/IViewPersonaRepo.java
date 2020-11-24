package com.udec.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.udec.view.PersonasDireccionMascotas;

@Repository
public interface IViewPersonaRepo extends JpaRepository<PersonasDireccionMascotas, Integer>{

	@Query(value="SELECT * FROM personas_direccion_mascotas", nativeQuery = true)
	public Page<PersonasDireccionMascotas> listarView(Pageable pageable);
}
