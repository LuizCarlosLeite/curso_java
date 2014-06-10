package br.com.naptec.base_dados.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectorUtil {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String HOST = 
			"jdbc:oracle:thin:@localhost:1521:XE";
	//jdbc:<VENDOR>://<HOST>:<PORTA>/<BD>
	private static final String USER = "admin";
	private static final String PSW = "naptec";
	
	public static Connection criaConexao() throws SQLException, 
					ClassNotFoundException {
		Class.forName(DRIVER);
		Connection con = DriverManager.getConnection(HOST, USER, PSW);
		return con;
	}
	
	public static void main(String[] args) {
		try {
			Connection con = ConectorUtil.criaConexao();
			if(con != null) {
				System.out.println("Ok");
			} else {
				System.err.println("Erro");
			}
		} catch(SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}



