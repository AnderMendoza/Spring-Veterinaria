package com.certus.spring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.certus.spring.models.Mascota;



@Repository
public interface MascotaDAO extends CrudRepository<Mascota, Integer> {
	
}
