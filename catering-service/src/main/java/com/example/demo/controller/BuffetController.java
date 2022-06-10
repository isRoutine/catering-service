package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Buffet;
import com.example.demo.service.BuffetService;

@Controller
@RequestMapping("/buffet")
public class BuffetController {
	
	@Autowired BuffetService buffetService;
	private static final String BUFFET_DIR = "/buffet/";
	
	// mostra tutti i buffet nel db
	@GetMapping("/all")
	public String getBuffetList(Model model) {
		List<Buffet> buffets = buffetService.findAll();
		model.addAttribute("buffets",buffets);
		return BUFFET_DIR + "BuffetList.html";
		
	}
	
	// mostra uno specifico buffet selezionato tramite id
	@GetMapping("/{id}")
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return BUFFET_DIR + "BuffetProfile";
	}
	
	// mostra la form per l'aggiunta di un nuovo buffet
	@GetMapping("/add/form")
	public String getAddForm(Model model) {
		boolean input_user = false;
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("input_user", input_user);
		return BUFFET_DIR + "BuffetAdd";
	}

	// aggiunge al db un nuovo buffet
	@PostMapping("/add")
	public String addBuffet(@ModelAttribute("buffet") Buffet buffet, Model model) {
		this.buffetService.save(buffet);
		//return "index";
		return this.getBuffet(buffet.getId(),model);
	}
	
	// elimina dal db un buffet selezionato tramite id
	@GetMapping("/delete/{id}")
	public String deleteBuffet(@PathVariable("id") Long id,Model model) {
		this.buffetService.deleteById(id);
		model.addAttribute("buffets", this.buffetService.findAll());
		return BUFFET_DIR + "BuffetList.html";
	}
	
	// mostra la form per l'edit di un buffet selezionato tramite id
	@GetMapping("/edit/form/{id}")
	public String getEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return BUFFET_DIR + "BuffetEdit";
	}
	
	
	// edita un buffet nel db
	@PostMapping("/edit/{id}")
	public String editBuffet(@ModelAttribute("buffet") Buffet buffet, Model model) {
		this.buffetService.update(buffet);
		model.addAttribute("buffets", this.buffetService.findAll());
		return BUFFET_DIR + "BuffetList";
	}
	
}
