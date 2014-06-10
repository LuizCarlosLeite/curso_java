package br.com.naptec.produtos.frm.tm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.naptec.produtos.beans.Categoria;

public class CategoriaTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -5277692202067910019L;
	private List<Categoria> items = new ArrayList<Categoria>();
	
	public void addCategoria(Categoria cat) {
		items.add(cat);
		fireTableDataChanged();
	}
	
	public void deleteCategoria(int rowIndex) {
		items.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public String getColumnName(int colIndex) {
		switch(colIndex) {
		case 0:
			return "Código";
		case 1:
			return "Nome";
		case 2:
			return "Descrição";
			default:
				return null;
		}
	}
	
	public int getColumnCount() {
		return 3;
	}
	
	public int getRowCount() {
		return items.size();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Categoria cat = items.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return cat.getId();
		case 1:
			return cat.getNome();
		case 2:
			return cat.getDescricao();
		}
		return null;
	}
	
	public void clean() {
		items.clear();
		fireTableDataChanged();
	}
	
	public void addMany(List<Categoria> items) {
		clean();
		this.items = items;
		fireTableDataChanged();
	}
}









