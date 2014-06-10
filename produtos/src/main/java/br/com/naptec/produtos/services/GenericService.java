package br.com.naptec.produtos.services;

import java.io.Serializable;
import java.util.List;

import br.com.naptec.produtos.beans.ClassePersistente;

public interface GenericService<E extends ClassePersistente, ID extends Serializable> {
	public void gravar(E e) throws Exception;
	public void excluir(ID id) throws Exception;
	public E findById(ID id) throws Exception;
	public List<E> list() throws Exception;
	public List<E> listByParams(E e) throws Exception;
}
