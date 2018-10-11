package com.apap.tutorial5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial5.model.PilotModel;

/**
 * 
 * @author redhita
 * PilotDB
 */
@Repository
public interface PilotDb extends JpaRepository<PilotModel,Long>{
	PilotModel findByLicenseNumber(String licenseNumber);

}
