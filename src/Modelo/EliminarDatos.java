package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class EliminarDatos {
	private static Statement sentencia;
	
	public static void removeMuseo(Connection con, Museos museos){
		
			String sql = "delete from museos"+" where id="+museos.getId()+"";
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
