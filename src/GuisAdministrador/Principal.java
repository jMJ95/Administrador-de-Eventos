package GuisAdministrador;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import org.freixas.jcalendar.DateEvent;
import org.freixas.jcalendar.DateListener;
import org.freixas.jcalendar.JCalendar;

import GuisPortero.VentanaPrincipal;


public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JLabel lblNombre;
	private JCalendar calendar;

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 780, 526);
	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		mnUsuarios.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/1396563113_user_business.png")));
		menuBar.add(mnUsuarios);
		
		JMenuItem mntmAgregarUsuario = new JMenuItem("Mant. Usuarios");
		mntmAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MantenimientoUsuario mantenimiento = new  MantenimientoUsuario();
					mantenimiento.setVisible(true);
					Principal.this.dispose();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		mnUsuarios.add(mntmAgregarUsuario);
		
		JMenu mnEventos = new JMenu("Eventos");
		mnEventos.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/1396585069_x-office-calendar.png")));
		menuBar.add(mnEventos);
		
		JMenuItem mntmAgregarEvento = new JMenuItem("Mant. Eventos");
		mntmAgregarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					VentanaDeEventos eventos = new VentanaDeEventos();
					eventos.setVisible(true);
					Principal.this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnEventos.add(mntmAgregarEvento);
		
		JMenu mnInvitados = new JMenu("Invitados");
		mnInvitados.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/1396585249_system-users.png")));
		menuBar.add(mnInvitados);
		
		JMenuItem mntmAgregarInvitado = new JMenuItem("Mant. Invitados");
		mntmAgregarInvitado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MantenimientoInvitado invitados = new MantenimientoInvitado();
					invitados.setVisible(true);
					Principal.this.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnInvitados.add(mntmAgregarInvitado);
		
		JMenu mnInvitaciones = new JMenu("Invitaciones");
		mnInvitaciones.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/1396585323_note-edit.png")));
		menuBar.add(mnInvitaciones);
		
		JMenuItem mntmVerInvitaciones = new JMenuItem("Ver Invitaciones");
		mntmVerInvitaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try {
					InvitacionesGrafico invitaciones = new  InvitacionesGrafico();
					invitaciones.setVisible(true);
					Principal.this.dispose();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnInvitaciones.add(mntmVerInvitaciones);
		
		JMenu mnReportes = new JMenu("Reportes");
		mnReportes.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/VENTAS.JPG")));
		menuBar.add(mnReportes);
		
		JMenuItem mntmVerReportes = new JMenuItem("Ver Reportes");
		mntmVerReportes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ReportePrueba reportes = new ReportePrueba();
					reportes.setVisible(true);
					Principal.this.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		mnReportes.add(mntmVerReportes);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnOpciones.setIcon(new ImageIcon(Principal.class.getResource("/Iconos/1397243662_gear_wheel.png")));
		menuBar.add(mnOpciones);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Loggeo login = new Loggeo();
				int seleccion = JOptionPane.showConfirmDialog(null, "Desea Cerrar Secion", "Cerrar", JOptionPane.YES_NO_OPTION);
				if(seleccion == 0){
					login.setVisible(true);
					Principal.this.dispose();
				}
			}
		});
		mnOpciones.add(mntmSalir);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(5, 0, 769, 65);
		contentPane.add(panel);
		
		JLabel lblAdministradorDeInvitados = new JLabel("Administrador De Eventos");
		lblAdministradorDeInvitados.setFont(new Font("Tw Cen MT", Font.PLAIN, 50));
		panel.add(lblAdministradorDeInvitados);
		
		lblNombre = new JLabel("");
		lblNombre.setFont(new Font("Tw Cen MT", Font.ITALIC, 25));
		lblNombre.setBounds(10, 93, 263, 45);
		contentPane.add(lblNombre);
		
		calendar = new JCalendar();
		calendar.addDateListener(new DateListener() {
			public void dateChanged(DateEvent arg0) {
				JOptionPane.showMessageDialog(null, calendar.getDate());
			}
		});
		calendar.setTodayFont(new Font("Dialog", Font.BOLD, 14));
		calendar.setOpaque(false);
		calendar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		calendar.setBounds(193, 251, 388, 195);
		contentPane.add(calendar);
		
		JButton btnNewButton = new JButton("Ver Portero");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaPrincipal portero = new VentanaPrincipal();
				portero.setVisible(true);
				portero.btnAdministrador.setVisible(true);
				Principal.this.dispose();
			}
		});
		btnNewButton.setBounds(570, 103, 194, 38);
		contentPane.add(btnNewButton);
		
		setLocationRelativeTo(null);
	}
}
