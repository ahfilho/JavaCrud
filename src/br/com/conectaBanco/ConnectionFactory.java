package br.com.conectaBanco;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection()  {
		
		
	      try {
	    	  //VAI TENTAR REGISTRAR O DRIVER PARA A CONEX�O COM O BANCO
	    	  DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	    	  
	    	  //RETORNA A CONEX�O
	          return DriverManager.getConnection(
	  "jdbc:mysql://localhost/javabanco", "root", "");  // CONEX�O COM O BANCO
	      } catch (SQLException e) {
	          throw new RuntimeException(e);
	      }
	  }
}