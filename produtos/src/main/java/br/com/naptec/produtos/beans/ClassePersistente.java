package br.com.naptec.produtos.beans;

import java.io.Serializable;

public class ClassePersistente implements Serializable {
	private static final long serialVersionUID = 8419211249251257419L;
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
