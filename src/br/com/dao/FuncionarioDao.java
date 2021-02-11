package br.com.dao;

import java.sql.Connection;

//import com.mysql.jdbc.PreparedStatement;

import br.com.conectaBanco.ConnectionFactory;
import br.com.model.Funcionario;

public class FuncionarioDao {

	
	//criar uma conex�o privada
	private Connection connection;
	
	//um construtor publico onde a vari�vel da conex�o receber� um objeto da conex�o
	public FuncionarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	
	//m�todo adicionar no banco
	public void adicionaFuncionario(Funcionario funcionario){
		
		//string de consulta
		String sql = "insert into funcionario"+
		"(nome,usuario,senha)"+"values(?,?,?)"; 
		//try {
			//uma forma de voc� fazer uma inser��o no banco mais segura, onde voc� prepara os parametros para serem inseridos. evitando assim ataques como o sql injection.
			//O preparedStatement assume a responsabilidade de montar a sua query (String) , sem que voc� tenha que concatenar as condi��es, desde que voc� passe os par�metros corretamente. =]
			
		//	PreparedStatement ps = connection.prepareStatement(sql);
			//ps.setString(1, funcionario.getNome());
			//ps.setString(2, funcionario.getUsuario());
			//ps.setString(3, funcionario.getSenha());
			
			
			//ps.execute();
			//ps.close();
			///////////////////// PAREI AQUI
		//}
		
	}
	}
	
