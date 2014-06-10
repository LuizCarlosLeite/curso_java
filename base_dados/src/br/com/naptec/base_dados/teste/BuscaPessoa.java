package br.com.naptec.base_dados.teste;

import javax.swing.JOptionPane;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.base_dados.dao.PessoaDAO;

public class BuscaPessoa {
	public static void main(String[] args) {
		Long id = Long.parseLong(
				JOptionPane.showInputDialog("Código"));
		
		PessoaDAO pd = new PessoaDAO();
		Pessoa p = pd.buscarPorId(id);
		
		if(null != p && null != p.getId()) {
			int opc = JOptionPane.showConfirmDialog(null, 
					"Deseja excluir o registro: " +
					"\nNome: " + p.getNome() + 
					"\nEmail: " + p.getEmail() +
					"\nIdade: " + p.getIdade() + 
					"\n?", 
					"Confirmação",
					JOptionPane.YES_NO_OPTION);
			if(JOptionPane.YES_OPTION == opc) {
				pd.excluir(p.getId());
			}
		} else {
			JOptionPane.showMessageDialog(null,
						"Registro não encontrado!");
		}
	}
}




