package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.PhanQuyen;

public interface PhanQuyenRepository extends CrudRepository<PhanQuyen, String> {
	@Query("SELECT c FROM PhanQuyen c where c.MaQuyen = :maQuyen")
	public PhanQuyen findByMaQuyen(@Param("maQuyen") String maQuyen);
}
