package br.com.caelum.contas.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.model.Conta;

@Controller
public class ContaController {
	
	//	A ideia dao IoC é que a classe não mais resolve as suas dependências 
	//	por conta própria mas apenas declara que depende de alguma outra classe. 
	private ContaDAO contaDAO;
	
	@Autowired
	public ContaController(ContaDAO dao){
		this.contaDAO = dao;
	}

	/**
	 * Responsável por renderizar a página de formulário
	 */
	@RequestMapping(value = "/form")
	public String formulario(){
		return "conta/formulario";
	}
	
	/**
	 * Responsável receber os dados do formulário e salvar no banco de dados
	 * @Valid - valida minhas anotações postas com o BeanValidation nos meus pojos
	 * BindingResult - me mostra o resultado dessas validações
	 */
	@RequestMapping(value = "/adicionarConta")
	public String add(@Valid Conta conta, BindingResult result){
		if(result.hasErrors()){
			return "conta/formulario";
		}
		/*if (result.hasFieldErrors("descricao")) {
            return "conta/formulario";
        }*/
		contaDAO.adiciona(conta);
		return "conta/conta-adicionada";
	}
	
	/**
	 * Lista as contas do banco de dados, o ModelAndView envia informações para as views
	 */
	@RequestMapping(value = "/lista")
	public ModelAndView lista(){
		List<Conta> contas = contaDAO.lista();
		ModelAndView mv = new ModelAndView("conta/lista");
		mv.addObject("contas", contas);
		return mv;
	}
	
	/**
	 * Remove uma conta vinda do JSP da lista
	 * forword - Faz um redirecionamento do lado do servidor
	 * redirect - Faz o redirecionamento do lado cliente (o browser faz uma nova requisição)
	 */
	@RequestMapping(value = "/deletarConta")
	public String deletar(Conta conta){
		contaDAO.remove(conta);
		return "redirect:lista";
	}
	
	/**
	 * Responsável por renderizar a página de formulário de edição
	 */
	@RequestMapping(value = "/formEdicao")
	public String mostra(Long id, Model model){
		Conta conta = contaDAO.buscaPorId(id);
		model.addAttribute("conta", conta);
		return "conta/formulario-edicao";
	}
	
	/**
	 * Responsável receber os dados do formulário de edição e atualizar no banco de dados
	 */
	@RequestMapping(value = "/alterarConta")
	public String atualizar(Conta conta){
		contaDAO.altera(conta);
		return "redirect:lista";
	}
	
	/**
	 * Responsável receber uma conta e atualizar o status dela
	 * Devolve o status 200 para o usuário
	 */
	@RequestMapping(value = "/pagarConta")
	public void pagar(Long id, HttpServletResponse response){
		contaDAO.paga(id);
		response.setStatus(200);
	}
	
	//	https://www.tutorialspoint.com/jsp/index.htm
	//	Site FODA - https://www.tutorialspoint.com/springmvc/
	//	BeanValidation - http://blog.caelum.com.br/java-ee-6-comecando-com-bean-validation/
	//	FlashScope - http://vard-lokkur.blogspot.com.br/2012/02/spring-mvc-flash-attributes.html
	
}
