package ClasesAdministrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ManejadorDeBD.Conexion;

public class ManejadorDeReportes {
	ArrayList<Integer> total = new ArrayList<Integer>();
	ArrayList<Integer> presentes = new ArrayList<Integer>();
	ArrayList<Integer> resultante = new ArrayList<Integer>();
	Conexion conexion = new Conexion();
	public ArrayList <Reporte> reportes = new ArrayList <Reporte>();
	
	
	public void reporteAsistencia () throws SQLException{	
		reportes.clear();
		String SQL ="SELECT e.Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' Group by e.Nombre";
		ResultSet resultado = conexion.Consultar(SQL);
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString("e.Nombre"),resultado.getInt("count(it.Asistencia)")));
		}
	}
	
	public void MasVisitados() throws SQLException{	
		reportes.clear();
		String SQL ="SELECT e.Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' Group by e.Nombre Order by count(it.Asistencia) desc";
		ResultSet resultado = conexion.Consultar(SQL);
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString("e.Nombre"),resultado.getInt("count(it.Asistencia)")));
		}
	}
	
	public void eventosMujeres() throws SQLException{
		reportes.clear();
		String SQL = "SELECT e.Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' and i.Sexo = 'Femenino' Group by e.Nombre Order by count(it.Asistencia) desc";
		ResultSet resultado = conexion.Consultar(SQL);
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString("e.Nombre"),resultado.getInt("count(it.Asistencia)")));
		}
	}
	
	public void eventosHombres() throws SQLException{
		reportes.clear();
		String SQL = "SELECT e.Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' and i.Sexo = 'Masculino' Group by e.Nombre Order by count(it.Asistencia) desc";
		ResultSet resultado = conexion.Consultar(SQL);
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString("e.Nombre"),resultado.getInt("count(it.Asistencia)")));
		}
	}
	
	public void eventosPorDias() throws SQLException{
		reportes.clear();
		String SQL = "SELECT DAYNAME(e.Fecha), count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' Group by DAYNAME(e.Fecha) Order by count(it.Asistencia) desc";
		ResultSet resultado = conexion.Consultar(SQL);
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString(1),resultado.getInt("count(it.Asistencia)")));
		}
	}
	
	public void porsentaje() throws SQLException{
		reportes.clear();
		String SQL1 = "SELECT e.Id_evento, e.Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento Group by e.Nombre";
		String SQL2 = "SELECT e. Nombre, count(it.Asistencia) FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' Group by e.Nombre";
		String SQL3 = "SELECT e.Nombre FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento Group by e.Nombre";
		ResultSet resultado = conexion.Consultar(SQL1);
		ResultSet comprobar;
		int cont = 0;
		int cont2 = 0;
		int id = 0;
		int registro = 0;
		while (resultado.next()){
			cont++;
			id = resultado.getInt("e.Id_evento");
			String SQL4 = "SELECT e. Nombre FROM proyectofinal.invitado i join proyectofinal.invitacion it on i.Id_invitado = it.Id_invitado join proyectofinal.evento e on it.Id_evento = e.Id_evento where it.Asistencia = 'si' and e.Id_evento = "+id+" Group by e.Nombre";
			comprobar = conexion.Consultar(SQL4);
			if(comprobar.next()==false){
				registro = cont;
				
			}
			total.add(resultado.getInt("count(it.Asistencia)"));
			
		}
		resultado = conexion.Consultar(SQL2);
		while (resultado.next()){
			cont2++;
			if(cont2 == registro){
				presentes.add(0);
			}
			presentes.add(resultado.getInt("count(it.Asistencia)"));
			
			
		}
		if(registro > cont2){
			presentes.add(0);
			cont2++;
			}
		int a =0;
		while(a < total.size()){
			
		resultante.add((presentes.get(a)*100)/total.get(a));
			
		a++;
		}
		resultado = conexion.Consultar(SQL3);
		a = 0;
		while (resultado.next()){
			reportes.add(new Reporte(resultado.getString(1), resultante.get(a) ));
			a++;
		}
	}
}