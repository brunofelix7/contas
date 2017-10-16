package br.com.caelum.contas.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Intercepta as requisi��es antes ou depois de irem para uma Action
 * Funcionam como Filtros, ou seja, toda requisicao antes de ser executada passar� por ele. 
 */
public class AutorizadorInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * Existe uma interface 'HandlerInterceptor' que implementa 3 metodos: preHandle, postHandle e afterCompletion.
	 * Os metodos preHandle e postHandle serao executados antes e depois, respectivamente, da a��o. 
	 * Enquanto o m�todo afterCompletion � chamado no final da requisicao, ou seja apos ter renderizado o JSP.
	 */

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object controller) throws Exception {
		String uri = request.getRequestURI();
		
	    // Se for a pagina de login ou resources, deixa passar
		if(uri.endsWith("loginForm") || uri.endsWith("login") || uri.endsWith("logout") || uri.contains("resources")) {
			return true;
		}
		if(request.getSession().getAttribute("usuarioLogado") != null) {
			//	Continua a axecucao
			request.getSession().setMaxInactiveInterval(30);
			return true;
		}
		//	Caso contr�rio, pare a execu��o e retorne para a tela de login
		response.sendRedirect("loginForm");
		return false;
		
	}
}
