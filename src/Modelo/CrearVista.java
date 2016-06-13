package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/** clase que crea una vista nueva de la tabla
 * @author Maria Jose Rodriguez Martinez
 */

//clase que crea una vista nueva de la tabla
public class CrearVista {
	private static Statement sentencia;
	public static void crearVista(Connection con){
		
		//metodo que crear una vista nueva con todos los datos
		String sql1="CREATE VIEW IF NOT EXISTS vistaMuseos as select * from museos";
				
		
		try {
			sentencia=con.createStatement();
			sentencia.execute(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
