package br.com.naptec.produtos.services.impl;

import java.util.List;

import br.com.naptec.produtos.beans.Fornecedor;
import br.com.naptec.produtos.dao.FornecedorDAO;
import br.com.naptec.produtos.dao.impl.FornecedorDAOImpl;
import br.com.naptec.produtos.services.FornecedorService;
import br.com.naptec.produtos.utils.MapaUtil;

public class FornecedorServiceImpl implements FornecedorService {
	
	private FornecedorDAO dao;
	
	public FornecedorServiceImpl() {
		dao = new FornecedorDAOImpl();
	}

	public void gravar(Fornecedor e) throws Exception {
		if (null == e.getId()) {
			dao.gravar(e);
		} else {
			dao.atualizar(e);
		}
	}

	public void excluir(Long id) throws Exception {
		dao.excluir(id);
	}

	public Fornecedor findById(Long id) throws Exception {
		return dao.findById(id);
	}

	public List<Fornecedor> list() throws Exception {
		return dao.list();
	}

	public List<Fornecedor> listByParams(Fornecedor e)
			throws Exception {
		MapaUtil<Fornecedor> muc = new MapaUtil<Fornecedor>();
		return dao.listByParams(muc.convertObjectToMap(e));
	}

}
