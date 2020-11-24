package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.entity.Cuidador;

@Repository
public interface ICuidadorRepo extends JpaRepository<Cuidador, Integer>{

}
