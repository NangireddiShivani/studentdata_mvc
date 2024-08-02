package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.studentmodel;
import com.example.demo.repositary.studentrepositary;

import jakarta.servlet.http.HttpSession;



@Controller
public class studentcontroller {
	@Autowired
	studentrepositary repo;
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
		
	}
	@GetMapping("/")
	public String  home(Model m) {
		List<studentmodel> li=(List<studentmodel>) repo.findAll();
		m.addAttribute("add-data" ,li);
//		add-attribut-data added to frontend
		return "home";
		
		
	}
	@GetMapping("/getbyid/{id}")
	public String getby( @PathVariable(value="id")  int id,Model m){
		Optional<studentmodel> sm=repo.findById(id);
		studentmodel stm=sm.get();
		m.addAttribute("data",stm);
		return "edit";
		
	}
	@PutMapping("/save_products")
	public String insert(@ModelAttribute studentmodel sm,HttpSession hts) {
		repo.save(sm);
		hts.setAttribute("message" ,"successful");
		return "redirect:/loadform";	
	}
	@PutMapping("/update")
	public String edit(@ModelAttribute studentmodel sm,HttpSession hts) {
		repo.save(sm);
		hts.setAttribute("message", "successfully updated");
		return "redirect:/";
	}
	@DeleteMapping("/delete/{id}")
	
	public String delete(@PathVariable(value="id") int id,HttpSession hts) {
		repo.deleteById(id);
		hts.setAttribute("message", "successfully deleted");
		return "redirect:/";
		
	}
	
	@GetMapping("/loadform")
	public String loadform() {
		return "add";
	}

}

//httpsession-pop ups
