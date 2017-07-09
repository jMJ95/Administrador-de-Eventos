package ClasesPortero;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ClasesAdministrador.Evento;
import ManejadorDeBD.Conexion;

public class ManejadorDeEventos {
Conexion con = new Conexion();
public ArrayList<Evento> arrayEvento = new ArrayList<Evento>();


public void Mostrar() throws SQLException{
	arrayEvento.clear();
	String SQL = ("Select * From proyectofinal.evento where Fecha = (select CURDATE())");
    ResultSet resultado = con.Consultar(SQL);
    while(resultado.next()){
    arrayEvento.add(new Evento(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4)));
    }
}

public void ProximosEventos() throws SQLException{
	arrayEvento.clear();
	String SQL = ("Select * From proyectofinal.evento where Fecha >= (select CURDATE())");
    ResultSet resultado = con.Consultar(SQL);
    while(resultado.next()){
    arrayEvento.add(new Evento(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4)));
    }
}
public void mostrarTodos() throws SQLException{
	arrayEvento.clear();
	String SQL = ("Select * From proyectofinal.evento");
    ResultSet resultado = con.Consultar(SQL);
    while(resultado.next()){
    arrayEvento.add(new Evento(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4)));
    }
}
}
