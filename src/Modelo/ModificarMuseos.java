package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Museos;;
/**
 * @author Maria Jose Rodriguez Martinez
 */
public class ModificarMuseos {
private static Statement sentencia;
	
	public static void updateMuseo(Connection con, Museos museos){
		
		String sql = "UPDATE trabajador SET "
				+"nombre='"+museos.getNombreMuseo()+"',"
				+"direccion='"+museos.getDireccion()+"',"
				+"horario='"+museos.getHorario()+"',"
				+"telefono="+museos.getTelefono()+","
				+"where id="+museos.getId()+"";
		try {
			sentencia = con.createStatement();
			sentencia.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			try {
				sentencia = con.createStatement();
				sentencia.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}
