package ClasesAdministrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ManejadorDeBD.Conexion;

public class ManejadorDeInvitaciones {
	private Conexion conexion = new Conexion();
	public ArrayList<Invitaciones> ali = new ArrayList<Invitaciones>();

    public void Invitar(int invitado,int evento){
    String SQL =("Insert into proyectofinal.invitacion (Id_invitado,Id_evento) Values ('"+invitado+"','"+evento+"')");
    if(conexion.Ejecutar(SQL)){
        JOptionPane.showMessageDialog(null, "Invitacion Enviada");
    }else{
        JOptionPane.showMessageDialog(null, "Error");
    }
}
	
	public void mostrarInvitacion () throws SQLException {
		this.ali.clear();
		ResultSet resultados = conexion.Consultar("select it.Id_invitacion, e.Id_evento,e.Nombre,i.Id_invitado,i.Nombre from invitado i join invitacion it on i.Id_invitado = it.Id_invitado join evento e on it.Id_evento = e.Id_evento");
		while (resultados.next()) {
			ali.add(new Invitaciones(resultados.getInt(2), resultados.getString(3), resultados.getInt(4), resultados.getString(5), resultados.getInt(1)));
		}
	}
	
	public void Modificar(int idEvento,int idInvitado) throws SQLException{
    	String SQL=("select it.Id_invitacion, e.Id_evento,e.Nombre,i.Id_invitado,i.Nombre from invitado i join invitacion it on i.Id_invitado = it.Id_invitado join evento e on it.Id_evento = e.Id_evento where e.Id_evento="+idEvento+" and i.Id_invitado="+idInvitado);
    	conexion.Ejecutar(SQL);
    	ResultSet resultados = conexion.Consultar(SQL);
        while(resultados.next()){
    		ali.add(new Invitaciones(resultados.getInt(2), resultados.getString(3), resultados.getInt(4), resultados.getString(5), resultados.getInt(1)));
        }
        
     }
	public void actualizar(int invitado, int evento, int invitacion) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE `invitacion` SET `Id_invitado`="+invitado+",`Id_evento`="+evento+" WHERE `Id_invitacion`="+invitacion;  
        conexion.Ejecutar(SQL);
        JOptionPane.showMessageDialog(null,"Datos Actulizados Correctamente");
    }

	public void Eliminar(Invitaciones id){
        String SQL= ("DELETE FROM `invitacion` WHERE `Id_invitacion`="+id.getIdInvitacion());
        conexion.Ejecutar(SQL);
        JOptionPane.showMessageDialog(null,"Datos Eliminados Correctamente");
    }
}
