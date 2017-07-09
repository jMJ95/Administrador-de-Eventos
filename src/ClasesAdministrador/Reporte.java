package ClasesAdministrador;

public class Reporte {

	String nombre;
	int informacion;

	public Reporte(String nombre, int informacion) {
		super();
		this.nombre = nombre;
		this.informacion = informacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getInformacion() {
		return informacion;
	}

	public void setInformacion(int informacion) {
		this.informacion = informacion;
	}

}
