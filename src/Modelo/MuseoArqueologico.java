package Modelo;

/**
 * 
 * @author Maria Jose Rodriguez Martinez
 *
 */
//clase que hereda de la clase Museos (clase padre)
public class MuseoArqueologico extends Museos{
	private String AnoConstruccion;
	private String NumObjetos;
	/**
	 * @param id del museo
	 * @param nombreMuseo nombre de nuestro museo
	 * @param direccion direccion del museo
	 * @param horario apertura y cierre de los museos
	 * @param telefono telefono de contacto e informacion
	 * @param anoConstruccion año en el que se construyo el museo 
	 * @param numObjetos numero de objetos que contiene el museo
	 * @throws IlegalMuseoException excepcion si te equivocas en el telefono
	 */
	public MuseoArqueologico(int id, String nombreMuseo, String direccion, String horario, String telefono,
			String anoConstruccion, String numObjetos) throws IlegalMuseoException {
		super(id, nombreMuseo, direccion, horario, telefono);
		AnoConstruccion = anoConstruccion;
		NumObjetos = numObjetos;
	}
	/**
	 * @return the anoConstruccion
	 */
	public String getAnoConstruccion() {
		return AnoConstruccion;
	}
	/**
	 * @param anoConstruccion the anoConstruccion to set
	 */
	public void setAnoConstruccion(String anoConstruccion) {
		AnoConstruccion = anoConstruccion;
	}
	/**
	 * @return the numObjetos
	 */
	public String getNumObjetos() {
		return NumObjetos;
	}
	/**
	 * @param numObjetos the numObjetos to set
	 */
	public void setNumObjetos(String numObjetos) {
		NumObjetos = numObjetos;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MuseoArqueologico [AnoConstruccion=" + AnoConstruccion + ", NumObjetos=" + NumObjetos + "]";
	}
	

}
