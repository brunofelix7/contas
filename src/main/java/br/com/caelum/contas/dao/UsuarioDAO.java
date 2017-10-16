package br.com.caelum.contas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.caelum.contas.model.Usuario;

@Repository
public class UsuarioDAO {
	
	private Connection connection;

	@Autowired
	public UsuarioDAO(DataSource ds) {
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public boolean existeUsuario(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuario nao deve ser nulo");
		}
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			boolean encontrado = rs.next();
			rs.close();
			stmt.close();
			return encontrado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void insere(Usuario usuario) {
		if(usuario == null) {
			throw new IllegalArgumentException("Usuario nao deve ser nulo");
		}
		try {
			PreparedStatement stmt = this.connection.prepareStatement("INSERT INTO usuario (login,senha) VALUES (?,?)");
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}		
	}
}

