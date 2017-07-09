package ClasesAdministrador;

public class Invitaciones {
	int idEvento;
	String nombreEvento;
	int idInvitado;
	String nombreInvitado;
	int idInvitacion;
	
	public Invitaciones( int idEvento, String nombreEvento, int idInvitado,
			String nombreInvitado, int idInvitacion) {
		super();
		this.idEvento = idEvento;
		this.nombreEvento = nombreEvento;
		this.idInvitado = idInvitado;
		this.nombreInvitado = nombreInvitado;
		this.idInvitacion = idInvitacion;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public int getIdInvitado() {
		return idInvitado;
	}
	public void setIdInvitado(int idInvitado) {
		this.idInvitado = idInvitado;
	}
	public String getNombreInvitado() {
		return nombreInvitado;
	}
	public void setNombreInvitado(String nombreInvitado) {
		this.nombreInvitado = nombreInvitado;
	}
	public int getIdInvitacion() {
		return idInvitacion;
	}
	public void setIdInvitacion(int idInvitacion) {
		this.idInvitacion = idInvitacion;
	}
}