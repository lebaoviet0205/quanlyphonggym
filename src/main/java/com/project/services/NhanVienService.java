package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.NhanVien;
import com.project.repositories.NhanVienRepository;

@Service
public class NhanVienService {
	@Autowired
	private NhanVienRepository repo;
	
	public List<NhanVien> listAll(){
		return (List<NhanVien>) repo.findAll();
	}
	
	public NhanVien findByUserName(String userName) {
		return repo.findByUserName(userName);
	}
	
	public NhanVien findByMaNV(String maNV) {
		return repo.findByMaNV(maNV);
	}
	
	public void save(NhanVien nhanVien) {
		repo.save(nhanVien);
	}
}
