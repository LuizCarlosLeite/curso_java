package br.com.naptec.produtos.utils;

import java.io.InputStream;
import java.util.Properties;

import br.com.naptec.produtos.dao.util.ConectorUtil;

public class LePropriedades {
	private static Properties props;
	private static InputStream is;

	public static Properties getProperties() throws Exception {
		is = ConectorUtil.class.getClassLoader().
				getResourceAsStream("jdbc.properties");
		props = new Properties();
		props.load(is);
		return props;
	}
}
