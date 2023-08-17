package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.GiaGoiTap;
import com.project.entity.LopDichVu;
import com.project.repositories.GiaGoiTapRepository;
import com.project.repositories.LopDichVuRepository;

@Service
public class GiaGoiTapService {
	@Autowired
	private GiaGoiTapRepository repo;
	
	public List<GiaGoiTap> listAll(){
		return (List<GiaGoiTap>) repo.findAll();
	}
	
	public void save(GiaGoiTap giaGoiTap) {
		repo.save(giaGoiTap);
	}
}
