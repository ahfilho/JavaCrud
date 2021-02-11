	package br.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.coyote.ProtocolException;

import java.util.Date;

import br.com.dao.ContatoDao;
import br.com.model.Contato;
import br.com.mvc.logica.Logica;

import java.util.Calendar;

@WebServlet("/adicionaContato")
public class AdicionaContatoServlet extends HttpServlet  {
	
	//toda servlet deve ter um construtor sem argumentos para que o container possa cri�-lo. Depois da cria��o, o servlet
	// container inicaliza a servlet com o m�todo INIT e o usa enquanto estiver ativo.
	//Quando fica inativo � chamado o metodo destroy, para entao liberar o objeto.
	/*public void init(ServletConfig config) throws ServletException{
		super.init(config);
		log("iniciando a servlet");
	}
	
	public void destroy(){
		super.destroy();
		log("destruindo a servlet");
	}
	*/
    protected void service(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        // busca o writer
        PrintWriter out = response.getWriter();

        // buscando os par�metros no request
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String dataEmTexto = request
                .getParameter("dataNascimento");
        Calendar dataNascimento = null;

        // fazendo a convers�o da data
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy")
                  .parse(dataEmTexto);
            dataNascimento = Calendar.getInstance();
            dataNascimento.setTime(date);
        } catch (ParseException e) {
            out.println("Erro de convers�o da data");
            return; //para a execu��o do m�todo
        }

        // monta um objeto contato
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setEndereco(endereco);
        contato.setEmail(email);
        contato.setDataNascimento(dataNascimento);

        // salva o contato
        ContatoDao dao = new ContatoDao();
        dao.adicionaNoBanco(contato);

        	//O REQUEST DISPATCHER VAI CHAMAR A PAGINA JSP E EXIBIR O QUE FOR COLOCADO L�
        	
        RequestDispatcher rd = request
                .getRequestDispatcher("/WEB-INF/jsp/contato-adicionado.jsp");
        rd.forward(request,response);
        
        }

    
    }

