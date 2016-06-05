package Modelo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

//clase que inserta datos en la tabla historial
public class CrearTrigger {
	private static Statement sentencia;
public static void crearTriggerBorrado(Connection con){
	//creamos  la tabla historial
			/*CREATE TABLE historial (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			nombre TEXT,
			apellidos TEXT,
			fecha_baja TEXT
			);*/
			String sql1 = "CREATE TABLE IF NOT EXISTS historial "
					+ "(Id INTEGER PRIMARY KEY AUTOINCREMENT,NombreMuseo TEXT, Direccion TEXT,"
					+ "Horario TEXT,"+"Telefono TEXT)";
			//creamos el trigger
			/*CREATE TRIGGER borrado BEFORE DELETE
				ON alumno
				BEGIN
				INSERT INTO historial (nombre, apellidos, fecha_baja)
					VALUES (old.nombre, old.apellidos, datetime(’now’));
				END;*/
			String sql2 = " CREATE TRIGGER IF NOT EXISTS borrado BEFORE DELETE "
					+ "ON museos"
					+ "BEGIN "
					+ "INSERT INTO historial (Id,NombreMuseo, Direccion, Horario, Telefono) "
					+ "VALUES (old.Id,old.NombreMuseo, old.Direccion,old.Horario,old.Telefono); "
					+ "END";
			try {
				sentencia = con.createStatement();
				sentencia.addBatch(sql1);
				sentencia.addBatch(sql2);
				sentencia.executeBatch();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
public static void crearTriggerActualizar(Connection con){
	//creamos  la tabla historial
			/*CREATE TABLE historial (
			id INTEGER PRIMARY KEY AUTOINCREMENT,
			nombre TEXT,
			apellidos TEXT,
			fecha_baja TEXT
			);*/
			String sql1 = "CREATE TABLE IF NOT EXISTS actualizados "
					+ "(NombreMuseo TEXT, Direccion TEXT,"
					+ "Horario TEXT,"+"Telefono TEXT)";
			//creamos el trigger
			/*CREATE TRIGGER borrado BEFORE DELETE
				ON alumno
				BEGIN
				INSERT INTO historial (nombre, apellidos, fecha_baja)
					VALUES (old.nombre, old.apellidos, datetime(’now’));
				END;*/
			String sql2 = " CREATE TRIGGER IF NOT EXISTS borrado BEFORE UPDATE "
					+ "ON museos"
					+ " BEGIN "
					+ "INSERT INTO actualizados (NombreMuseo, Direccion, Horario, Telefono)"
					+ " VALUES (NombreMuseo,Direccion,Horario,Telefono);"
					+ " END";
			try {
				sentencia = con.createStatement();
				sentencia.addBatch(sql1);
				sentencia.addBatch(sql2);
				sentencia.executeBatch();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

