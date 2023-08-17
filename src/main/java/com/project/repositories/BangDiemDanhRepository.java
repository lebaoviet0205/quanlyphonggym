package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.BangDiemDanh;
import com.project.entity.ChiTietThe;

public interface BangDiemDanhRepository extends CrudRepository<BangDiemDanh, String> {
	@Query("SELECT c FROM BangDiemDanh c where c.goiTap.MaGoiTap = :maGoiTap and c.the.MaThe = :maThe and c.NgayDangKy = :ngayDangKy and c.NgayDiemDanh = :ngayDiemDanh")
	public BangDiemDanh findBangDiemDanh(@Param("maGoiTap") String maGoiTap,  @Param("maThe") String maThe, @Param("ngayDangKy") String ngayDangKy, @Param("ngayDiemDanh") String ngayDiemDanh);
	
	@Query("SELECT c FROM BangDiemDanh c where c.NgayDiemDanh = :ngayDiemDanh")
	public List<BangDiemDanh> findByNgayDiemDanh(@Param("ngayDiemDanh") String ngayDiemDanh);
	
	@Query("SELECT COUNT(c) FROM BangDiemDanh c where c.goiTap.MaGoiTap = :maGoiTap and c.the.MaThe = :maThe and c.NgayDangKy = :ngayDangKy")
	public int countDiemDanh(@Param("maGoiTap") String maGoiTap,  @Param("maThe") String maThe, @Param("ngayDangKy") String ngayDangKy);
}