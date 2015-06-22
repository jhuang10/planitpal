package com.planitpal.service.domain.repository;

import com.planitpal.service.domain.Plan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanitpalRepository extends CrudRepository<Plan, String>{

}

