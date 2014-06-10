package br.com.naptec.produtos.beans;

public class Categoria extends ClassePersistente {
	private static final long serialVersionUID = 2529417844370502742L;
	private String nome;
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
