package ClasesAdministrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ManejadorDeBD.Conexion;

public class ManejadorDeInvitado {
	public Conexion conexion = new Conexion();
    public ArrayList<Invitado> invitados = new ArrayList<>();
    
	public void manejarInvitado(String nombre,String apellido,String direccion ,String sexo, String telefono) throws ClassNotFoundException, SQLException{
	        String SQL = "insert into invitado(Nombre,Apellido,Direccion,Sexo,Telefono) Values ('"+nombre+"','"+apellido+"','"+direccion+"','"+sexo+"','"+telefono+"')";
	        conexion.Ejecutar(SQL);
	   }
	
	 public void mostrar() throws SQLException, ClassNotFoundException {
		 	this.invitados.clear();
	        ResultSet resultados = conexion.Consultar("SELECT * FROM invitado");
	        while (resultados.next()) {
	            invitados.add(new Invitado(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4), resultados.getString(5), resultados.getString(6)));
	        }
	    }
	 
	public void Eliminar(int id){
        String SQL= ("Delete from invitado where Id_invitado="+id);
        conexion.Ejecutar(SQL);
        JOptionPane.showMessageDialog(null,"Datos Eliminados Correctamente");
    }
	
	public void Modificar(int invitado) throws SQLException{
    	String SQL=("Select * from proyectofinal.invitado where Id_invitado="+invitado);
    	conexion.Ejecutar(SQL);
    	ResultSet resultados = conexion.Consultar(SQL);
        while(resultados.next()){
        	invitados.add(new Invitado(resultados.getInt(1), resultados.getString(2), resultados.getString(3), resultados.getString(4), resultados.getString(5), resultados.getString(6)));
        }
        
     }
	public void actualizar(String nombre,String apellido,String direccion ,String sexo, String telefono,int id ) throws ClassNotFoundException, SQLException{
        String SQL = "UPDATE proyectofinal.invitado SET Nombre='"+nombre+"',Apellido='"+apellido+"',Direccion='"+direccion+"',Sexo='"+sexo+"',Telefono='"+telefono+"' WHERE Id_invitado="+id;  
        conexion.Ejecutar(SQL);
        JOptionPane.showMessageDialog(null,"Datos Actulizados Correctamente");
    }
}
