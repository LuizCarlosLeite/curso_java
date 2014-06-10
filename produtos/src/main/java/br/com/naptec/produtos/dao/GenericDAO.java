package br.com.naptec.produtos.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import br.com.naptec.produtos.beans.ClassePersistente;

public interface GenericDAO<E extends ClassePersistente, ID extends Serializable> {
	public void gravar(E e) throws Exception;
	public void atualizar(E e) throws Exception;
	public void excluir(ID id) throws Exception;
	public E findById(ID id) throws Exception;
	public List<E> list() throws Exception;
	public List<E> listByParams(Map<String, Object> params) throws Exception;
}
