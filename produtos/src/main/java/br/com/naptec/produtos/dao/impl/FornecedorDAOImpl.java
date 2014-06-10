package br.com.naptec.produtos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.naptec.produtos.beans.Fornecedor;
import br.com.naptec.produtos.dao.FornecedorDAO;
import br.com.naptec.produtos.dao.util.ConectorUtil;

public class FornecedorDAOImpl implements FornecedorDAO {
	private PreparedStatement stm;
	private ResultSet rs;
	private StringBuilder query;
	private Connection con;

	public void gravar(Fornecedor e) throws Exception {
		try {
			query = new StringBuilder();
			query.append("INSERT INTO tb_fornecedores \n");
			query.append("(nome, endereco, telefone, email) \n");
			query.append("VALUES \n");
			query.append("(?, ?, ?, ?)");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setString(1, e.getNome());
			stm.setString(2, e.getEndereco());
			stm.setString(3, e.getTelefone());
			stm.setString(4, e.getEmail());
			stm.execute();
		} finally {
			con.close();
		}
	}

	public void atualizar(Fornecedor e) throws Exception {
		try {
			query = new StringBuilder();
			query.append("UPDATE tb_fornecedores \n");
			query.append("SET \n");
			query.append("nome = ?, \n");
			query.append("endereco = ?, \n");
			query.append("telefone = ?, \n");
			query.append("email = ? \n");
			query.append("WHERE id_fornecedor = ?");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setString(1, e.getNome());
			stm.setString(2, e.getEndereco());
			stm.setString(3, e.getTelefone());
			stm.setString(4, e.getEmail());
			stm.setLong(5, e.getId());
			stm.execute();
		} finally {
			con.close();
		}
	}

	public void excluir(Long id) throws Exception {
		try {
			query = new StringBuilder();
			query.append("DELETE FROM tb_fornecedores \n");
			query.append("WHERE id_fornecedor = ?");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
		} finally {
			con.close();
		}
	}

	public Fornecedor findById(Long id) throws Exception {
		Fornecedor f = null;
		try {
			query = new StringBuilder();
			query.append("SELECT \n");
			query.append("id_fornecedor, nome, endereco, \n");
			query.append("telefone, email \n");
			query.append("FROM tb_fornecedores ");
			query.append("WHERE id_fornecedor = ?");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				f = new Fornecedor();
				f.setId(rs.getLong("id_fornecedor"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getString("telefone"));
				f.setEndereco(rs.getString("endereco"));
				f.setEmail(rs.getString("email"));
			}
		} finally {
			con.close();
		}
	
		return f;
	}

	public List<Fornecedor> list() throws Exception {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		try {
			query = new StringBuilder();
			query.append("SELECT \n");
			query.append("id_fornecedor, nome, endereco, \n");
			query.append("telefone, email \n");
			query.append("FROM tb_fornecedores ");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rs.getLong("id_fornecedor"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getString("telefone"));
				f.setEndereco(rs.getString("endereco"));
				f.setEmail(rs.getString("email"));
				list.add(f);
			}
		} finally {
			con.close();
		}
	
		return list;
	}

	public List<Fornecedor> listByParams(Map<String, Object> params)
			throws Exception {
		List<Fornecedor> list = new ArrayList<Fornecedor>();
		try {
			query = new StringBuilder();
			query.append("SELECT \n");
			query.append("id_fornecedor, nome, endereco, \n");
			query.append("telefone, email \n");
			query.append("FROM tb_fornecedores \n");
			query.append("WHERE \n");
			Iterator<String> keys = params.keySet().iterator();
			while(keys.hasNext()) {
				String key = keys.next();
				Object value = params.get(key);
				if(query.toString().contains("?")) {
					query.append(" AND \n");
				}
				if(value instanceof String) {
					query.append(" UPPER(" + key + ")");
					query.append(" LIKE UPPER(?) \n");
				} else {
					query.append(key + " = ? \n");
				}
			}
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			keys = params.keySet().iterator();
			int count = 0;
			while(keys.hasNext()) {
				String key = keys.next();
				Object value = params.get(key);
				if(value instanceof String) {
					stm.setString(++count, "%" + value + "%");
				} else {
					stm.setLong(++count, new Long(value.toString()));
				}
			}
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				Fornecedor f = new Fornecedor();
				f.setId(rs.getLong("id_fornecedor"));
				f.setNome(rs.getString("nome"));
				f.setTelefone(rs.getString("telefone"));
				f.setEndereco(rs.getString("endereco"));
				f.setEmail(rs.getString("email"));
				list.add(f);
			}
		} finally {
			con.close();
		}
	
		return list;
	}

}
