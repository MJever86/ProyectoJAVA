package Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.SysoCounter;
import com.itextpdf.text.pdf.PdfWriter;
/**
 * Clase para elaborar los pdf 
 * @author Maria Jose Rodriguez Martinez
 */

public class CrearPDF {
	public static void CrearDocumentoPDF(File pdf,List<Museos> lista){
		//primero se crea el documento
		try {
			Document documento= new Document();
			FileOutputStream ficheroPdf= new FileOutputStream(pdf);
			PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(1);
			documento.open();
			for (Museos museos : lista) {
				documento.add(new Paragraph(museos.toString()));
				
			}
			documento.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.format("El archivo %s no ha podido ser creado",  pdf);
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
