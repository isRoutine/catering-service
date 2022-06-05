package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Chef;
import com.example.demo.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired ChefRepository chefRepository;
	
	public List<Chef> findAll(){
		List<Chef> chefs = new LinkedList<Chef>();
		for(Chef b : this.chefRepository.findAll())
			chefs.add(b);
		return chefs;
	}
	
	
	public Chef findById(Long id) {
		return this.chefRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Chef b) {
		this.chefRepository.save(b);
	}
	
	@Transactional
	public void delete(Chef b) {
		this.chefRepository.delete(b);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.chefRepository.deleteById(id);
	}
}
