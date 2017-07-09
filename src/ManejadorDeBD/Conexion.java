package ManejadorDeBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    private static final String usuario  = "root";
	private static final String pass  = "";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/proyectofinal";
	private Connection conexion = null;
	
	
	public Conexion(){
		try {
			Class.forName(driver);
				conexion= DriverManager.getConnection(url, usuario, pass);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
	}
	
	public void Desconectar(){
		this.conexion = null;
	}
    
    public boolean Ejecutar(String SQL)
    {
        try
        {
            PreparedStatement Query = this.conexion.prepareStatement(SQL);
            Query.execute();
            return true;
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "Error "+ex.getMessage());
            return false;
        }
 
    }
    
    public ResultSet Consultar(String SQL)
    {
        try
        {
            PreparedStatement Query = this.conexion.prepareStatement(SQL);
            ResultSet Resultado = Query.executeQuery();
            return Resultado;
        }
        catch(SQLException ex)
        {
            System.out.println("ERROR: "+ex);
            return null;
        }
    }
}
