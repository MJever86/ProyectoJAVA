package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
/** clase que crea la tabla correspondiente en la base de datos
 * @author Maria Jose Rodriguez Martinez
 */

public class CrearTablas {
	private static Statement sentencia;
	public static void crearTablaMuseos(Connection con){
		
		String sql1="CREATE TABLE IF NOT EXISTS museos("
				+"Id INTEGER PRIMARY KEY AUTOINCREMENT,"
				+"NombreMuseo TEXT,"
				+"Direccion TEXT,"
				+"Horario TEXT,"
				+"Telefono TEXT)";
		
		try {
			sentencia=con.createStatement();
			sentencia.execute(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
