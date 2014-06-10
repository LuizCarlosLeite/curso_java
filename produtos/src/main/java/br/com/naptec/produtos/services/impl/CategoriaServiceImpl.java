package br.com.naptec.produtos.services.impl;

import java.util.List;

import br.com.naptec.produtos.beans.Categoria;
import br.com.naptec.produtos.dao.CategoriaDAO;
import br.com.naptec.produtos.dao.impl.CategoriaDAOImpl;
import br.com.naptec.produtos.services.CategoriaService;
import br.com.naptec.produtos.utils.MapaUtil;

public class CategoriaServiceImpl implements CategoriaService {

	private CategoriaDAO dao;
	
	public CategoriaServiceImpl() {
		dao = new CategoriaDAOImpl();
	}
	
	public void gravar(Categoria e) throws Exception {
		if(null == e.getId()) {
			dao.gravar(e);
		} else {
			dao.atualizar(e);
		}
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}

	public Categoria findById(Long id) throws Exception {
		return dao.findById(id);
	}

	public List<Categoria> list() throws Exception {
		return dao.list();
	}

	public List<Categoria> listByParams(Categoria e)
			throws Exception {
		MapaUtil<Categoria> muc = new MapaUtil<Categoria>();
		return dao.listByParams(muc.convertObjectToMap(e));
	}

}












