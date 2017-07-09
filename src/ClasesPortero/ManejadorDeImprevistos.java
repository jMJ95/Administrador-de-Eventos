package ClasesPortero;

import javax.swing.JOptionPane;

import ManejadorDeBD.Conexion;

public class ManejadorDeImprevistos {

	Conexion con = new Conexion();
	
	public void Ingresar(String Nombre,String Apellido,String Usuario,String Razon){
		String SQL =("Insert into proyectofinal.imprevisto (Nombre,Apellido,id_usuario,Razon)Values('"+Nombre+"','"+Apellido+"','"+Usuario+"','"+Razon+"')");
		if(con.Ejecutar(SQL)){
			JOptionPane.showMessageDialog(null, "Invitado Agregado");
		}else{
			JOptionPane.showMessageDialog(null, "Error al Reistrar Visitante", "Error", JOptionPane.CANCEL_OPTION);
		}
	}
}
