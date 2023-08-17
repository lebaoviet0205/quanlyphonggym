package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.ChiTietThe;
import com.project.entity.KhachHang;
import com.project.repositories.ChiTietTheRepository;
import com.project.repositories.KhachHangRepository;

@Service
public class ChiTietTheService {
	@Autowired
	private ChiTietTheRepository repo;
	
	public List<ChiTietThe> listAll(){
		return (List<ChiTietThe>) repo.findAll();
	}
	
	public void save(ChiTietThe chiTietThe) {
		repo.save(chiTietThe);
	}
	
	public void delete(ChiTietThe chiTietThe) {
		repo.delete(chiTietThe);
	}
	
	public ChiTietThe findChiTietThe(String maGoiTap, String maThe, String ngayDangKy) {
		return repo.findChiTietThe(maGoiTap, maThe, ngayDangKy);
	}
	
	public List<ChiTietThe> findListChiTietThe (String maGoiTap, String maThe){
		return repo.findListChiTietThe(maGoiTap, maThe);
	}
}
