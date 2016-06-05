package Modelo;

/*
 * @author Maria Jose Rodriguez Martinez
 */
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CrearTablas {
	private static Statement sentencia;
	public static void crearTablaMuseos(Connection con){
		
		String sql1="CREATE TABLE IF NOT EXISTS museos("
				+"id INTEGER,"
				+"nombre TEXT,"
				+"direccion TEXT,"
				+"horario TEXT,"
				+"telefono TEXT)";
		
		try {
			sentencia=con.createStatement();
			sentencia.execute(sql1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
