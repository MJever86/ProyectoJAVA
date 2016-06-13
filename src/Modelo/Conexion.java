package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.sqlite.SQLiteConfig;
/**clase para establecer la conexion con la base de datos
 * @author Maria Jose Rodriguez Martinez
 */


//establecemos una conexion con la base de datos para que java se entienda con sqlite
public class Conexion {
	private static Connection conexion =null;
	//con esto nadie podrá crear objetos
	private Conexion(){}; 
	public static Connection getConexion(){
		//vamos a cerrar correctamente la conexion
		//usando un hook de la maquina virtual
		Runtime.getRuntime().addShutdownHook(new MiShutdownHuk());
		//usamos la filosofia del patron Singleton
		if (conexion == null){
			//trabajamos con un fichero de propiedades
			ResourceBundle rb = ResourceBundle.getBundle("sqlite");
			String url = rb.getString("url");
			String driver = rb.getString("driver");
			
			try {
				//cargamos el driver
				Class.forName(driver);
				//establecemos una configuracion particular para sqlite
				SQLiteConfig configuracion = new SQLiteConfig();
				configuracion.enforceForeignKeys(true);
				conexion = DriverManager.getConnection(url, configuracion.toProperties());
				//cargamos la BD
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conexion;
		
	}
	static class MiShutdownHuk extends Thread{

		@Override
		public void run() {
				if (conexion != null){
				try {
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
}
