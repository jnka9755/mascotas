package com.udec.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.udec.entity.MascotaCuidador;


@Repository
public interface IMascotaCuidadorRepo extends JpaRepository<MascotaCuidador, Integer>{

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO public.mascota_cuidador (info_adicional, id_cuidador, id_mascota)"
			+ " VALUES (:infoAdicional, :idCuidador, :idMascota);", nativeQuery = true)
	public void guardar(@Param("infoAdicional") String infoAdicional, @Param("idCuidador") Integer idCuidador, @Param("idMascota") Integer idMascota);
	
	@Query(value = "SELECT COUNT(*) FROM mascota_cuidador WHERE id_cuidador = :idCuidador AND id_mascota = :idMascota", nativeQuery = true)
	public Integer buscaMascotaCuidador(@Param("idCuidador") Integer idCuidador, @Param("idMascota") Integer idMascota);

	@Query("SELECT mc FROM MascotaCuidador mc WHERE mc.mascota.id = :idMascota")
	public List<MascotaCuidador> listarMascotaCuidador(@Param("idMascota") Integer idMascota);
}