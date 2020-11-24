package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Mascota;

@Repository
public interface IMascotaRepo extends JpaRepository<Mascota, Integer>{

}