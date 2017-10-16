package br.com.caelum.contas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import br.com.caelum.contas.model.Conta;
import br.com.caelum.contas.model.TipoDaConta;

@Repository
public class ContaDAO {
	
	//	Receba suas depend�ncias sempre pelo construtor, isso � uma boa pr�tica de c�digo!
	private Connection connection;

	/**
	 * Faz uma conex�o atrav�s do que foi configurado no spring-context.xml
	 * Ao usar @Autowired no construtor, o Spring tenta descobrir como abrir uma conex�o, 
	 * mas claro que o Container n�o faz ideia com qual banco queremos nos conectar. 
	 * Para solucionar isso o Spring oferece uma configura��o de XML/Classe que define um provedor de conex�es.
	 */
	@Autowired
	public ContaDAO(DataSource ds) {
		try {
			this.connection = ds.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void adiciona(Conta conta) {
		String sql = "INSERT INTO conta (descricao, paga, valor, tipo) values (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getDescricao());
			stmt.setBoolean(2, conta.isPaga());
			stmt.setDouble(3, conta.getValor());
			stmt.setString(4, conta.getTipo().name());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Conta conta) {
		if (conta.getId() == null) {
			throw new IllegalStateException("Id da conta naoo deve ser nula.");
		}
		String sql = "DELETE FROM conta WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setLong(1, conta.getId());
			stmt.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void altera(Conta conta) {
		String sql = "UPDATE conta SET descricao = ?, paga = ?, dataPagamento = ?, tipo = ?, valor = ? where id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, conta.getDescricao());
			stmt.setBoolean(2, conta.isPaga());
			stmt.setDate(3, conta.getDataPagamento() != null ? new Date(conta.getDataPagamento().getTimeInMillis()) : null);
			stmt.setString(4, conta.getTipo().name());
			stmt.setDouble(5, conta.getValor());
			stmt.setLong(6, conta.getId());
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Conta> lista() {
		try {
			List<Conta> contas = new ArrayList<Conta>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM conta");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				contas.add(populaConta(rs));
			}
			rs.close();
			stmt.close();
			return contas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Conta buscaPorId(Long id) {
		if (id == null) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}
		try {
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM conta WHERE id = ?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return populaConta(rs);
			}
			rs.close();
			stmt.close();
			return null;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void paga(Long id) {
		if (id == null) {
			throw new IllegalStateException("Id da conta nao deve ser nula.");
		}
		String sql = "UPDATE conta SET paga = ?, dataPagamento = ? WHERE id = ?";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setBoolean(1, true);
			stmt.setDate(2, new Date(Calendar.getInstance().getTimeInMillis()));
			stmt.setLong(3, id);
			stmt.execute();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Conta populaConta(ResultSet rs) throws SQLException {
		Conta conta = new Conta();
		conta.setId(rs.getLong("id"));
		conta.setDescricao(rs.getString("descricao"));
		conta.setPaga(rs.getBoolean("paga"));
		conta.setValor(rs.getDouble("valor"));
		Date data = rs.getDate("dataPagamento");
		if (data != null) {
			Calendar dataPagamento = Calendar.getInstance();
			dataPagamento.setTime(data);
			conta.setDataPagamento(dataPagamento);
		}
		conta.setTipo(Enum.valueOf(TipoDaConta.class, rs.getString("tipo")));
		return conta;
	}
}
