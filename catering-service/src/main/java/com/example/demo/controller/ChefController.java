package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Chef;
import com.example.demo.service.ChefService;

@Controller
@RequestMapping("/chef")
public class ChefController {
	
	@Autowired ChefService chefService;

	@GetMapping("/all")
	public String getChefList(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs",chefs);
		return "ChefList.html";
		
	}	
	
}
