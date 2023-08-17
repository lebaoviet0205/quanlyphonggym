package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.GoiTap;

public interface GoiTapRepository extends CrudRepository<GoiTap, String> {
	@Query("SELECT c FROM GoiTap c where c.MaGoiTap = :maGoiTap")
	public GoiTap findByMaGoiTap(@Param("maGoiTap") String maGoiTap);
}
