package Modelo;

//clase que hereda de la clase Museos (clase padre)
public class MuseoArqueologico extends Museos{
	private String AnoConstruccion;
	private String NumObjetos;
	/**
	 * @param id
	 * @param nombreMuseo
	 * @param direccion
	 * @param horario
	 * @param telefono
	 * @param añoConstruccion
	 * @param numObjetos
	 */
	public MuseoArqueologico(int id, String nombreMuseo, String direccion, String horario, String telefono,
			String anoConstruccion, String numObjetos) {
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
