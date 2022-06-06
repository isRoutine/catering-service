package com.example.demo.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Buffet;
import com.example.demo.repository.BuffetRepository;

@Service
public class BuffetService {

	@Autowired BuffetRepository buffetRepository;
	
	public List<Buffet> findAll(){
		List<Buffet> buffets = new LinkedList<Buffet>();
		for(Buffet b : this.buffetRepository.findAll())
			buffets.add(b);
		return buffets;
	}
	
	
	public Buffet findById(Long id) {
		return this.buffetRepository.findById(id).get();
	}
	
	@Transactional
	public void save(Buffet b) {
		this.buffetRepository.save(b);
	}
	
	@Transactional
	public void delete(Buffet b) {
		this.buffetRepository.delete(b);
	}
	
	@Transactional
	public void deleteById(Long id) {
		this.buffetRepository.deleteById(id);
	}


	@Transactional
	public void update(Buffet buffet) {
		// TODO Auto-generated method stub
		Buffet foo = this.buffetRepository.findById(buffet.getId()).get();
		foo.setName(buffet.getName());
		foo.setDescription(buffet.getDescription());
		this.buffetRepository.save(foo);
	}
		
}
