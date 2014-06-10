package br.com.naptec.produtos.utils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.naptec.produtos.beans.Categoria;

public class MapaUtil<E> {
	
	public Map<String, Object> convertObjectToMap(E e) throws
		Exception {		
		Map<String, Object> mapa = new HashMap<String, Object>();
		
		List<Method> methods = Arrays.asList(
				e.getClass().getDeclaredMethods());
		/*
		 * getWidget
		 * */
		for(Method m : methods) {
			if(m.getName().startsWith("get")) {
				Object value = m.invoke(e, null);
				if(value != null) {
					mapa.put(m.getName().substring(3, 
							m.getName().length()).toLowerCase(), 
							value);
				}
			}
		}
		
		return mapa;
	}
	
	public static void main(String[] args) throws Exception {
		Categoria c = new Categoria();
		c.setNome("Categoria 1");
		c.setDescricao("Descrição");
		c.setId(10L);
		
		MapaUtil<Categoria> muc = new MapaUtil<Categoria>();
		Map<String, Object> res = muc.convertObjectToMap(c);
		
		Iterator<String> itm = res.keySet().iterator();
		while(itm.hasNext()) {
			String key = itm.next();
			System.out.println(key + " : " + res.get(key));
		}
	}
	
}









