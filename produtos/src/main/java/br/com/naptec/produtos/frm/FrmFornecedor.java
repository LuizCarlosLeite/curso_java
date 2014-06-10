package br.com.naptec.produtos.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import br.com.naptec.produtos.beans.Fornecedor;
import br.com.naptec.produtos.frm.tm.FornecedorTableModel;
import br.com.naptec.produtos.services.FornecedorService;
import br.com.naptec.produtos.services.impl.FornecedorServiceImpl;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.Component;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;

public class FrmFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JTable tblFornecedores;
	private JButton btnPesquisar;
	private JButton btnGravar;
	private JScrollPane scrollPane;
	private FornecedorTableModel ftm;
	private FornecedorService fornServ;


	/**
	 * Create the frame.
	 */
	public FrmFornecedor() {
		setTitle("Cadastro de fornecedores");
		ftm = new FornecedorTableModel();	
		
		fornServ = new FornecedorServiceImpl();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 598, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtCodigo = new JTextField();
		txtCodigo.setBorder(new TitledBorder(null, "C\u00F3digo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtCodigo.setBounds(10, 11, 70, 45);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBorder(new TitledBorder(null, "Nome", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txtNome.setBounds(90, 11, 237, 45);
		contentPane.add(txtNome);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtEndereco.setBounds(337, 11, 237, 45);
		contentPane.add(txtEndereco);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Telefone", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtTelefone.setBounds(10, 67, 190, 45);
		contentPane.add(txtTelefone);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Email", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtEmail.setBounds(210, 67, 232, 45);
		contentPane.add(txtEmail);
		
		btnPesquisar = new JButton("");
		btnPesquisar.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		btnPesquisar.setAlignmentX(Component.CENTER_ALIGNMENT);
		try {
			btnPesquisar.setIcon(new ImageIcon("E:\\Java\\icones\\search.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(452, 67, 56, 45);
		contentPane.add(btnPesquisar);
		
		btnGravar = new JButton("");
		try {
			btnGravar.setIcon(new ImageIcon("E:\\Java\\icones\\save.gif"));
		} catch (Exception e) {
			e.printStackTrace();
		}		
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
		btnGravar.setBounds(518, 67, 56, 45);
		contentPane.add(btnGravar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 123, 564, 268);
		contentPane.add(scrollPane);
		
		tblFornecedores = new JTable(ftm);
		tblFornecedores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				editar();
			}
		});
		scrollPane.setViewportView(tblFornecedores);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(10, 402, 89, 23);
		contentPane.add(btnEditar);
		
		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(485, 402, 89, 23);
		contentPane.add(btnExcluir);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novo();
			}
		});
		btnNovo.setBounds(109, 402, 89, 23);
		contentPane.add(btnNovo);
	}

	protected void novo() {
		try {
			limpar();
			txtCodigo.setEditable(true);		
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	protected void excluir() {
		try {
			Fornecedor forn = obtemFornecedorSelecionado();
			fornServ.excluir(forn.getId());
			listar();
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	protected void editar() {
		try {
			Fornecedor forn = obtemFornecedorSelecionado();
			txtCodigo.setText(forn.getId().toString());
			txtCodigo.setEditable(false);
			txtNome.setText(forn.getNome());
			txtEndereco.setText(forn.getEndereco());
			txtTelefone.setText(forn.getTelefone());
			txtEmail.setText(forn.getEmail());				
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	private Fornecedor obtemFornecedorSelecionado() throws Exception {
		int linha = tblFornecedores.getSelectedRow();
		Fornecedor forn = fornServ.findById((Long) ftm.getValueAt(linha, 0));
		return forn;
	}

	protected void gravar() {
		try {
			String sCod = txtCodigo.getText();
			String sNome = txtNome.getText();
			String sEnd = txtEndereco.getText();
			String sTel = txtTelefone.getText();
			String sEmail = txtEmail.getText();
			Fornecedor forn = new Fornecedor();
			if(null != sCod && !"".equals(sCod.trim())) {
				forn.setId(Long.parseLong(sCod));
			}
			forn.setNome(sNome);
			forn.setEndereco(sEnd);
			forn.setTelefone(sTel);
			forn.setEmail(sEmail);
			fornServ.gravar(forn);
			limpar();
			pesquisar();		
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	private void limpar() {
		txtCodigo.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");		
	}

	protected void pesquisar() {
		try {
			String sCod = txtCodigo.getText();
			String sNome = txtNome.getText();
			String sEnd = txtEndereco.getText();
			String sTel = txtTelefone.getText();
			String sEmail = txtEmail.getText();
			
			if("".equals(sCod) && "".equals(sNome) && 
					"".equals(sEnd) && "".equals(sTel) && "".equals(sEmail)) {
				listar();
			} else if(!"".equals(sCod)) {
				buscarPorId(sCod);
			} else {
				listarPorParam(sNome, sEnd, sTel, sEmail);
			}
			
		} catch (Exception ex) {
			trataExcecao(ex);
		}
	}

	private void trataExcecao(Exception ex) {
		JOptionPane.showMessageDialog(this, ex.getMessage(), 
				"Erro", JOptionPane.ERROR_MESSAGE);		
	}

	private void listarPorParam(String sNome, String sEnd, String sTel,
			String sEmail) throws Exception {
		Fornecedor forn = new Fornecedor();
		if (null != sNome && !"".equals(sNome.trim())) {
			forn.setNome(sNome);
		}
		if (null != sEnd && !"".equals(sEnd.trim())) {
			forn.setEndereco(sEnd);
		}
		if (null != sTel && !"".equals(sTel.trim())) {
			forn.setTelefone(sTel);
		}
		if (null != sEmail && !"".equals(sEmail.trim())) {
			forn.setEndereco(sEmail);
		}		
		ftm.addMany(fornServ.listByParams(forn));
	}

	private void buscarPorId(String sCod) throws Exception {
		Fornecedor forn = fornServ.findById(Long.parseLong(sCod));
		List<Fornecedor> forns = new ArrayList<Fornecedor>();
		forns.add(forn);
		ftm.addMany(forns);
	}

	private void listar() throws Exception {
		ftm.addMany(fornServ.list());
	}
}
