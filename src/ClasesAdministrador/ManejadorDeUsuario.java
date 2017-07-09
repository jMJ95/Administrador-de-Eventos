package ClasesAdministrador;

import ManejadorDeBD.Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class ManejadorDeUsuario {
       public  ArrayList<Usuario> arrayUsuario = new ArrayList<Usuario>();
       Conexion con = new Conexion();
       String SQL;
         public void Ingresar(String usuario,String Nombre,String Apellido,String Clave,String Perfil){
            SQL = ("insert into proyectofinal.usuario(id_usuario,Nombre,Apellido,Contrasena,Perfil) Values ('"+usuario+"','"+Nombre+"','"+Apellido+"','"+Clave+"','"+Perfil+"')");
            con.Ejecutar(SQL);
            JOptionPane.showMessageDialog(null, "Datos Ingresados Correctamente");
         }
                  
         public void Mostrar() throws SQLException{
        	 this.arrayUsuario.clear();
             SQL= ("Select * from proyectofinal.usuario");
             ResultSet resultado = con.Consultar(SQL);
             while(resultado.next()){
             arrayUsuario.add(new Usuario(resultado.getString(2),resultado.getString(3),resultado.getString(1),resultado.getString(4),resultado.getString(5)));
             }
         }
         
         public void Eliminar(String usuario){
             SQL= ("Delete from proyectofinal.usuario where id_usuario='"+usuario+"'");
             con.Ejecutar(SQL);
             JOptionPane.showMessageDialog(null, "Datos Eliminados Correctamente");
         }
         public void Modificar(String usuario) throws SQLException{
        	SQL=("Select * from proyectofinal.usuario where id_usuario='"+usuario+"'");
        	con.Ejecutar(SQL);
        	ResultSet resultado = con.Consultar(SQL);
            while(resultado.next()){
            arrayUsuario.add(new Usuario(resultado.getString(2),resultado.getString(3),resultado.getString(1),resultado.getString(4),resultado.getString(5)));
            }
         }
         public void actualizar(String nombre,String apellido,String clave,String perfil,String usuario ) throws ClassNotFoundException, SQLException{
             String SQL = "Update proyectofinal.usuario Set Nombre='"+nombre+"', Apellido='"+apellido+"', Contrasena = '"+clave+"',Perfil = '"+perfil+"' Where id_usuario= '"+usuario+"' ";  
             con.Ejecutar(SQL);
             JOptionPane.showMessageDialog(null,"Datos Actulizados Correctamente");
         }
}
