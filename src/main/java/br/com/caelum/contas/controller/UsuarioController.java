package br.com.caelum.contas.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.caelum.contas.dao.UsuarioDAO;
import br.com.caelum.contas.model.Usuario;

@Controller
public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	@Autowired
	public UsuarioController(UsuarioDAO usuarioDAO){
		this.usuarioDAO = usuarioDAO;
	}
	
	@RequestMapping(value = "/loginForm")
	public String formulario(){
		return "usuario/login";
	}
	
	@RequestMapping(value = "/login")
	public String login(@Valid Usuario usuario, BindingResult result, HttpSession session){
		if(result.hasErrors()){
			return formulario();
		} if(usuarioDAO.existeUsuario(usuario)){
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		}
		return formulario();
	}
	
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:loginForm";
	}
	
	/**
	 * Session - � uma maneira que o servidor tem de lembrar de vc, mais pra frente algum cara inteligente 
	 * vai verificar quem ta fazendo uma requisi��o e ver se ele tem essa vari�vel 'usuarioLogado', se tiver ele passou 
	 * pelo login com sucesso, continue no sistema, se n�o ele n�o fez login ainda, volta para o furmul�rio de login.
	 * 
	 */
	
	/**
	 * Cookies - Vari�vel que fica armazenada no browser do usu�rio, quando vc acessa o Tomcat pela primeira vez,
	 * o servidor pede para o browser guardar a vari�vel 'JSESSIONID', sempre que vc faz uma requisi��o o seu browser  
	 * est� devolvendo essa ID pra o servidor.
	 * Quando um cookie � salvo no cliente, ele � enviado de volta ao servidor toda vez que o cliente efetuar 
	 * uma nova requisi��o. Desta forma, o servidor consegue identificar aquele cliente sempre com os dados que 
	 * o cookie enviar. Um exemplo de bom uso de cookies � na tarefa de lembrar o nome de usu�rio na pr�xima vez 
	 * que ele quiser se logar, para que n�o tenha que redigitar o mesmo. Cada cookie s� � armazenado para um website. 
	 */
}
