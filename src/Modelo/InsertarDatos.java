package Modelo;
/*
 * @author Maria Jose Rodriguez Martinez
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Modelo.Museos;
//clase que inserta datos
public class InsertarDatos {
	private static Statement sentencia;
	public static void addMuseos(Connection con, List<Museos> lista){
		String sql= "";
		for (Museos museos : lista) {
			sql = "INSERT INTO MUSEOS VALUES"
					+ "("+museos.getId()+
					","+"'"+museos.getNombreMuseo()+
					"',"+"'"+museos.getDireccion()+
					"',"+"'"+museos.getHorario()+
					"',"+"'"+museos.getTelefono()+"')";
			try {
				sentencia = con.createStatement();
				sentencia.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
		}
	public static void addMuseos(Connection con, Museos museos){
		
			String sql = "INSERT INTO MUSEOS VALUES"
					+ "("+museos.getId()+
					","+"'"+museos.getNombreMuseo()+
					"',"+"'"+museos.getDireccion()+
					"',"+"'"+museos.getHorario()+
					"',"+"'"+museos.getTelefono()+"')";
			try {
				sentencia = con.createStatement();
				sentencia.executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	public static boolean comprobarDatos (Connection con){
		String sql= "SELECT * FROM museos";
		ResultSet res= null;
		try {
			sentencia = con.createStatement();
			res = sentencia.executeQuery(sql);
			return res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
}
}
