package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.project.entity.BangDiemDanh;
import com.project.entity.ChiTietThe;
import com.project.entity.PhanQuyen;
import com.project.repositories.BangDiemDanhRepository;
import com.project.repositories.PhanQuyenRepository;

@Service
public class BangDiemDanhService {
	@Autowired
	private BangDiemDanhRepository repo;
	
	public List<BangDiemDanh> listAll(){
		return (List<BangDiemDanh>) repo.findAll();
	}
	
	public BangDiemDanh findBangDiemDanh(String maGoiTap, String maThe, String ngayDangKy, String ngayDiemDanh) {
		return repo.findBangDiemDanh(maGoiTap, maThe, ngayDangKy, ngayDiemDanh);
	}

	public List<BangDiemDanh> findByNgayDiemDanh(String ngayDiemDanh) {
		return repo.findByNgayDiemDanh(ngayDiemDanh);
	}
	
	public void save(BangDiemDanh bangDiemDanh) {
		repo.save(bangDiemDanh);
	}
	
	public int countDiemDanh(String maGoiTap, String maThe, String ngayDangKy) {
		return repo.countDiemDanh(maGoiTap, maThe, ngayDangKy);
	}
}
