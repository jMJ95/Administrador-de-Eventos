package ClasesPortero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ManejadorDeBD.Conexion;

public class ManejadorDeInvitaciones {

	public ArrayList<Invitaciones> Arrayinvitados = new ArrayList<Invitaciones>();
	public ArrayList<Invitaciones> ArrayBuscar = new ArrayList<Invitaciones>();
	public ArrayList<String> ArrayRestantes = new ArrayList<String>();
	Conexion con = new Conexion();
	
	public void Mostrar(int id) throws SQLException{
		String SQL =(" SELECT i.Id_invitado,i.Nombre, i.Apellido, i.Telefono,e.Nombre,it.Asistencia FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Id_evento = '"+id+"'");
		ResultSet resultado = con.Consultar(SQL);
		while(resultado.next()){
			Arrayinvitados.add(new Invitaciones(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4),resultado.getString(5),resultado.getString(6)));
		}
	}
	
	public void BuscarInvitado(int id,String NombreOApellido){
		
		String SQL = ("SELECT i.Id_invitado,i.Nombre, i.Apellido, i.Telefono,e.Nombre,it.Asistencia,it.Id_evento FROM proyectofinal.invitado "
				+ "i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento "
				+ "where it.Id_evento = '"+id+"' and (i.Nombre = '"+NombreOApellido+"'  or i.Apellido = '"+NombreOApellido+"')");
		
		ResultSet resultado = con.Consultar(SQL);
		try {
			while(resultado.next()){
				ArrayBuscar.add(new Invitaciones(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4),resultado.getString(5),resultado.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void InvitadosRestantes(int id) throws SQLException{
		String SQL ="SELECT count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where e.Id_evento = '"+id+"'  and it.Asistencia = 'No'";
		ResultSet resultado = con.Consultar(SQL);
		while (resultado.next()){
			ArrayRestantes.add(resultado.getString(1));
		}
	}
}
