package GuisAdministrador;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesAdministrador.Invitaciones;
import ClasesAdministrador.ManejadorDeInvitaciones;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InvitacionesGrafico extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JButton btnModificarInvitacion;
	private JButton btnEliminar;
	private DefaultTableModel modelo = new DefaultTableModel();
	private ManejadorDeInvitaciones mdi = new ManejadorDeInvitaciones();
	private JTextField txtIdEvento;
	private JTextField txtIdInvitado;
	private Object[] invitacionmodificada;
	private JButton button;

	
	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public InvitacionesGrafico() throws ClassNotFoundException, SQLException {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 729, 536);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 257, 660, 208);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					modificar();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		String cabecera[] = { "ID evento", "Nombre del evento", "Id invitado",
		"Nombre de usuario" };
		modelo.setColumnIdentifiers(cabecera);
		table.setModel(modelo);
		cargarTabla();
		scrollPane.setViewportView(table);

		btnModificarInvitacion = new JButton("Modificar Invitacion");
		btnModificarInvitacion.setEnabled(false);
		btnModificarInvitacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					actualizar();
					cargarTabla();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnModificarInvitacion.setIcon(new ImageIcon(InvitacionesGrafico.class
				.getResource("/Iconos/1396563081_Notes.png")));
		btnModificarInvitacion.setBounds(279, 81, 153, 46);
		contentPane.add(btnModificarInvitacion);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					eliminar();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEliminar.setEnabled(false);
		btnEliminar.setIcon(new ImageIcon(InvitacionesGrafico.class
				.getResource("/Iconos/1396563109_Delete.png")));
		btnEliminar.setBounds(495, 81, 153, 46);
		contentPane.add(btnEliminar);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(10, 0, 693, 46);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblInvitaciones = new JLabel("Invitaciones");
		lblInvitaciones.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblInvitaciones.setHorizontalAlignment(SwingConstants.CENTER);
		lblInvitaciones.setBounds(127, 0, 443, 46);
		panel.add(lblInvitaciones);

		JLabel lblIdEvento = new JLabel("Id Evento");
		lblIdEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdEvento.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblIdEvento.setBounds(59, 191, 104, 14);
		contentPane.add(lblIdEvento);

		JLabel lblIdUsuario = new JLabel("Id Invitado");
		lblIdUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdUsuario.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		lblIdUsuario.setBounds(367, 190, 124, 14);
		contentPane.add(lblIdUsuario);

		txtIdEvento = new JTextField();
		txtIdEvento.setBounds(204, 190, 104, 20);
		contentPane.add(txtIdEvento);
		txtIdEvento.setColumns(10);

		txtIdInvitado = new JTextField();
		txtIdInvitado.setColumns(10);
		txtIdInvitado.setBounds(550, 184, 104, 20);
		contentPane.add(txtIdInvitado);
		
		button = new JButton("Atras");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal principal = new Principal();
				principal.setVisible(true);
				InvitacionesGrafico.this.dispose();
			}
		});
		button.setIcon(new ImageIcon(InvitacionesGrafico.class.getResource("/Iconos/ANTERIOR.JPG")));
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		button.setBounds(63, 80, 153, 46);
		contentPane.add(button);
		
		setLocationRelativeTo(null);
	}

	public void cargarTabla() throws ClassNotFoundException, SQLException {
		modelo.setRowCount(0);
		mdi.mostrarInvitacion();
		for (Invitaciones i : mdi.ali) {
			modelo.addRow(new Object[] { i.getIdEvento(), i.getNombreEvento(),
					i.getIdInvitado(), i.getNombreInvitado() });
		}
	}

	public void modificar() throws SQLException {
		int row = table.getSelectedRow();
		if (row >= 0) {
			btnModificarInvitacion.setEnabled(true);
			btnEliminar.setEnabled(true);
			int evento = (int) table.getValueAt(row, 0);
			int invitado = (int) table.getValueAt(row, 2);
			mdi.Modificar(evento, invitado);
			for (Invitaciones i : mdi.ali) {
				invitacionmodificada = new Object[] {i.getIdEvento(), i.getIdInvitado(),i.getIdInvitacion()};
				txtIdInvitado.setText(Integer.toString((int)invitacionmodificada[1]));
				txtIdEvento.setText(Integer.toString((int)invitacionmodificada[0]));
			}
		}
	}

	public void actualizar() throws ClassNotFoundException, SQLException{
		mdi.actualizar(Integer.parseInt(txtIdInvitado.getText()),Integer.parseInt(txtIdEvento.getText()),(int)invitacionmodificada[2]);
		cargarTabla();
		btnModificarInvitacion.setEnabled(false);
		btnEliminar.setEnabled(false);
		txtIdEvento.setText("");
		txtIdInvitado.setText("");
	}

	public void eliminar() throws ClassNotFoundException, SQLException {
		int filaselect = table.getSelectedRow();
		if (filaselect >= 0) {
			modelo.removeRow(filaselect);
			mdi.Eliminar(mdi.ali.get(filaselect));
			cargarTabla();
			btnModificarInvitacion.setEnabled(false);
			btnEliminar.setEnabled(false);
			txtIdEvento.setText("");
			txtIdInvitado.setText("");
		}
	}
}
