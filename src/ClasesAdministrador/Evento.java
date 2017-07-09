package ClasesAdministrador;

public class Evento {
	int id;
	String nombre;
	String ubicacion;
	String fecha;
	
	public Evento(int id, String nombre, String ubicacion, String fecha) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.fecha = fecha;
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
