package br.com.naptec.produtos.frm;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class FrmPrincipal extends JFrame {

	private JPanel contentPane;
	private JMenuItem mntmSair;
	private JMenuItem mntmCategoria;
	private JMenu mnArquivo;
	private JMenuBar menuBar;
	private JMenuItem mntmFornecedor;
	private FrmCategoria frmCat;
	private FrmFornecedor frmForn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmPrincipal frame = new FrmPrincipal();
					frame.setLocationRelativeTo(null);
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
	public FrmPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 415);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu("Arquivo");
		menuBar.add(mnArquivo);
		
		mntmCategoria = new JMenuItem("Categoria");
		mntmCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == frmCat) {
					frmCat = new FrmCategoria();
				}
				frmCat.setLocationRelativeTo(null);
				frmCat.setVisible(true);
			}
		});
		mntmCategoria.setMnemonic('C');
		mntmCategoria.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnArquivo.add(mntmCategoria);
		
		mntmFornecedor = new JMenuItem("Fornecedor");
		mntmFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null == frmForn) {
					frmForn = new FrmFornecedor();
				}
				frmForn.setLocationRelativeTo(null);
				frmForn.setVisible(true);				
			}
		});
		mntmFornecedor.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.ALT_MASK));
		mnArquivo.add(mntmFornecedor);
		
		JSeparator separator = new JSeparator();
		mnArquivo.add(separator);
		
		mntmSair = new JMenuItem("Sair");
		mntmSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encerrar();
			}
		});
		mntmSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSair.setMnemonic('S');
		mnArquivo.add(mntmSair);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	protected void encerrar() {
		if(JOptionPane.YES_OPTION ==
				JOptionPane.showConfirmDialog(this, 
						"Deseja realmente sair do sistema?",
						"Confirmação",
						JOptionPane.YES_NO_OPTION)){
			//Quando o valor é zero (0), saída normal do sistema
			//Quando a saída é maior que zero, saída com alerta
			//Quando a saída é menor que zero, saída com erro
			System.exit(0);
		}
	}

}














