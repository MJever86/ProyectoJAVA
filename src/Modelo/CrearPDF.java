package Modelo;
/*
 * @author Maria Jose Rodriguez Martinez
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class CrearPDF {
	public static void CrearDocumentoPDF(File pdf,List<Museos> lista){
		//primero se crea el documento
		Document documento= new Document();
		
		try {
			FileOutputStream ficheroPdf= new FileOutputStream(pdf);
			PdfWriter.getInstance(documento, ficheroPdf).setInitialLeading(1);
			documento.open();
			for (Museos museos : lista) {
				documento.add(new Paragraph(museos.toString()));
				
			}
			documento.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
