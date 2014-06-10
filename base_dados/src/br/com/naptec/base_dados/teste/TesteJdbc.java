package br.com.naptec.base_dados.teste;

import java.util.List;

import javax.swing.JOptionPane;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.base_dados.dao.PessoaDAO;

public class TesteJdbc {
	public static void main(String[] args) {
		String nome = JOptionPane.showInputDialog("Nome");
		String email = JOptionPane.showInputDialog("Email");
		int idade = Integer.parseInt(
				JOptionPane.showInputDialog("Idade"));
		
		Pessoa pes = new Pessoa();
		pes.setNome(nome);
		pes.setEmail(email);
		pes.setIdade(idade);
		
		PessoaDAO pd = new PessoaDAO();
		pd.gravar(pes);
		
		List<Pessoa> pessoas = pd.listar();
		
		System.out.println("Nome\t\tEmail");
		for(Pessoa p : pessoas) {
			System.out.println(p.getNome() + "\t\t" + p.getEmail());
		}
	}
}










