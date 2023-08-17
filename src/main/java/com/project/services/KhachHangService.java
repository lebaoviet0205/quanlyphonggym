package com.project.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.KhachHang;
import com.project.repositories.KhachHangRepository;

@Service
public class KhachHangService {
	@Autowired
	private KhachHangRepository repo;
	
	public List<KhachHang> listAll(){
		return (List<KhachHang>) repo.findAll();
	}
	
	public void save(KhachHang khachHang) {
		repo.save(khachHang);
	}
	
	public KhachHang findByMaKH(String maKH) {
		return repo.findByMaKH(maKH);
	}
	
	public List<KhachHang> findFromDateToDate(LocalDate from, LocalDate to){
		return repo.findFromDateToDate(from, to);
	}
}
