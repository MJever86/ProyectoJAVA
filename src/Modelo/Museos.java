package Modelo;


/**
 * 
 * @author Maria Jose Rodriguez Martinez
 *
 */
public class Museos {
	private int Id;
	private String NombreMuseo;
	private String Direccion;
	private String Horario;
	private String Telefono;
	
	public Museos(int id,String nombreMuseo, String direccion, String horario, String telefono) {
		super();
		this.Id=id;
		this.NombreMuseo = nombreMuseo;
		this.Direccion = direccion;
		this.Horario = horario;
		this.Telefono = telefono;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNombreMuseo() {
		return NombreMuseo;
	}

	public void setNombreMuseo(String nombreMuseo) {
		NombreMuseo = nombreMuseo;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getHorario() {
		return Horario;
	}

	public void setHorario(String horario) {
		Horario = horario;
	}

	public String getTelefono() {
		return Telefono;
	}

	public void setTelefono(String telefono) {
		Telefono = telefono;
	}

	@Override
	public String toString() {
		return "Museos [Id=" + Id + ", NombreMuseo=" + NombreMuseo + ", Direccion=" + Direccion + ", Horario=" + Horario
				+ ", Telefono=" + Telefono + "]";
	}

}
