package Vista;

/**
 * @author Maria Jose Rodriguez Martinez
 * 
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Modelo.Conexion;
import Modelo.CrearPDF;
import Modelo.CrearTablas;
import Modelo.CrearTrigger;
import Modelo.EliminarDatos;
import Modelo.InsertarDatos;
import Modelo.Museos;
import Modelo.MuseosTableModelo;
import Modelo.SeleccionarDatos;

import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.Window.Type;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Container;


import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;

public class MuseosSevilla extends JFrame {

	private JPanel contentPane;
	private JDialog dialogo;
	private MuseosSevilla frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public static List<Museos> lista= new ArrayList <Museos>();
	private MuseosTableModelo model;
	private JTable tabla= new JTable();
	private JSplitPane splitPane= new JSplitPane();
	private JScrollPane scrollPane = new JScrollPane(tabla);
	private Connection conexion= Conexion.getConexion();
	 File fichero;
	//private JTable table;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MuseosSevilla frame = new MuseosSevilla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MuseosSevilla() {
		//icono del programa
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/giral.png"));
		setForeground(Color.BLACK);
		//titulo del programa		
		setTitle("MUSEOS DE SEVILLA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//cambiar las medidas del JFrame
		setBounds(100, 100, 800, 500);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.BLACK);
		setJMenuBar(menuBar);
		
		
		JMenu mnNewMenu = new JMenu("Datos");
		menuBar.add(mnNewMenu);
		//se crea la tabla 
		CrearTablas.crearTablaMuseos(conexion);
		
		
            
        
		//metodo que carga los datos
		JMenuItem mntmCargarDatos = new JMenuItem("Cargar datos");
		
		//aqui se verifica si la base de datos contiene datos, si es asi se bloquea
		if (InsertarDatos.comprobarDatos(conexion)){
            mntmCargarDatos.setEnabled(false);
            lista=SeleccionarDatos.cargarDatosDesdeTabla(conexion);
            //InsertarDatos.addMuseos(conexion, lista);
            model = new MuseosTableModelo(lista);
            tabla.setModel(model);
            
        }
		//System.out.println(InsertarDatos.comprobarDatos(conexion));
		mntmCargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				//filtro para que solo coja este tipo de ficheros
				FileNameExtensionFilter filter= new FileNameExtensionFilter("csv", "CSV Files","CSV");
				chooser.setFileFilter(filter);
				int returnVal= chooser.showOpenDialog(menuBar);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					System.out.println("Has elegido este fichero: "+chooser.getSelectedFile().getName());
					chooser.getSelectedFile().getName();
					fichero=chooser.getSelectedFile();
					
				//File fichero= new File("datosMuseos/datos.csv");
				try {
					//codificacion del fichero que se lee tanto en windows como en linux
					Scanner sc= new Scanner(fichero, "ISO-8859-1");
					
					//nos saltamos la primera linea
					//String palabra= sc.nextLine();
					String []palabraseparada=null;
					while (sc.hasNextLine()){
						String palabra=sc.nextLine();
						
						palabraseparada=palabra.split(";");
						lista.add(new Museos(Integer.parseInt(palabraseparada[0]), palabraseparada[1], palabraseparada[2],palabraseparada[3],palabraseparada[4]));
						//System.out.println(lista);		
					}
					model= new MuseosTableModelo(lista);
					tabla.setModel(model);
					//metodo que deshabilita el boton cargar datos.
					mntmCargarDatos.setEnabled(false);
					System.out.println(lista);
					InsertarDatos.addMuseos(conexion, lista);
					//llamamos a los triggers y a crear vista
					//CrearTrigger.crearTriggerActualizar(conexion);
					CrearTrigger.crearTriggerBorrado(conexion);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
			
		});
		//splitPane.setLeftComponent(scrollPane);

		getContentPane().add(splitPane);
		
		mnNewMenu.add(mntmCargarDatos);
		
		JMenu mnRegistro = new JMenu("Registro");
		menuBar.add(mnRegistro);
		
		//Atrasa de un registro a otro hacia atras
		JMenuItem mntmAnterior = new JMenuItem("Anterior");
		mntmAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabla!=null){
					if(tabla.getSelectedRow()>0){
						tabla.setRowSelectionInterval(tabla.getSelectedRow()-1, tabla.getSelectedRow()-1);
					}
					
				}
			}
		});
		mnRegistro.add(mntmAnterior);
		
		//Avanza de un resistro a otro hacia adelante
		JMenuItem mntmSiguiente = new JMenuItem("Siguiente");
		mntmSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabla!=null){
					if(tabla.getSelectedRow()>0){
						tabla.setRowSelectionInterval(tabla.getSelectedRow()+1, tabla.getSelectedRow()+1);
					}
				}
			}
		});
		mnRegistro.add(mntmSiguiente);
		
		//Añadir nuevo registro a nuestra tabla
		JMenuItem mntmNuevo = new JMenuItem("Nuevo");
		mntmNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.add(new Museos(lista.size()+1,"", "", "", ""));
				tabla.repaint();
				
			}
		});
		mnRegistro.add(mntmNuevo);
		
		//Elimina un registro de nuestra tabla
		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				lista.remove(tabla.getSelectedRow());
				EliminarDatos.removeMuseo(conexion, lista.get(tabla.getSelectedRow()));
			}
		});
		mnRegistro.add(mntmEliminar);
		
		JMenu mnInforme = new JMenu("Informe");
		menuBar.add(mnInforme);
		
		//Genera un pdf
		JMenuItem mntmGenerarPdf = new JMenuItem("Generar PDF");
		mnInforme.add(mntmGenerarPdf);
		
		//metodo para generar PDF con los elementos que esten seleccionados
		//si no se selecciona nada solo muestra la cabecera
		mntmGenerarPdf.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JFileChooser chooser = new JFileChooser();
				//filtro para que solo coja este tipo de ficheros
				FileNameExtensionFilter filter= new FileNameExtensionFilter("pdf", "PDF Files","PDF");
				chooser.setFileFilter(filter);
				int returnVal= chooser.showSaveDialog(menuBar);
				if(returnVal == JFileChooser.APPROVE_OPTION){
					CrearPDF.CrearDocumentoPDF(chooser.getSelectedFile(),lista);
				}	
			}
		});
				
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		//Popup que muestra quien ha realizado la aplicacion 
		JMenuItem mntmAcercaDe = new JMenuItem("Acerca de...");
		mntmAcercaDe.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				/*dialogo = new JDialog(frame);
				dialogo.setModal(true);
				dialogo.setTitle("Acerca de...");
				dialogo.getContentPane().setLayout(new BoxLayout(dialogo.getContentPane(), BoxLayout.Y_AXIS));
				dialogo.getContentPane().add(new JPanel(new GridBagLayout()));
				dialogo.getContentPane().add(new JLabel("<html>MUSEOS DE SEVILLA<br><br>"
						+ "Nombre: M� Jose Rodriguez Martinez<br><br>" + "Fecha:</html>"));
				dialogo.getContentPane().add(new JLabel(LocalDate.now() + ""));
				dialogo.pack();
				dialogo.setVisible(true);
				dialogo.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);*/
				
				JOptionPane.showMessageDialog(null, "Museos de Sevilla y otros centros culturales\n"
						+"Realizada por M� Jose Rodriguez Martinez");
			}
		});	
		mnAyuda.add(mntmAcercaDe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
		);
		
		JPanel panel_1 = new JPanel();
		splitPane.setRightComponent(panel_1);
		
		JLabel lblHorario = new JLabel("Horario:");
		
		JButton button = new JButton("Agregar Museo");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lista.add(new Museos(lista.size()+1,"", "", "", ""));
				tabla.repaint();
			}
		});
		//evento que hace limpiar todos los textfield 
		JButton button_1 = new JButton("Limpiar formularios");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_1.setText("");
				textField.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		//quitamos la seleccion multiple de filas
		tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		JLabel label_1 = new JLabel("Museo:");
		
		JLabel label_2 = new JLabel("Direcci\u00F3n:");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JLabel lblTelefono = new JLabel("Telefono:");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		
		//boton siguiente directamente desde la interfaz sin pasar por el menú
		JButton btnSiguiente = new JButton("Sig.");
		btnSiguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tabla!=null){
					if(tabla.getSelectedRow()>0){
						tabla.setRowSelectionInterval(tabla.getSelectedRow()+1, tabla.getSelectedRow()+1);
					}
				}
			}
		});
		JButton btnAnterior = new JButton("Ant.");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(tabla!=null){
					if(tabla.getSelectedRow()>0){
						tabla.setRowSelectionInterval(tabla.getSelectedRow()-1, tabla.getSelectedRow()-1);
					}
				}
			}
		});
			
					
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.remove(tabla.getSelectedRow());
			}
		});
		
		//boton que agrega al introducir datos en el formulario
		JButton btnAplicar = new JButton("Aplicar");
		btnAplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista.get(tabla.getSelectedRow()).setNombreMuseo(textField_1.getText());
				lista.get(tabla.getSelectedRow()).setDireccion(textField_2.getText());
				lista.get(tabla.getSelectedRow()).setHorario(textField_3.getText());
				lista.get(tabla.getSelectedRow()).setTelefono(textField.getText());
				
			}
		});
		
		JLabel lblBarraEstado = new JLabel("");
		
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(label_2)
										.addComponent(label_1)
										.addComponent(lblHorario)
										.addComponent(lblTelefono))
									.addGap(22)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(textField_3)
											.addComponent(textField_2)
											.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(textField, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
											.addGap(154))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(button)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnAplicar)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnSiguiente)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAnterior)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnEliminar)
									.addGap(12)
									.addComponent(button_1))))
						.addComponent(lblBarraEstado))
					.addContainerGap(72, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(30, Short.MAX_VALUE)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHorario))
					.addGap(19)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTelefono))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(button)
						.addComponent(btnAplicar)
						.addComponent(btnAnterior)
						.addComponent(btnSiguiente)
						.addComponent(btnEliminar)
						.addComponent(button_1))
					.addGap(20)
					.addComponent(lblBarraEstado))
		);
		panel_1.setLayout(gl_panel_1);
		
		//metodo para hacer que el scrollPane se agrande para que se muestren los datos mejor
		//table = new JTable();
		splitPane.setLeftComponent(scrollPane);
		splitPane.setDividerLocation(0.9f);
		splitPane.setResizeWeight(0.9f);
		
		panel.setLayout(gl_panel);
		
		//creamos la etiqueta para la barra de estado y creamos el metodo para que funcione
		tabla.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				lblBarraEstado.setText("Museo: "+(tabla.getSelectedRow()+1)+" de "+lista.size());
				
			}
		});
	}
	
	}

