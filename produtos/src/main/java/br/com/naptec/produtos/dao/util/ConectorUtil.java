package br.com.naptec.produtos.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

import br.com.naptec.produtos.utils.LePropriedades;

public class ConectorUtil {

	public static Connection getConnection() throws Exception {
		Connection con = null;
		try {
			Class.forName(getProperty("driver"));
			con = DriverManager.getConnection(getProperty("host"), 
					getProperty("username"), 
					getProperty("password"));
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
		return con;
	}

	private static String getProperty(String propertyName) 
			throws Exception {
		return LePropriedades.getProperties().
				getProperty(propertyName)
				.toString();
	}
	
	public static void main(String[] args) {
		try {
			Connection con = ConectorUtil.getConnection();
			JOptionPane.showMessageDialog(null, 
				con == null ? "Erro" : "Conexão ok");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}







