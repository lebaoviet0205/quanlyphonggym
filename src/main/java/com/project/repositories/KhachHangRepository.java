package com.project.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.KhachHang;

public interface KhachHangRepository extends CrudRepository<KhachHang, String>{
	@Query("SELECT c FROM KhachHang c where c.MaKH = :maKH")
	public KhachHang findByMaKH(@Param("maKH") String maKH);
	
	@Query("SELECT c FROM KhachHang c where c.NgayDangKy between :from and :to")
	public List<KhachHang> findFromDateToDate(@Param("from") LocalDate from, @Param("to") LocalDate to);
}
