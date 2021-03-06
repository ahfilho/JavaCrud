package br.com.mvc.logica;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.dao.ContatoDao;
import br.com.model.Contato;

public class RemoveContatoLogica implements Logica{

	@Override
	public String executa(HttpServletRequest req, HttpServletResponse res) throws Exception {


	      long id = Long.parseLong(req.getParameter("id"));

	      Contato contato = new Contato();
	      contato.setId(id);

	      ContatoDao dao = new ContatoDao();
	      dao.remover(contato);

	      System.out.println("Excluindo contato... ");

	      return "mvc?logica=ListaContatosLogica";
	    }

	  }