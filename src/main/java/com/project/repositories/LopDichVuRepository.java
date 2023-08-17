package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.LopDichVu;

public interface LopDichVuRepository extends CrudRepository<LopDichVu, String> {
	@Query("SELECT c FROM LopDichVu c where c.MaLop = :classId")
	public LopDichVu findByClassId(@Param("classId") String classId);
}
