package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Maria Jose Rodriguez Martinez
 * 
 */
public class MuseoDTO implements MuseoDAO {
	private static Statement sentencia;
	public  void addMuseos(Connection con, List<Museos> lista){
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
	public void addMuseos(Connection con, Museos museos){
		
			/*String sql = "INSERT INTO MUSEOS VALUES"
					+ "("+museos.getId()+
					","+"'"+museos.getNombreMuseo()+
					"',"+"'"+museos.getDireccion()+
					"',"+"'"+museos.getHorario()+
					"',"+"'"+museos.getTelefono()+"')";*/
			String sql = "INSERT INTO MUSEOS VALUES"
					+ "("+null+
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
	@Override
	public void updateMuseo(Connection con, Museos museos) {
		// TODO Auto-generated method stub
		String sql = "UPDATE museo SET "
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
	@Override
	public void removeMuseo(Connection con, int id) {
		// TODO Auto-generated method stub
		String sql = "delete from museos"+" where id="+id+"";
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
	@Override
	public List<Museos> cargarDatosDesdeTabla(Connection conexion) {
		// TODO Auto-generated method stub
		List<Museos> lista= new ArrayList<Museos>();
		ResultSet res= null;
		String sql="SELECT * FROM museos;";
	
	try {
		sentencia = conexion.createStatement();
		res = sentencia.executeQuery(sql);
		while ( res.next()){
			try {
				lista.add(new Museos(res.getInt(1),
						res.getString(2),
						res.getString(3),
						res.getString(4),
						res.getString(5)));
			} catch (IlegalMuseoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return lista;
	
	}
	
	
}
