package ClasesPortero;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import ManejadorDeBD.Conexion;

public class ManejadorDeAsistencia {

	Conexion conexion = new Conexion();
	
	public void Asistencia(String asistencia,String id ) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE proyectofinal.invitacion SET Asistencia='"+asistencia+"' Where Id_invitado ='"+id+"'"; 
        if(conexion.Ejecutar(SQL)){
        JOptionPane.showMessageDialog(null,"Datos Actulizados Correctamente");
        }else{
        	JOptionPane.showMessageDialog(null,"Error");
        }
    }
}
