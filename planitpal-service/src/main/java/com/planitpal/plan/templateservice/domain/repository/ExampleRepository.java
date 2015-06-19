package com.planitpal.plan.templateservice.domain.repository;

import com.planitpal.plan.templateservice.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends CrudRepository<Example, String> {
}