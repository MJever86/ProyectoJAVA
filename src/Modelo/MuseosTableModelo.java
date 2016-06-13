package Modelo;

import java.util.List;

import javax.swing.table.AbstractTableModel;
/**clase que muestra nuestra tabla en la base de datos
 * 
 * @author Maria Jose Rodriguez Martinez
 */

public class MuseosTableModelo extends AbstractTableModel{
	private String[] columNames={"Id","NombreMuseo","Direccion","Telefono","Informacion"};
	private List<Museos> lista;
	
	public MuseosTableModelo (List<Museos> lista){
		this.lista= lista;
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
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
		/*case 0:
			return m.getId();*/
		case 0:
			return m.getNombreMuseo();
		case 1:
			return m.getDireccion();
		case 2: 
			return m.getHorario();
		case 3:
			return m.getTelefono();
		
		}
		return 0;
	}
	
}
