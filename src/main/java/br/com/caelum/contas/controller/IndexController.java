package br.com.caelum.contas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(value = "/")
	public String execute(){
		System.out.println("Curso Alura - Spring MVC");
		return "index";
	}
	
}
