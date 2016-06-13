package Modelo;

import java.sql.Connection;
import java.util.List;

/**
 * @author Maria Jose Rodriguez Martinez
 */

//interfaz que se implementara en la clase MuseoDTO

public interface MuseoDAO {
		//metodo que permite la realizacion de inserts en la base de datos 
	
  void addMuseos(Connection con, List<Museos> lista);
	void addMuseos(Connection con, Museos museos);
	
	//metodo que permite la modificacion de un museo a traves de update

	 void updateMuseo(Connection con, Museos museos);
	
	//metodo que permite el borrado de museos de la tabla mediante el uso de delete
	 void removeMuseo(Connection con, int id);
	
	//metodo que realiza una consulta select sobre todo los elementos de la tabla 
	 List<Museos> cargarDatosDesdeTabla(Connection conexion);

}
