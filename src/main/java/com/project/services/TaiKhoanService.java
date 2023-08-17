package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.TaiKhoan;
import com.project.repositories.TaiKhoanRepository;

@Service
public class TaiKhoanService {
	@Autowired
	private TaiKhoanRepository repo;
	
	public List<TaiKhoan> listAll(){
		return (List<TaiKhoan>) repo.findAll();
	}
	
	public void save(TaiKhoan taiKhoan) {
		repo.save(taiKhoan);
	}
	
	public TaiKhoan findByUsername(String username) {
		return repo.findByUserName(username);
	}
}
