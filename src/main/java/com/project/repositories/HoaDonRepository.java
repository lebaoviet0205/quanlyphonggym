package com.project.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.HoaDon;

public interface HoaDonRepository extends CrudRepository<HoaDon, String> {
	@Query("SELECT c FROM HoaDon c where c.MaHD = :maHD")
	public HoaDon findByMaHD(@Param("maHD") String maHD);
	
	@Query("SELECT c FROM HoaDon c where (c.NgayLap between :from and :to)")
	public List<HoaDon> findFromDateToDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
