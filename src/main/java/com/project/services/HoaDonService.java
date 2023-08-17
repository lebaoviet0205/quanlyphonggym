package com.project.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.HoaDon;
import com.project.repositories.HoaDonRepository;

@Service
public class HoaDonService {
	@Autowired
	private HoaDonRepository repo;
	
	public List<HoaDon> listAll(){
		return (List<HoaDon>) repo.findAll();
	}
	
	public void save(HoaDon hoaDon) {
		repo.save(hoaDon);
	}
	
	public void delete(HoaDon hoaDon) {
		repo.delete(hoaDon);
	}
	
	public HoaDon findByMaHD(String maHD) {
		return repo.findByMaHD(maHD);
	}
	
	public List<HoaDon> findFromDateToDate(LocalDateTime form, LocalDateTime to) {
		return repo.findFromDateToDate(form, to);
	}
}
