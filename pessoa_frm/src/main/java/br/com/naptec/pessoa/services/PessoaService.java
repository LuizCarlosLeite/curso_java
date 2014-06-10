package br.com.naptec.pessoa.services;

import java.util.List;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.base_dados.dao.PessoaDAO;

public class PessoaService {
	private PessoaDAO pDao;
	
	public PessoaService() {
		pDao = new PessoaDAO();
	}
	
	public void gravar(Pessoa pes) throws Exception {
		if(pes.getId() == null) {
			pDao.gravar(pes);
		} else {
			pDao.atualizar(pes);
		}
	}
	
	public void excluir(Long id) throws Exception {
		pDao.excluir(id);
	}
	
	public Pessoa buscarPorId(Long id) throws Exception {
		return pDao.buscarPorId(id);
	}
	
	public List<Pessoa> listar() throws Exception {
		return pDao.listar();
	}
}








