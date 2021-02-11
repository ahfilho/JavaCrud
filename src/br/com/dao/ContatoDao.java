package br.com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.conectaBanco.ConnectionFactory;
import br.com.model.Contato;

public class ContatoDao {

	// parado ate resolver o metodo adicionar, depois voltar ao filtro.
	/*
	 * //construtor que recebe conex�o e armazena no atributo public
	 * ContatoDao(Connection connection) { this.connection = connection; }
	 */
	private Connection connection;
	// inicia uma conex�o privada com um atributo privado

	// constutor que vai receber um objeto da nova conex�o
	public ContatoDao() {
		this.connection = new ConnectionFactory().getConnection();
	}

	// metodo para remover do banco
	public void remover(Contato contato) {
		try {
			// instru��es para o banco
			PreparedStatement stmt = connection.prepareStatement("delete from contatos where id=?");
			// vai deletar da tabela contatos onde o if for igual ao id informado
			stmt.setLong(1, contato.getId()); // id informado
			stmt.execute(); // executa a a��o
			stmt.close(); // fecha a conex�o
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void adicionaNoBanco(Contato contato) {

		String sql = "insert into contatos" + "(nome,email,endereco,dataNascimento)" + "values(?,?,?,?)";
		try {
			// preparedStatement para inser��o
			PreparedStatement stmt = connection.prepareStatement(sql);

			// setando os valores que ser�o inseridos no banco
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new java.sql.Date(contato.getDataNascimento().getTimeInMillis()));

			// executa a opera��o
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Contato pesquisaBanco(long id) throws SQLException {
		// pega a conex�o e o statement
		Connection conexao = new ConnectionFactory().getConnection();
		PreparedStatement stmt = connection.prepareStatement("select * from contatos where id=?");

		stmt.setLong(1, id);

		// executa um select na tabela contatos e retorna os registros de uma
		// determinada query
		ResultSet rs = stmt.executeQuery();

		Contato contato = null;

		// O OBJETO RETORNADO � UM RESULTSET, permite navegar por seus registros atrav�s
		// do m�todo NEXT
		// VAI RETORNAR FALSE QUANDO CHEGAR AO FIM DA PESQUISA NA TABELA

		// itera no resultset
		if (rs.next()) {
			contato = new Contato();
			contato.setId(id);

			// para retornar o valor de uma coluna do banco, basta chamar um dos m�todos get
			// do RESULTSET
			/*
			 * String nome = rs.getString("id"); String email = rs.getString("email");
			 * String endereco = rs.getString("endereco"); String dataNascimento =
			 * rs.getString("dataNascimento");
			 * 
			 * System.out.println(nome+"---"+ email +"---" + endereco + "" +
			 * dataNascimento);
			 * 
			 */

		}

//	rs.close();  //fecha a itera��o ao final
		stmt.close(); // encerra o statement
		conexao.close(); // fecha a conex�o ao final

		return contato;
	}

	public List<Contato> getLista() {
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = this.connection.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
				contato.setId(rs.getLong("id"));
				contato.setNome(rs.getString("nome"));
				contato.setEmail(rs.getString("email"));
				contato.setEndereco(rs.getString("endereco"));

				// montando a data atrav�s do Calendar
				Calendar data = Calendar.getInstance();
				data.setTime(rs.getDate("dataNascimento"));
				contato.setDataNascimento(data);

				// adicionando o objeto � lista
				contatos.add(contato);
			}
			rs.close();
			stmt.close();
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//ALTERAR
	public void altera(Contato contato) {
//recebe um contato
		// os valores que ser�o atualizados na tabela contato
		String sql = "update contatos set nome=?, email=?, endereco=?," + "dataNascimento=? where id=?";

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));

			stmt.setLong(5, contato.getId());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
