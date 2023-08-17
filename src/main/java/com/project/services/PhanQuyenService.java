package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.PhanQuyen;
import com.project.repositories.PhanQuyenRepository;

@Service
public class PhanQuyenService {
	@Autowired
	private PhanQuyenRepository repo;
	
	public List<PhanQuyen> listAll(){
		return (List<PhanQuyen>) repo.findAll();
	}
	
	public PhanQuyen findByMaQuyen(String maQuyen) {
		return repo.findByMaQuyen(maQuyen);
	}
}
