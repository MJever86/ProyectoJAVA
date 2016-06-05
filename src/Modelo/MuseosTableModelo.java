package Modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;

public class MuseosTableModelo extends AbstractTableModel{
	private String[] columNames={"id","Nombre Museo","Direccion","Telefono","Información"};
	private List<Museos> lista;
	
	public MuseosTableModelo (List<Museos> lista){
		this.lista= lista;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}
	@Override
	public Object getValueAt(int arg0, int arg1) {
		// TODO Auto-generated method stub
		Museos m=lista.get(arg0);
		switch(arg1){
		case 0:
			return m.getId();
		case 1:
			return m.getNombreMuseo();
		case 2:
			return m.getDireccion();
		case 3: 
			return m.getHorario();
		case 4:
			return m.getTelefono();
		
		}
		return 0;
	}
	
}
