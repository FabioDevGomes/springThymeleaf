package com.fabio.curso.boot.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fabio.curso.boot.domain.Cargo;
import com.fabio.curso.boot.domain.Departamento;
import com.fabio.curso.boot.domain.Funcionario;
import com.fabio.curso.boot.domain.UF;
import com.fabio.curso.boot.service.CargoService;
import com.fabio.curso.boot.service.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;
	
	@Autowired
	CargoService cargoService;

	@GetMapping("/cadastrar")
	public String cadastrar(Funcionario funcionario) {
		return "/funcionario/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarTodos());
		return "/funcionario/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.salvar(funcionario);
		attr.addFlashAttribute("success","Funcionario incluído com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("funcionario", funcionarioService.buscarPorId(id));
		return "/funcionario/cadastro";
	}
	
	//esse parâmetro está sendo enviado do formulário .html
	@PostMapping("/editar")
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		funcionarioService.editar(funcionario);
		attr.addFlashAttribute("success","Funcionário editado com sucesso.");
		return "redirect:/funcionarios/cadastrar";
	}
	
	@GetMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		funcionarioService.excluir(id);
		model.addAttribute("success","Funcionario excluído com sucesso.");
		return listar(model);
	}
	
	@GetMapping("/buscar/nome")
	public String getProNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", funcionarioService.buscarPorNome(nome));
		return "/funcionario/lista";
	}

		
	//essa anotação já coloca o retorno desse método na variável 'cargos'
	@ModelAttribute("cargos")
	public List<Cargo> listaCargos(){
		return cargoService.buscarTodos();
	}

	//essa anotação já coloca o retorno desse método na variável 'ufs'
	@ModelAttribute("ufs")
	public UF[] listaUfs(){
		return UF.values();
	}
}
