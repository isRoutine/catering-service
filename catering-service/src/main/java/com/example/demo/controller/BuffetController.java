package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Controller
@RequestMapping("/buffet")
public class BuffetController {
	
	@Autowired BuffetService buffetService;
	
	@GetMapping("/all")
	public String getBuffetList(Model model) {
		List<Buffet> buffetList = buffetService.findAll();
		model.addAttribute("buffetList",buffetList);
		return "BuffetList.html";
		
	}
	
	
	
}
