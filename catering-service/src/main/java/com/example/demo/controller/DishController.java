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

import com.example.demo.model.Dish;
import com.example.demo.service.BuffetService;
import com.example.demo.service.DishService;

@Controller
@RequestMapping("/dish")
public class DishController {
	
	@Autowired private DishService dishService;

	@Autowired private BuffetService buffetService;
	
	private static final String DISH_DIR = "/dish/";

	@GetMapping("/all")
	public String getDishList(Model model) {
		List<Dish> dishs = dishService.findAll();
		model.addAttribute("dishs",dishs);
		return DISH_DIR + "DishList.html";
	}	
	
	// mostra uno specifico dish selezionato tramite id
	@GetMapping("/{id}")
	public String getDish(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dish", this.dishService.findById(id));
		return "index";
	}	

	
	// mostra la form per l'aggiunta di un nuovo dish per un relativo buffet
	@GetMapping("/add/form/{idBuffet}")
	public String getAddForm(@PathVariable("idBuffet") Long idBuffet, Model model) {
		model.addAttribute("dish", new Dish());
		model.addAttribute("idBuffet", idBuffet);
		return DISH_DIR + "dishAdd";
	}

	// aggiunge al db un nuovo dish per un relativo buffet
	@PostMapping("/add/{idBuffet}")
	public String adddish(@ModelAttribute("dish") Dish dish, @ModelAttribute("idBuffet") Long idBuffet, 
												Model model) {
		this.buffetService.addPiatto(dish, idBuffet);
		return "/buffet/" + idBuffet;
	}
	
	
	// elimina dal db un dish selezionato tramite id
	@GetMapping("/delete/{id}")
	public String deleteDish(@PathVariable("id") Long id,Model model) {
		this.dishService.deleteById(id);
		model.addAttribute("dishs", this.dishService.findAll());
		return DISH_DIR + "DishList.html";
	}
	
	// mostra la form per l'edit di un dish selezionato tramite id
	@GetMapping("/edit/form/{id}")
	public String getEditForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("dish", this.dishService.findById(id));
		return DISH_DIR + "DishEdit";
	}
	
	
	// edita un dish nel db
	@PostMapping("/edit/{id}")
	public String editDish(@ModelAttribute("dish") Dish dish, Model model) {
		this.dishService.update(dish);
		model.addAttribute("dishs", this.dishService.findAll());
		return DISH_DIR + "DishList";
	}
	
}
