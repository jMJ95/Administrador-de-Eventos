package ClasesPortero;

public class Invitaciones {
	private int ID;
	private String nombre;
	private String apellido;
	private String telefono;
	private String evento;
	private String asistencia;
	
	public String getEvento() {
		return evento;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public Invitaciones(int ID,String nombre, String apellido, String telefono,String evento,
			String asistencia) {
		super();
		this.ID = ID;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.evento = evento;
		this.asistencia = asistencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(String asistencia) {
		this.asistencia = asistencia;
	}
	
	
	
}
