package Controlador;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Modelo.IlegalMuseoException;
import Modelo.Museos;
/**
 * @author Maria Jose Rodriguez Martinez
 */

public class Controlador {
	public static void main(String[] args) throws NumberFormatException, IlegalMuseoException {

		//inicializo la lista de museos cogiendo los datos de mi fichero
		List<Museos> lista = new ArrayList<Museos>();
		try (Scanner sc = new Scanner(new File("datosMuseos/datos.csv"));) {
			String datos = sc.nextLine();
			while (sc.hasNextLine()) {
				datos = sc.nextLine();
				System.out.println(datos);
				String[] campos = datos.split(",");
				lista.add(new Museos(Integer.parseInt(campos[0]), campos[1], campos[2], campos[3], campos[4]));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("fichero no encontrado");
		}

	}
}