package br.com.naptec.produtos.frm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.naptec.produtos.beans.Categoria;
import br.com.naptec.produtos.frm.tm.CategoriaTableModel;
import br.com.naptec.produtos.services.CategoriaService;
import br.com.naptec.produtos.services.impl.CategoriaServiceImpl;

public class FrmCategoria extends JFrame {

	private static final long serialVersionUID = -2453671231342218257L;
	private JPanel contentPane;
	private JTextField txtCodCat;
	private JTextField txtNomeCat;
	private JTable tblCategorias;
	private CategoriaTableModel ctm;
	private CategoriaService catServ;
	private JTextArea txtDescCat;
	private JButton btnPesquisar;
	private JButton btnGravar;
	private JButton btnEditar;
	private JButton btnExcluir;

	/**
	 * Create the frame.
	 */
	public FrmCategoria() {
		ctm = new CategoriaTableModel();
		
		catServ = new CategoriaServiceImpl();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 490, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodCat = new JTextField();
		txtCodCat.setBorder(new TitledBorder(null, "C\u00F3digo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCodCat.setBounds(10, 11, 70, 45);
		contentPane.add(txtCodCat);
		txtCodCat.setColumns(10);
		
		txtNomeCat = new JTextField();
		txtNomeCat.setBorder(new TitledBorder(null, "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNomeCat.setBounds(90, 11, 374, 45);
		contentPane.add(txtNomeCat);
		txtNomeCat.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 67, 320, 60);
		contentPane.add(scrollPane);
		
		txtDescCat = new JTextArea();
		txtDescCat.setBorder(new TitledBorder(null, "Descri\u00E7\u00E3o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollPane.setViewportView(txtDescCat);
		
		btnPesquisar = new JButton("?");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(343, 67, 56, 60);
		contentPane.add(btnPesquisar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 138, 450, 230);
		contentPane.add(scrollPane_1);
		
		tblCategorias = new JTable(ctm);
		ajustaTabela(tblCategorias);
		scrollPane_1.setViewportView(tblCategorias);
		
		btnGravar = new JButton("+");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
		btnGravar.setBounds(408, 67, 56, 60);
		contentPane.add(btnGravar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(10, 379, 100, 23);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(360, 379, 100, 23);
		contentPane.add(btnExcluir);
	}
	
	protected void excluir() {
		try {
			Categoria cat = obtemCategoriaSelecionada();
			catServ.excluir(cat.getId());
			listar();
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	protected void editar() {
		try {
			Categoria cat = obtemCategoriaSelecionada();
			txtCodCat.setText(cat.getId().toString());
			txtCodCat.setEditable(false);
			txtNomeCat.setText(cat.getNome());
			txtDescCat.setText(cat.getDescricao());
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}
	
	private Categoria obtemCategoriaSelecionada() throws Exception {
		int linha = tblCategorias.getSelectedRow();
		Categoria cat = catServ.findById((Long) ctm.getValueAt(linha, 0));
		return cat;
	}

	protected void gravar() {
		try {
			String sCod = txtCodCat.getText();
			String sNome = txtNomeCat.getText();
			String sDesc = txtDescCat.getText();
			Categoria cat = new Categoria();
			cat.setNome(sNome);
			cat.setDescricao(sDesc);
			if(null != sCod && !"".equals(sCod.trim())) {
				cat.setId(Long.parseLong(sCod));
			}
			catServ.gravar(cat);
			limpar();
			pesquisar();
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	private void limpar() {
		txtCodCat.setText("");
		txtNomeCat.setText("");
		txtDescCat.setText("");
	}

	private void ajustaTabela(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(300);
	}

	protected void pesquisar() {
		try {
			String sCod = txtCodCat.getText();
			String sNome = txtNomeCat.getText();
			String sDesc = txtDescCat.getText();
			
			if("".equals(sCod) && "".equals(sNome) && 
					"".equals(sDesc)) {
				listar();
			} else if(!"".equals(sCod)) {
				buscarPorId(sCod);
			} else {
				listarPorParam(sNome, sDesc);
			}
			
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}
	
	private void buscarPorId(String sId) throws Exception {
		Categoria cat = catServ.findById(Long.parseLong(sId));
		List<Categoria> cats = new ArrayList<Categoria>();
		cats.add(cat);
		ctm.addMany(cats);
	}

	private void listar() throws Exception {
		ctm.addMany(catServ.list());
	}

	private void listarPorParam(String sNome, String sDesc)
		throws Exception {
		Categoria cat = new Categoria();
		if(null != sNome && !"".equals(sNome.trim())) {
			cat.setNome(sNome);
		} 
		if(null != sDesc && !"".equals(sDesc.trim())) {
			cat.setDescricao(sDesc);
		}
		ctm.addMany(catServ.listByParams(cat));
	}

	private void trataExcecao(Exception ex) {
		JOptionPane.showMessageDialog(this, ex.getMessage(), 
				"Erro", JOptionPane.ERROR_MESSAGE);
	}
}









