package br.com.naptec.base_dados.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.base_dados.dao.util.ConectorUtil;

public class PessoaDAO {
	private Connection con;
	private PreparedStatement stm;
	private ResultSet rs;
	
	private void criaCon() {
		try  {
			con = ConectorUtil.criaConexao();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void gravar(Pessoa pes) {
		StringBuilder query = new StringBuilder();
		try {
			query.append("INSERT INTO tb_pessoa(nome, email, \n");
			query.append(" idade) VALUES (?, ?, ?)");
			criaCon();
			stm = con.prepareStatement(query.toString());
			stm.setInt(3, pes.getIdade());
			stm.setString(1, pes.getNome());
			stm.setString(2, pes.getEmail());
			stm.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Pessoa> listar() {
		StringBuilder query = new StringBuilder();
		List<Pessoa> pes = new ArrayList<Pessoa>();
		try {
			query.append("SELECT id, nome, idade, email \n");
			query.append("FROM tb_pessoa");
			criaCon();
			stm = con.prepareStatement(query.toString());
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				Pessoa p = new Pessoa();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setIdade(rs.getInt("idade"));
				pes.add(p);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pes;
	}
	
	public Pessoa buscarPorId(Long id) {
		Pessoa p = null;
		StringBuilder query = new StringBuilder();
		try {
			query.append("SELECT id, nome, idade, email \n");
			query.append("FROM tb_pessoa WHERE id = ?");
			criaCon();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				p = new Pessoa();
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
				p.setEmail(rs.getString("email"));
				p.setIdade(rs.getInt("idade"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public void excluir(Long id) {
		StringBuilder query = new StringBuilder();
		query.append("DELETE FROM tb_pessoa WHERE id = ?");
		try {
			criaCon();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
		}catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void atualizar(Pessoa pes) throws Exception {
		StringBuilder query = new StringBuilder();
		try {
			query.append("UPDATE tb_pessoa SET \n");
			query.append("nome = ?, idade = ?, email = ? \n");
			query.append("WHERE id = ?");
			
			criaCon();
			stm = con.prepareStatement(query.toString());
			stm.setString(1, pes.getNome());
			stm.setInt(2, pes.getIdade());
			stm.setString(3, pes.getEmail());
			stm.setLong(4, pes.getId());
			stm.execute();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}




















