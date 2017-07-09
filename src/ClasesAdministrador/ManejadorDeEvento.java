package ClasesAdministrador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import ManejadorDeBD.Conexion;

public class ManejadorDeEvento {
    public  ArrayList<Evento> arrayEvento = new ArrayList<Evento>();
    
    Conexion con = new Conexion();
    
    public void Ingresar(String Nombre,String Ubicacion,String Fecha){
        
        String SQL = ("insert into  proyectofinal.evento(Nombre,Ubicacion,Fecha) Values ('"+Nombre+"','"+Ubicacion+"','"+Fecha+"')");
        con.Ejecutar(SQL);
        JOptionPane.showMessageDialog(null, "Datos Ingresados Correctamente");
     }
     
     public void Mostrar() throws SQLException{
    	 arrayEvento.clear();
         String SQL= ("Select * from proyectofinal.evento");
         ResultSet resultado = con.Consultar(SQL);
         while(resultado.next()){
         arrayEvento.add(new Evento(resultado.getInt(1),resultado.getString(2),resultado.getString(3),resultado.getString(4)));
         }
     }
     
     public void Eliminar(int id){
         String SQL= ("Delete from proyectofinal.evento where id_evento='"+id+"'");
         con.Ejecutar(SQL);
     }
     
     public void Modificar(int id, String Nombre, String Ubicacion, String Fecha){
         String SQL= ("Update proyectofinal.evento set Nombre = '"+Nombre+"',Ubicacion = '"+Ubicacion+"',Fecha = '"+Fecha+"' where id_evento = '"+id+"'");
         con.Ejecutar(SQL);
     }
}
