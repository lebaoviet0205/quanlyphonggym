package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.GoiTap;
import com.project.repositories.GoiTapRepository;

@Service
public class GoiTapService {
	@Autowired
	private GoiTapRepository repo;
	
	public List<GoiTap> listAll(){
		return (List<GoiTap>) repo.findAll();
	}
	
	public void save(GoiTap goiTap) {
		repo.save(goiTap);
	}
	
	public GoiTap findByMaGoiTap(String maGoiTap) {
		return repo.findByMaGoiTap(maGoiTap);
	}
}
