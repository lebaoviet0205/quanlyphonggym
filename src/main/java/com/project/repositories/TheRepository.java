package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.The;

public interface TheRepository extends CrudRepository<The, String> {
	@Query("SELECT c FROM The c where c.MaThe = :maThe")
	public The findByMaThe(@Param("maThe") String maThe);
}
