package br.com.treinaweb.GerenciadorTarefas.Controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.treinaweb.GerenciadorTarefas.Models.Tarefa;
import br.com.treinaweb.GerenciadorTarefas.Repositorios.IRepositorioTarefa;

@Controller
@RequestMapping("/tarefas")
public class TarefasController {

	@Autowired
	private IRepositorioTarefa repositorioTarefa;

	@GetMapping("/listar")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/tarefas/listar");
		mv.addObject("tarefas", repositorioTarefa.findAll());

		return mv;
	}

	@GetMapping("/inserir")
	public ModelAndView inserir() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("tarefas/inserir");
		mv.addObject("tarefa", new Tarefa());

		return mv;
	}

	@PostMapping("/inserir")
	public ModelAndView inserir(@Valid Tarefa tarefa, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
					"A data de expiração deve ser preenchida.");
		} else {
			if (tarefa.getDataExpiracao().before(new Date()))
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
						"A data de expiração não pode ser anterior a data atual.");
		}

		if (result.hasErrors()) {
			mv.setViewName("/tarefas/inserir");
			mv.addObject(tarefa);
		} else {
			mv.setViewName("redirect:/tarefas/listar");
			repositorioTarefa.save(tarefa);
		}

		return mv;
	}
	
	@GetMapping("/alterar/{id}")
	public ModelAndView alterar(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Tarefa tarefa = repositorioTarefa.getOne(id);
		mv.addObject("tarefa", tarefa);
		mv.setViewName("/tarefas/alterar");
		return mv;
	}
	
	@PostMapping("/alterar")
	public ModelAndView alterar(@Valid Tarefa tarefa, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		if (tarefa.getDataExpiracao() == null) {
			result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
					"A data de expiração deve ser preenchida.");
		} else {
			if (tarefa.getDataExpiracao().before(new Date()))
				result.rejectValue("dataExpiracao", "tarefa.dataExpiracaoInvalida",
						"A data de expiração não pode ser anterior a data atual.");
		}

		if (result.hasErrors()) {
			mv.setViewName("/tarefas/alterar");
			mv.addObject(tarefa);
		} else {
			mv.setViewName("redirect:/tarefas/listar");
			repositorioTarefa.save(tarefa);
		}

		return mv;
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id) {
		repositorioTarefa.deleteById(id);
		return "redirect:/tarefas/listar";
	}
	
	@GetMapping("/concluir/{id}")
	public String concluir(@PathVariable("id") Long id) {
		Tarefa tarefa = repositorioTarefa.getOne(id);
		tarefa.setConcluida(true);
		tarefa.setDataConclusao(new Date());
		repositorioTarefa.save(tarefa);
		return "redirect:/tarefas/listar";
	}
}
