package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.ChiTietThe;

public interface ChiTietTheRepository extends CrudRepository<ChiTietThe, String>{
	@Query("SELECT c FROM ChiTietThe c where c.goiTap.MaGoiTap = :maGoiTap and c.the.MaThe = :maThe and c.NgayDangKy = :ngayDangKy")
	public ChiTietThe findChiTietThe(@Param("maGoiTap") String maGoiTap,  @Param("maThe") String maThe, @Param("ngayDangKy") String ngayDangKy);
	
	@Query("SELECT c FROM ChiTietThe c where c.goiTap.MaGoiTap = :maGoiTap and c.the.MaThe = :maThe")
	public List<ChiTietThe> findListChiTietThe(@Param("maGoiTap") String maGoiTap,  @Param("maThe") String maThe);
}
