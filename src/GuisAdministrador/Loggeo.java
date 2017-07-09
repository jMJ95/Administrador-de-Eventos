package GuisAdministrador;

import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ClasesAdministrador.ManejadorDeEntrada;

@SuppressWarnings("serial")
public class Loggeo extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;
	ManejadorDeEntrada mdn = new ManejadorDeEntrada();

	/**
	 * Create the frame.
	 */
	public Loggeo() {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 286);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(7, 0, 503, 56);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAdministradorDeEventos = new JLabel("Administrador De Eventos");
		lblAdministradorDeEventos.setFont(new Font("Times New Roman", Font.PLAIN, 28));
		lblAdministradorDeEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdministradorDeEventos.setBounds(39, 0, 417, 56);
		panel.add(lblAdministradorDeEventos);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(219, 102, 225, 27);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(219, 155, 225, 27);
		contentPane.add(txtClave);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setIcon(new ImageIcon(Loggeo.class.getResource("/Iconos/1396563113_user_business.png")));
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblUsuario.setBounds(48, 102, 141, 27);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasena = new JLabel("Contrasena");
		lblContrasena.setIcon(new ImageIcon(Loggeo.class.getResource("/Iconos/1396563117_change_password.png")));
		lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblContrasena.setBounds(48, 140, 141, 42);
		contentPane.add(lblContrasena);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnIngresar.setIcon(new ImageIcon(Loggeo.class.getResource("/Iconos/1396563557_door_in.png")));
		btnIngresar.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(mdn.IniciarSecion(txtUsuario.getText(), txtClave.getText())){
						Loggeo.this.dispose();
					}
					txtUsuario.setText("");
					txtClave.setText("");
				} catch (HeadlessException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnIngresar.setBounds(303, 197, 141, 35);
		contentPane.add(btnIngresar);
		
		setLocationRelativeTo(null);
	}
}
