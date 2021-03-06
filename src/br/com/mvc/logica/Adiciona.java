package br.com.mvc.logica;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ContatoDao;
import br.com.model.Contato;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class Adiciona implements Logica {

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {

		String usuario = req.getParameter("usuario");
		String email = req.getParameter("email");
		String telefone = req.getParameter("telefone");
		String dataEmTexto = req.getParameter("dataNascimento");
		Calendar dataNascimento = null;

		// fazendo a convers?o da data
		try {

			// deixando explicito o date correto para usar
			java.util.Date date = new SimpleDateFormat("dd/mm/yyyy").parse(dataEmTexto);
			dataNascimento = Calendar.getInstance();
			dataNascimento.setTime(date);

		} catch (Exception e) {

			System.out.println("ERRO NA CONVERS?O DA DATA");

		}
		// pega os valores da requisi??o e monta no objeto, depois salva
		// e retorna a pagina de listagem
		System.out.println("ADICIONANDO NO BANCO --- adicionaContatoLogica --> ADICIONA");
		// TENTA ADICIONAR
		try {

			Contato contato = new Contato();
			contato.setUsuario(usuario);
			contato.setEmail(email);
			contato.setTelefone(telefone);
			contato.setDataNascimento(dataNascimento);

			ContatoDao dao = new ContatoDao();
			dao.adicionaNoBanco(contato);
		} catch (Exception e) {
			System.out.println("ERRO ANTES DE ADICIONAR NO BANCO");
			return "/WEB-INF/jsp/erro.html";
		}

		return "/WEB-INF/jsp/contato-adicionado.jsp";

	}

}
