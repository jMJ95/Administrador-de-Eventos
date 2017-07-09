package ClasesAdministrador;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import GuisAdministrador.Principal;
import GuisPortero.VentanaPrincipal;
import ManejadorDeBD.Conexion;

public class ManejadorDeEntrada {
	Conexion con = new Conexion();
	Principal principal = new Principal();
	VentanaPrincipal portero = new VentanaPrincipal();

	public boolean IniciarSecion(String usuario, String clave)
			throws HeadlessException, SQLException {
		String SQL = ("SELECT Nombre,Apellido,Perfil,id_usuario FROM proyectofinal.usuario WHERE id_usuario='"
				+ usuario + "' and Contrasena ='" + clave + "'");
		ResultSet Resultado = con.Consultar(SQL);
		if (Resultado.next()) {
			if (Resultado.getString(3).equals("Administrador")) {
				JOptionPane.showMessageDialog(null, "Bienvenido"+" "+Resultado.getString(1));
				principal.lblNombre.setText(Resultado.getString(1)+" "+Resultado.getString(2));
				principal.setVisible(true);
				return true;
			} else {
				portero.setVisible(true);
				portero.lblNombre.setText(Resultado.getString(4));
				return true;
			}

		} else{
			JOptionPane.showMessageDialog(null, "Loggeo Incorrecto");
		}

		return false;
	}
}
