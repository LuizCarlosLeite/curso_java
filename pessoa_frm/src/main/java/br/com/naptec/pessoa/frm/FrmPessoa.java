package br.com.naptec.pessoa.frm;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.naptec.base_dados.beans.Pessoa;
import br.com.naptec.pessoa.services.PessoaService;

public class FrmPessoa extends JFrame {

	private static final long serialVersionUID = 8329107086178358152L;
	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTextField txtIdade;
	private JTextField txtEmail;
	private final JButton btnLimpar = new JButton("Limpar");
	private JButton btnGravar;
	private PessoaService service;
	private JButton btnEditar;
	private JButton btnPesquisar;
	private Pessoa pessoa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPessoa frame = new FrmPessoa();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmPessoa() {
		//Instanciar o objeto service da classe PessoaService
		service = new PessoaService();
		//Instanciar o objeto pesspa da classe Pessoa
		pessoa = new Pessoa();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 430, 210);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		lblCdigo.setBounds(10, 11, 55, 20);
		contentPane.add(lblCdigo);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(75, 11, 55, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 42, 55, 20);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(75, 42, 329, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblIdade = new JLabel("Idade");
		lblIdade.setBounds(10, 73, 55, 20);
		contentPane.add(lblIdade);
		
		txtIdade = new JTextField();
		txtIdade.setBounds(75, 73, 55, 20);
		contentPane.add(txtIdade);
		txtIdade.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 104, 55, 20);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(75, 104, 329, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(10, 140, 100, 23);
		contentPane.add(btnLimpar);
		
		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(304, 140, 100, 23);
		btnGravar.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
		contentPane.add(btnGravar);
		
		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisar();
			}
		});
		btnPesquisar.setBounds(304, 10, 100, 23);
		contentPane.add(btnPesquisar);
		
		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editar();
			}
		});
		btnEditar.setBounds(156, 140, 100, 23);
		contentPane.add(btnEditar);
	}
	
	protected void editar() {
		txtNome.setEditable(true);
		txtIdade.setEditable(true);
		txtEmail.setEditable(true);
	}
	
	protected void pesquisar() {
		try {
			String sCod = txtCodigo.getText();
			if("".equals(sCod.trim())) {
				throw new Exception("Campo Código é obrigatório");
			}
			Long cod = Long.parseLong(sCod);
			pessoa = service.buscarPorId(cod);
			
			if(null == pessoa || pessoa.getId() == null) {
				throw new Exception("Nenhum registro encontrado!");
			}
			
			//Definindo os valores dos campos
			txtCodigo.setText(pessoa.getId().toString());
			txtNome.setText(pessoa.getNome());
			txtIdade.setText(pessoa.getIdade() + "");
			txtEmail.setText(pessoa.getEmail());
			
			//Desabilitando os campos para edição
			txtCodigo.setEditable(false);
			txtNome.setEditable(false);
			txtIdade.setEditable(false);
			txtEmail.setEditable(false);
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, 
					ex.getMessage());
		}
	}
	
	protected void gravar() {
		try {
			//O método getText() devolve uma String com o valor          
			//do JTextField
			pessoa.setNome(txtNome.getText());
			pessoa.setEmail(txtEmail.getText());
			pessoa.setIdade(Integer.parseInt(txtIdade.getText()));
			
//			String sId = txtCodigo.getText().trim();
//			//Se não é String vazia
//			if(!"".equals(sId)) {
//				p.setId(Long.parseLong(sId));
//			}
			
			service.gravar(pessoa);
			JOptionPane.showMessageDialog(this, 
					"Registro gravado com sucesso!", 
					"Informação",
					JOptionPane.INFORMATION_MESSAGE);
			limparCampos();
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, 
					ex.getMessage());
		}
	}
	
	protected void limparCampos() {
		//setText(String): Define o texto do elemento JTextField
		this.txtCodigo.setText("");
		this.txtEmail.setText("");
		this.txtIdade.setText("");
		this.txtNome.setText("");
		
		txtCodigo.setEditable(true);
		txtNome.setEditable(true);
		txtEmail.setEditable(true);
		txtIdade.setEditable(true);
		
		pessoa = new Pessoa();
	}

	protected void alerta() {
		//this = esta instância de objeto da classe FrmPessoa
		JOptionPane.showMessageDialog(this, "Funciona!");		
	}
}












