package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SeleccionarDatos{
	private static Statement sentencia;
	public static void seleccionarDatos(Connection conexion){
	String sql="";
	}
	public static List<Museos> cargarDatosDesdeTabla(Connection conexion){
		List<Museos> lista= new ArrayList<Museos>();
		ResultSet res= null;
		String sql="SELECT * FROM museos;";
	
	try {
		sentencia = conexion.createStatement();
		res = sentencia.executeQuery(sql);
		while ( res.next()){
			lista.add(new Museos(res.getInt(1),
					res.getString(2),
					res.getString(3),
					res.getString(4),
					res.getString(5)));
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lista;
	}
		
}
