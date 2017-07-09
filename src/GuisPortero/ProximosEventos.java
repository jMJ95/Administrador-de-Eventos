package GuisPortero;

import java.awt.Font;
import java.awt.SystemColor;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesAdministrador.Evento;
import ClasesPortero.ManejadorDeEventos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class ProximosEventos extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JPanel panel;
	private JLabel lblProximosEventos;
	DefaultTableModel modelo = new DefaultTableModel();
	public JButton btnInicio,btnAtras;
	public int idEvento;

	/**
	 * Create the frame.
	 */
	public ProximosEventos() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 410);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 128, 595, 232);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnAtras = new JButton("Atras");
		btnAtras.setIcon(new ImageIcon(ProximosEventos.class.getResource("/Iconos/ANTERIOR.JPG")));
		btnAtras.setVisible(false);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSecundaria secundaria = new VentanaSecundaria();
				secundaria.setVisible(true);
				try {
					secundaria.conseguirId(idEvento);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				ProximosEventos.this.dispose();
			}
		});
		btnAtras.setBounds(10, 78, 125, 39);
		contentPane.add(btnAtras);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0, 0, 615, 60);
		contentPane.add(panel);
		panel.setLayout(null);
		
		lblProximosEventos = new JLabel("Proximos Eventos");
		lblProximosEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblProximosEventos.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		lblProximosEventos.setBounds(70, 0, 479, 60);
		panel.add(lblProximosEventos);
		
		btnInicio = new JButton("Atras");
		btnInicio.setIcon(new ImageIcon(ProximosEventos.class.getResource("/Iconos/ANTERIOR.JPG")));
		btnInicio.setVisible(false);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.setVisible(true);
				ProximosEventos.this.dispose();
			}
		});
		btnInicio.setBounds(10, 78, 125, 39);
		contentPane.add(btnInicio);
		
		try {
			MostrarProximosEventos();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		setLocationRelativeTo(null);
		
	}
	
	public void MostrarProximosEventos() throws SQLException{
		ManejadorDeEventos eventos = new ManejadorDeEventos();
		String [] cabecera = {"Id ","Nombre del Evento ","Ubicacion ","Fecha "};
		modelo.setColumnIdentifiers(cabecera);
		modelo.setNumRows(0);
		eventos.ProximosEventos();
		for(Evento e :eventos.arrayEvento){
			modelo.addRow(new Object[] {e.getId(),e.getNombre(),e.getUbicacion(),e.getFecha()});

		}
		table.setModel(modelo);	
	}

}
