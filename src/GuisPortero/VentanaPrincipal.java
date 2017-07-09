package GuisPortero;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesAdministrador.Evento;
import ClasesPortero.ManejadorDeEventos;
import ClasesPortero.ManejadorDeInvitaciones;
import GuisAdministrador.Loggeo;
import GuisAdministrador.Principal;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnVerEvento;
	public JLabel lblNombre;
	ManejadorDeInvitaciones invitaciones = new ManejadorDeInvitaciones();
	public String ID;
	int id;
	public JButton btnAdministrador;
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 834, 484);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnVerT = new JMenu("Eventos");
		mnVerT.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/1396585331_Note.png")));
		menuBar.add(mnVerT);
		
		JMenuItem mntmVerTodosLos = new JMenuItem("Proximos Eventos");
		mntmVerTodosLos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProximosEventos eventos = new ProximosEventos();
				eventos.btnInicio.setVisible(true);
				eventos.setVisible(true);
				VentanaPrincipal.this.dispose();
			}
		});
		mnVerT.add(mntmVerTodosLos);
		
		JMenu mnNewMenu = new JMenu("Opciones");
		mnNewMenu.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/1397243662_gear_wheel.png")));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCerrarSesion = new JMenuItem("Cerrar Sesion");
		mntmCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Loggeo login = new Loggeo();
				int seleccion = JOptionPane.showConfirmDialog(null, "Desea Cerrar Secion", "Cerrar", JOptionPane.YES_NO_OPTION);
				if(seleccion == 0){
					login.setVisible(true);
					VentanaPrincipal.this.dispose();
				}
			}
		});
		
		JSeparator separator = new JSeparator();
		mnNewMenu.add(separator);
		mnNewMenu.add(mntmCerrarSesion);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 205, 692, 177);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnVerEvento.setEnabled(true);
			}
		});
		scrollPane.setViewportView(table);
		
		btnVerEvento = new JButton("Mostrar Evento");
		btnVerEvento.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/Iconos/1396563776_magnifier_zoom_in.png")));
		btnVerEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaSecundaria secundaria = new VentanaSecundaria();
				secundaria.lblUsuario.setText(lblNombre.getText());
				SeleccionarFila();
				secundaria.setVisible(true);
				VentanaPrincipal.this.dispose();
				try {
					secundaria.conseguirId(id);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnVerEvento.setEnabled(false);
		btnVerEvento.setBounds(567, 153, 181, 41);
		contentPane.add(btnVerEvento);
		
		JLabel lblNewLabel = new JLabel("Eventos De Hoy");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(257, 162, 279, 32);
		contentPane.add(lblNewLabel);
		
		lblNombre = new JLabel("");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNombre.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblNombre.setBounds(332, 49, 164, 41);
		contentPane.add(lblNombre);
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal administrador = new Principal();
				administrador.setVisible(true);
				VentanaPrincipal.this.dispose();
			}
		});
		btnAdministrador.setVisible(false);
		btnAdministrador.setBounds(329, 113, 169, 32);
		contentPane.add(btnAdministrador);
		try {
			MostrarEventosDeHoy();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setLocationRelativeTo(null);
		
	}
	
	public void MostrarEventosDeHoy() throws SQLException{
		ManejadorDeEventos eventos = new ManejadorDeEventos();
		String [] cabecera = {"Id ","Nombre del Evento ","Ubicacion ","Fecha "};
		modelo.setColumnIdentifiers(cabecera);
		modelo.setNumRows(0);
		eventos.Mostrar();
		for(Evento e :eventos.arrayEvento){
			modelo.addRow(new Object[] {e.getId(),e.getNombre(),e.getUbicacion(),e.getFecha()});

		}
		table.setModel(modelo);	
	}
	
	public void SeleccionarFila(){
		if(table.getSelectedRow() != -1){
			int row = table.getSelectedRow();
			int datos = (int) table.getValueAt(row, 0);
			id = datos;
			}else
				JOptionPane.showMessageDialog(null, "Debe Seleccionar el Usuario que desee Eliminar");
		}
	}
