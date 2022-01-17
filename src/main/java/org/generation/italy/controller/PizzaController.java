package org.generation.italy.controller;

import javax.validation.Valid;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredientiService;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class PizzaController {

	
	@Autowired
	private PizzaService service;
	
	@Autowired
	private IngredientiService ingredientiService;
	
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("list", service.findAll());
		return "pizza";
	}
	
	@GetMapping("/create")
	public String crea(Model model) {
		model.addAttribute("edit", false);
		model.addAttribute("pizza", new Pizza());
		model.addAttribute("ingredienti", ingredientiService.findAllSortedByNome());
		return "form";
	}
	
	@PostMapping("/create")
	public String save(Model model,@ModelAttribute("pizza") Pizza formPizza) {
		service.save(formPizza);
		model.addAttribute("ingredienti", ingredientiService.findAllSortedByNome());
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit (@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("pizza", service.getById(id));
		model.addAttribute("ingredienti", ingredientiService.findAllSortedByNome());
		return "form";
	}
	
	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("pizza") Pizza formPizza,Model model,  RedirectAttributes redirectAttributes) {		
		service.update(formPizza);		
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String doDelete(Model model, @PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		service.deleteById(id);		
		return "redirect:/";
	}
	
	
}
