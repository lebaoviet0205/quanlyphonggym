package com.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.NhanVien;

public interface NhanVienRepository extends CrudRepository<NhanVien, String> {
	@Query("SELECT c FROM NhanVien c where c.taiKhoan.Username = :userName")
	public NhanVien findByUserName(@Param("userName") String userName);
	
	@Query("SELECT c FROM NhanVien c where c.MaNV = :maNV")
	public NhanVien findByMaNV(@Param("maNV") String maNV);
}
