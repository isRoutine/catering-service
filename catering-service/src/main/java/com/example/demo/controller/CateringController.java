package com.example.demo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;

@Controller
public class CateringController {
	
	@Autowired private BuffetService buffetService;
	@Autowired private ChefService   ChefService;	
	
	@GetMapping("/")
	public String homepage(Model model) {
		List<Buffet> buffets = this.buffetService.findAll();
		Collections.shuffle(buffets);
		return "index";
	}

}
