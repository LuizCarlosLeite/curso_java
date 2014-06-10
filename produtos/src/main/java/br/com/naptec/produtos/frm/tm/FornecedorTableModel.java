package br.com.naptec.produtos.frm.tm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.naptec.produtos.beans.Fornecedor;

public class FornecedorTableModel  extends AbstractTableModel {

	private static final long serialVersionUID = 8953961570008236079L;
	private List<Fornecedor> items = new ArrayList<Fornecedor>();
	
	public void addFornecedor(Fornecedor forn) {
		items.add(forn);
		fireTableDataChanged();
	}
	
	public void deleteFornecedor(int rowIndex) {
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
			return "Endereço";
		case 3:
			return "Telefone";
		case 4:
			return "Email";			
		default:
			return null;
		}
	}	

	@Override
	public int getRowCount() {
		return items.size();
	}

	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Fornecedor forn = items.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return forn.getId();
		case 1:
			return forn.getNome();
		case 2:
			return forn.getEndereco();
		case 3:
			return forn.getTelefone();
		case 4:
			return forn.getEmail();			
		}
		return null;
	}

	public void clean() {
		items.clear();
		fireTableDataChanged();
	}
	
	public void addMany(List<Fornecedor> items) {
		clean();
		this.items = items;
		fireTableDataChanged();
	}	
}
