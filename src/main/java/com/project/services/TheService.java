package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.entity.The;
import com.project.repositories.TheRepository;

@Service
public class TheService {
	@Autowired
	private TheRepository repo;
	
	public List<The> listAll(){
		return (List<The>) repo.findAll();
	}
	
	public void save(The the) {
		repo.save(the);
	}
	
	public The findByMaThe(String maThe) {
		return repo.findByMaThe(maThe);
	}
}
