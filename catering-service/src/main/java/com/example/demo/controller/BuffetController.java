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
import com.example.demo.model.Chef;
import com.example.demo.service.BuffetService;
import com.example.demo.service.ChefService;

@Controller
@RequestMapping("/buffet")
public class BuffetController {
	
	@Autowired BuffetService buffetService;
	
	@Autowired ChefService chefService;
	
	public static final String BUFFET_DIR = "/buffet/";
	
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
		Buffet buffet = this.buffetService.findById(id); 
		model.addAttribute("buffet", buffet);
		model.addAttribute("dishes", buffet.getDishes());
		return BUFFET_DIR + "BuffetProfile";
	}
	
	// mostra la form per l'aggiunta di un nuovo buffet
	@GetMapping("/add/{idChef}")
	public String getAddForm(@PathVariable("idChef") Long idChef, Model model) {
		model.addAttribute("buffet", new Buffet());
		model.addAttribute("idChef",idChef);
		return BUFFET_DIR + "BuffetAdd";
	}

	// aggiunge al db un nuovo buffet
	@PostMapping("/add/{idChef}")
	public String addBuffet(@ModelAttribute("buffet") Buffet buffet, 
							@PathVariable("idChef") Long idChef,Model model) {
		this.chefService.addBuffet(idChef,buffet);
		return "redirect:/chef/" + idChef;
	}
	
	// elimina dal db un buffet selezionato tramite id
	@GetMapping("/delete/{id}")
	public String deleteBuffet(@PathVariable("id") Long id,Model model) {
		Long idChef = this.buffetService.findById(id).getChef().getId();
		Buffet buffet = this.buffetService.findById(id);
		this.buffetService.deleteById(id);
		return "redirect:/chef/" + idChef;
	}
	
	// mostra la form per l'edit di un buffet selezionato tramite id
	@GetMapping("/edit/{id}")
	public String getEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("buffet", this.buffetService.findById(id));
		return BUFFET_DIR + "BuffetEdit";
	}
	
	
	// edita un buffet nel db
	@PostMapping("/edit/{id}")
	public String editBuffet(@ModelAttribute("buffet") Buffet buffet, Model model) {
		this.buffetService.update(buffet);
		//model.addAttribute("buffets", this.buffetService.findAll());
		return "redirect:/buffet/" + buffet.getId();
	}
	
}
