package br.com.alura.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
	@ResponseBody // diz para o spring que o retorno do metodo nao e uma pagina
	public String hello() {
		return "Helo World";
	}
}
