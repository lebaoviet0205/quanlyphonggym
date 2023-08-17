package com.project.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.project.entity.TaiKhoan;

public interface TaiKhoanRepository extends CrudRepository<TaiKhoan, String> {
	@Query("SELECT c FROM TaiKhoan c where lower(c.Username) = lower(:userName)")
	public TaiKhoan findByUserName(@Param("userName") String userName);
}
