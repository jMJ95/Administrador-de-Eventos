package GuisAdministrador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesAdministrador.ManejadorDeUsuario;
import ClasesAdministrador.Usuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class MantenimientoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtUsuario;
	private JPasswordField tpfClave;
	private JTable table;
	ManejadorDeUsuario mdu = new ManejadorDeUsuario();
	@SuppressWarnings("rawtypes")
	private JComboBox jcbPerfil;
	private DefaultTableModel modelo = new DefaultTableModel();
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JButton btnActualizar;
	@SuppressWarnings("unused")
	private Object[] usuarioModificado;
	private JButton btnAtras,btnCancelar;
	private JPanel panel;
	
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MantenimientoUsuario() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 818, 703);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
		btnEliminar = new JButton("Eliminar Usuario");
		btnEliminar.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/1396563115_user_business_close.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar();
				try {
					MostrarDatos();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(510, 67, 145, 43);
		contentPane.add(btnEliminar);
		
		btnModificar = new JButton("Modificar Usuario");
		btnModificar.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/1396563081_Notes.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					modificar();
					MostrarDatos();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnModificar.setBounds(178, 67, 145, 43);
		contentPane.add(btnModificar);
		
		btnAgregar = new JButton("Agregar Usuario");
		btnAgregar.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/1396563123_Add.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregar();
				try {
					MostrarDatos();
					Limpiar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			
			}
		});
		btnAgregar.setBounds(10, 67, 145, 43);
		contentPane.add(btnAgregar);
		
		panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.activeCaption, SystemColor.activeCaption));
		panel.setBounds(204, 135, 394, 318);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(154, 40, 232, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(154, 100, 232, 20);
		panel.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(154, 160, 177, 20);
		panel.add(txtUsuario);
		
		jcbPerfil = new JComboBox();
		jcbPerfil.setModel(new DefaultComboBoxModel(new String[] {"Administrador", "Portero"}));
		jcbPerfil.setBounds(163, 280, 106, 20);
		panel.add(jcbPerfil);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(26, 32, 118, 29);
		panel.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(26, 93, 118, 29);
		panel.add(lblApellido);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre De Usuario");
		lblNombreDeUsuario.setBounds(26, 154, 118, 29);
		panel.add(lblNombreDeUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setBounds(26, 215, 118, 29);
		panel.add(lblContrasea);
		
		JLabel lblPerfil = new JLabel("Perfil");
		lblPerfil.setBounds(26, 276, 118, 29);
		panel.add(lblPerfil);
		
		tpfClave = new JPasswordField();
		tpfClave.setBounds(154, 220, 142, 20);
		panel.add(tpfClave);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(97, 490, 608, 163);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		MostrarDatos();
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBounds(0, 0, 850, 50);
		contentPane.add(panel_2);
		
		JLabel label = new JLabel("Mantenimiento De Usuario");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Tahoma", Font.PLAIN, 30));
		label.setBackground(SystemColor.activeCaption);
		panel_2.add(label);
		
		btnActualizar = new JButton("Actualizar Usuario");
		btnActualizar.setEnabled(false);
		btnActualizar.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/1396563105_refresh.png")));
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actualizar();
					MostrarDatos();
					Limpiar();
					txtUsuario.setEnabled(true);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnActualizar.setBounds(344, 67, 145, 43);
		contentPane.add(btnActualizar);
		
		btnAtras = new JButton("Atras");
		btnAtras.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/ANTERIOR.JPG")));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal principal = new Principal();
				principal.setVisible(true);
				MantenimientoUsuario.this.dispose();
			}
		});
		btnAtras.setBounds(665, 67, 127, 43);
		contentPane.add(btnAtras);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cancelar();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnCancelar.setVisible(false);
		btnCancelar.setIcon(new ImageIcon(MantenimientoUsuario.class.getResource("/Iconos/1396563109_Delete.png")));
		btnCancelar.setBounds(665, 150, 127, 43);
		contentPane.add(btnCancelar);
		
		setLocationRelativeTo(null);
	}
	//Metodo para Mostrar los datos.
	public void MostrarDatos() throws SQLException{
        String [] cabecera = {"Nombre ","Apellido ","Nombre de usuario ","Contraseña ","Perfil de usuario "};
        modelo.setColumnIdentifiers(cabecera);
        modelo.setRowCount(0);
        mdu.Mostrar();
	for(Usuario u :mdu.arrayUsuario){
	modelo.addRow(new Object[] {u.getNombre(),u.getApellido(),u.getUsuario(),u.getClave(),u.getPerfil()});
	}
        table.setModel(modelo);	
    }
	
    //Metodo Para Agregar Usuario.
	@SuppressWarnings("deprecation")
	public void agregar(){
		if(VerificarCampos()){
		mdu.Ingresar(txtUsuario.getText(),txtNombre.getText(),txtApellido.getText(),tpfClave.getText(),(String) jcbPerfil.getSelectedItem());
	}
	}
	public void cargarTabla() throws ClassNotFoundException, SQLException{
		 modelo.setRowCount(0);
		 mdu.Mostrar();
	       for (Usuario u : mdu.arrayUsuario) {
	           modelo.addRow(new Object[]{u.getNombre(),u.getApellido(),u.getUsuario(),u.getClave(),u.getPerfil()});
	       }
	 }
	// Metodo Para Eliminar Usuario.
	public void eliminar(){
		if(table.getSelectedRow() != -1){
		int row = table.getSelectedRow();
		String datos = (String) table.getValueAt(row, 2);
		mdu.Eliminar(datos);
		}else
			JOptionPane.showMessageDialog(null, "Debe Seleccionar el Usuario que desee Eliminar");
	}
	//Metodo para Modificar Datos.
	@SuppressWarnings("deprecation")
	public void modificar() throws SQLException{
		 int row = table.getSelectedRow();
		 if(row>=0){
			 btnActualizar.setEnabled(true);
			 btnAgregar.setEnabled(false);
			 btnEliminar.setEnabled(false);
			 btnModificar.setEnabled(false);
			 btnCancelar.setVisible(true);
			 btnAtras.setEnabled(false);
		 String usuario = (String)table.getValueAt(row,2);
		 mdu.Modificar(usuario);
		 for (Usuario u : mdu.arrayUsuario) {
			 Object[] usuarioModificado = new Object[]{u.getNombre(),u.getApellido(),u.getUsuario(),u.getClave(),u.getPerfil()};
			 txtNombre.setText((String)usuarioModificado[0]);
			 txtApellido.setText((String)usuarioModificado[1]);
			 txtUsuario.setText((String)usuarioModificado[2]);
			 txtUsuario.enable(false);
			 tpfClave.setText((String)usuarioModificado[3]);
			 jcbPerfil.setSelectedItem((String)usuarioModificado[4]);
		 }
		 }else{
			 JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
		 }
	 }
	//Metodo Para Actualizar Datos.
	@SuppressWarnings("deprecation")
	public void actualizar() throws ClassNotFoundException, SQLException{
		 mdu.actualizar(txtNombre.getText(), txtApellido.getText(), tpfClave.getText(),(String)jcbPerfil.getSelectedItem(),txtUsuario.getText());
		 cargarTabla();
		 btnActualizar.setEnabled(false);
		 btnAgregar.setEnabled(true);
		 btnEliminar.setEnabled(true);
		 btnModificar.setEnabled(true);
		 btnAtras.setEnabled(true);
		 btnCancelar.setVisible(false);
	 }
	public void Cancelar() throws ClassNotFoundException, SQLException{
		cargarTabla();
		 btnActualizar.setEnabled(false);
		 btnAgregar.setEnabled(true);
		 btnEliminar.setEnabled(true);
		 btnModificar.setEnabled(true);
		 btnAtras.setEnabled(true);
		 btnCancelar.setVisible(false);
		 Limpiar();
		 txtUsuario.setEnabled(true);
	}
	public void Limpiar(){
		txtNombre.setText("");
		txtApellido.setText("");
		txtUsuario.setText("");
		tpfClave.setText("");
		jcbPerfil.setSelectedIndex(0);
	}
	
	public boolean VerificarCampos(){
		JTextField txt = new JTextField();

		for ( Component c: panel.getComponents()){
			if(c.getClass().getName().equals(txt.getClass().getName())){
				txt = (JTextField)c;
				if(txt.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe Llenar todos los campos", "Alerta", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}
		return true;
	}
}
