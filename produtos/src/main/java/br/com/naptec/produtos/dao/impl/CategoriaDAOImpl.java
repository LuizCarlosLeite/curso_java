package br.com.naptec.produtos.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.naptec.produtos.beans.Categoria;
import br.com.naptec.produtos.dao.CategoriaDAO;
import br.com.naptec.produtos.dao.util.ConectorUtil;

public class CategoriaDAOImpl implements CategoriaDAO {
	private PreparedStatement stm;
	private ResultSet rs;
	private StringBuilder query;
	private Connection con;
	
	public void gravar(Categoria e) throws Exception {
		try {
			query = new StringBuilder();
			query.append("INSERT INTO tb_categorias (nome, \n");
			query.append("descricao) VALUES(?, ?)");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(
					query.toString());
			stm.setString(1, e.getNome());
			stm.setString(2, e.getDescricao());
			stm.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				throw ex;
			}
		}
	}

	public void atualizar(Categoria e) throws Exception {
		try {
			Categoria aux = findById(e.getId());
			query = new StringBuilder();
			query.append("UPDATE tb_categorias SET \n");
			Map<Integer, Object> mapa = 
					new HashMap<Integer, Object>();
			int index = 0;
			if(!aux.getNome().equals(e.getNome())) {
				query.append("nome = ?");
				mapa.put(++index, e.getNome());
			}
			
			if(!aux.getDescricao().equals(e.getDescricao())) {
				if(query.toString().contains("=")) {
					query.append(", \n");
				}
				query.append("descricao = ?");
				mapa.put(++index, e.getDescricao());
			}
			query.append("\n WHERE id_categoria = ?");
			mapa.put(++index, e.getId());
			
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			Iterator<Integer> it = mapa.keySet().iterator();
			while(it.hasNext()) {
				int key = it.next();
				Object val = mapa.get(key);
				if(val instanceof String) {
					stm.setString(key, val.toString());
				} else if(val instanceof Long) {
					stm.setLong(key, new Long(val.toString()));
				}
			}
			stm.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				throw ex;
			}
		}
	}

	public void excluir(Long id) throws Exception {
		try {
			query = new StringBuilder();
			query.append("DELETE FROM tb_categorias \n");
			query.append("WHERE id_categoria = ?");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				con.close();
			} catch(Exception ex) {
				throw ex;
			}
		}
	}

	public Categoria findById(Long id) throws Exception {
		Categoria cat = null;
		try {
			query = new StringBuilder();
			query.append("SELECT id_categoria AS IDC, \n");
			query.append("nome AS NMC, descricao AS DSC \n");
			query.append("FROM tb_categorias \n");
			query.append("WHERE id_categoria = ?");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.setLong(1, id);
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				cat = new Categoria();
				cat.setId(rs.getLong("IDC"));
				cat.setNome(rs.getString("NMC"));
				cat.setDescricao(rs.getString("DSC"));
			}
		} catch (Exception ex) {
			throw ex;
		} finally {
			con.close();
		}
		return cat;
	}

	public List<Categoria> list() throws Exception {
		List<Categoria> list = new ArrayList<Categoria>();
		try {
			query = new StringBuilder();
			query.append("SELECT id_categoria AS IDC, \n");
			query.append("nome AS NMC, descricao AS DSC \n");
			query.append("FROM tb_categorias");
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				Categoria cat = new Categoria();
				cat.setId(rs.getLong("IDC"));
				cat.setNome(rs.getString("NMC"));
				cat.setDescricao(rs.getString("DSC"));
				list.add(cat);
			}
		} finally {
			con.close();
		}
		return list;
	}

	public List<Categoria> listByParams(Map<String, Object> params)
			throws Exception {
		List<Categoria> list = new ArrayList<Categoria>();
		try {
			query = new StringBuilder();
			query.append("SELECT id_categoria AS IDC, \n");
			query.append("nome AS NMC, descricao AS DSC \n");
			query.append("FROM tb_categorias \n");
			
			query.append("WHERE ");
			
			Iterator<String> it = params.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				Object value = params.get(key);
				if(query.toString().contains("?") && !it.hasNext()) {
					query.append(" AND ");
				}
				if(value instanceof String) {
					query.append(
						"UPPER(" + key + ") LIKE UPPER(?) \n");
				} else {
					query.append(key + " = ? \n");
				}
			}
			
			con = ConectorUtil.getConnection();
			stm = con.prepareStatement(query.toString());
			
			it = params.keySet().iterator();
			int cont = 1;
			while(it.hasNext()) {
				Object value = params.get(it.next());
				if(value instanceof String) {
					stm.setString(cont++, 
							"%" + value.toString() + "%");
				} else {
					stm.setLong(cont++, new Long(value.toString()));
				}
			}
			
			stm.execute();
			rs = stm.getResultSet();
			while(rs.next()) {
				Categoria cat = new Categoria();
				cat.setId(rs.getLong("IDC"));
				cat.setNome(rs.getString("NMC"));
				cat.setDescricao(rs.getString("DSC"));
				list.add(cat);
			}
		} finally {
			con.close();
		}
		return list;
	}

}








