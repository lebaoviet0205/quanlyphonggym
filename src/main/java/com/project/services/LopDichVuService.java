package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.LopDichVu;
import com.project.repositories.LopDichVuRepository;

@Service
public class LopDichVuService {
	@Autowired
	private LopDichVuRepository repo;
	
	public List<LopDichVu> listAll(){
		return (List<LopDichVu>) repo.findAll();
	}
	
	public LopDichVu findByClassId(String classId) {
		return repo.findByClassId(classId);
	}
	
	public void save(LopDichVu lopDichVu) {
		repo.save(lopDichVu);
	}
}
