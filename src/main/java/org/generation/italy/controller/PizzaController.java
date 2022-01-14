package org.generation.italy.controller;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PizzaController {

	
	@Autowired
	private PizzaService service;
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "pizza";
	}
	
	@GetMapping("/create")
	public String crea(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "form";
	}
	
	@PostMapping("/create")
	public String save(Model model,@ModelAttribute("pizza") Pizza formPizza) {
		service.save(formPizza);
		return "redirect:/";
	}
}
