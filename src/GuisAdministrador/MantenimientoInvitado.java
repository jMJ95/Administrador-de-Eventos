package GuisAdministrador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import ClasesAdministrador.Evento;
import ClasesAdministrador.Invitado;
import ClasesAdministrador.ManejadorDeEvento;
import ClasesAdministrador.ManejadorDeInvitaciones;
import ClasesAdministrador.ManejadorDeInvitado;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFormattedTextField;

public class MantenimientoInvitado extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	DefaultTableModel modelo2 = new DefaultTableModel();
	private JPanel contentPane;
	private JTextField txtnombre;
	private JTextField txtapellido;
	private JTextField txtdireccion;
	private JTable table;
	private ManejadorDeInvitado mdi =  new ManejadorDeInvitado();
	private ManejadorDeEvento mde = new ManejadorDeEvento();
	@SuppressWarnings("rawtypes")
	private JComboBox sexo ;
	private JButton btnactualizar;
	private JButton btnagregar;
	private JButton btneliminar;
	private JButton btnatras;
	private JButton btnagregarevento;
	Object[] invitadoModificado;
	@SuppressWarnings("rawtypes")
	DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
	private JTable TablaEventos;
	private JFormattedTextField txtTelefono;
	private Panel panel;
	private JButton btnCancelar;
 
	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public MantenimientoInvitado() throws ClassNotFoundException, SQLException {
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 781, 670);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnagregar = new JButton("Agregar");
		btnagregar.setIcon(new ImageIcon(MantenimientoInvitado.class.getResource("/Iconos/1396563123_Add.png")));
		btnagregar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VerificarCampos()){
				try {
					
					mdi.manejarInvitado(txtnombre.getText(), txtapellido.getText(), txtdireccion.getText(),sexo.getSelectedItem().toString(), txtTelefono.getText());
					JOptionPane.showMessageDialog(null,"Invitado Agregado!");
					limpiar();
					
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
				try {
					cargarTabla();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			}
			
		});
		btnagregar.setBounds(46, 67, 133, 40);
		contentPane.add(btnagregar);
		
		btneliminar = new JButton("Eliminar");
		btneliminar.setIcon(new ImageIcon(MantenimientoInvitado.class.getResource("/Iconos/1396563115_user_business_close.png")));
		btneliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					eliminar();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btneliminar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btneliminar.setBounds(404, 67, 133, 40);
		contentPane.add(btneliminar);
		
		btnatras = new JButton("Atras");
		btnatras.setIcon(new ImageIcon(MantenimientoInvitado.class.getResource("/Iconos/ANTERIOR.JPG")));
		btnatras.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnatras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal principal = new Principal();
				principal.setVisible(true);
				MantenimientoInvitado.this.dispose();
			}
		});
		btnatras.setBounds(583, 67, 133, 40);
		contentPane.add(btnatras);
		
		panel = new Panel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(29, 136, 326, 287);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(147, 42, 153, 20);
		panel.add(txtnombre);
		txtnombre.setColumns(10);
		
		txtapellido = new JTextField();
		txtapellido.setColumns(10);
		txtapellido.setBounds(147, 95, 153, 20);
		panel.add(txtapellido);
		
		txtdireccion = new JTextField();
		txtdireccion.setBounds(149, 202, 151, 20);
		panel.add(txtdireccion);
		txtdireccion.setColumns(10);
		
		sexo = new JComboBox();
		sexo.setModel(new DefaultComboBoxModel(new String[] {"Masculino", "Femenino"}));
		sexo.setToolTipText("");
		sexo.setBounds(147, 244, 77, 20);
		panel.add(sexo);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 44, 62, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Apellido");
		lblNewLabel_2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_2.setBounds(26, 101, 62, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono");
		lblNewLabel_3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_3.setBounds(26, 153, 62, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Direccion");
		lblNewLabel_4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_4.setBounds(26, 208, 62, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Sexo");
		lblNewLabel_5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		lblNewLabel_5.setBounds(26, 250, 62, 14);
		panel.add(lblNewLabel_5);
		
		MaskFormatter formatter;
		try {
			formatter = new MaskFormatter("(###) ###-####");
			txtTelefono = new JFormattedTextField(formatter);
			txtTelefono.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtTelefono.setBounds(147, 151, 153, 20);
			panel.add(txtTelefono);
			
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(389, 136, 355, 287);
		contentPane.add(scrollPane);
        
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					modificar();
					btneliminar.setEnabled(true);
					btnCancelar.setVisible(true);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		cargarTabla();
		table.setModel(modelo);
		scrollPane.setViewportView(table);
	
		btnagregarevento = new JButton("Invitar Usuario");
		btnagregarevento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EnviarInvitacion();
			}
		});
		btnagregarevento.setEnabled(false);
		btnagregarevento.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		btnagregarevento.setBounds(104, 536, 188, 49);
		contentPane.add(btnagregarevento);
		
		JLabel lblNewLabel_6 = new JLabel("Eventos");
		lblNewLabel_6.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		lblNewLabel_6.setBounds(517, 465, 128, 19);
		contentPane.add(lblNewLabel_6);
		
		btnactualizar = new JButton("Modificar");
		btnactualizar.setIcon(new ImageIcon(MantenimientoInvitado.class.getResource("/Iconos/1396563105_refresh.png")));
		btnactualizar.setEnabled(false);
		btnactualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actualizar();
					limpiar();
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		btnactualizar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnactualizar.setBounds(225, 68, 133, 40);
		contentPane.add(btnactualizar);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(0, 0, 765, 56);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Manteniento de invitado");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 24));
		label.setBounds(10, 0, 750, 56);
		panel_1.add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(389, 495, 366, 125);
		contentPane.add(scrollPane_1);
		
		TablaEventos = new JTable();
		TablaEventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 btnactualizar.setEnabled(false);
				 btnagregarevento.setEnabled(true);
				 Desactivar();
				 btneliminar.setEnabled(false);
				 
			}
		});
		scrollPane_1.setViewportView(TablaEventos);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar();
				
			}
		});
		btnCancelar.setVisible(false);
		btnCancelar.setIcon(new ImageIcon(MantenimientoInvitado.class.getResource("/Iconos/1396563109_Delete.png")));
		btnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnCancelar.setBounds(225, 466, 133, 40);
		contentPane.add(btnCancelar);
		

		CargarEventos();
		setLocationRelativeTo(null);
		
	}
	
	public void limpiar (){
		 txtnombre.setText("");
		 txtapellido.setText("");
		 txtTelefono.setText("");
		 txtdireccion.setText("");
	 }
	 public void cargarTabla() throws ClassNotFoundException, SQLException{
		 String [] cabecera ={"ID","Nombre","Apellido","Direccion","Sexo","Telefono"};
		 modelo.setColumnIdentifiers(cabecera);
		 modelo.setRowCount(0);
		 mdi.mostrar();
	       for (Invitado i : mdi.invitados) {
	           modelo.addRow(new Object[]{i.getId(),i.getNombre(),i.getApellido(),i.getDireccion(),i.getSexo(),i.getTelefono()});
	       }
	 }
	 public void eliminar() throws ClassNotFoundException, SQLException{
		 int row = table.getSelectedRow();
		 if(row>=0){
		 int datos = (int)table.getValueAt(row,0);
		 mdi.Eliminar(datos);	
		 cargarTabla();
		 }else{
			 JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
		 }
		 btnactualizar.setEnabled(false);
		 btnagregar.setEnabled(true);
		 btnagregarevento.setEnabled(true);
		 btneliminar.setEnabled(true);
		 btnatras.setEnabled(true);
		 btnagregarevento.setEnabled(false);
		 limpiar ();
	 }
	 public void modificar() throws SQLException{
		 int row = table.getSelectedRow();
		 if(row>=0){
			 btnactualizar.setEnabled(true);
			 btnagregar.setEnabled(false);
			 btnagregarevento.setEnabled(false);
			 btneliminar.setEnabled(false);
			 btnatras.setEnabled(false);
		 int invitado = (int)table.getValueAt(row,0);
		 mdi.Modificar(invitado);
		 for (Invitado i : mdi.invitados) {
			 invitadoModificado =new Object[]{i.getNombre(),i.getApellido(),i.getDireccion(),i.getTelefono(),i.getId()};
			 txtnombre.setText((String)invitadoModificado[0]);
			 txtapellido.setText((String)invitadoModificado[1]);
			 txtTelefono.setText((String)invitadoModificado[3]);
			 txtdireccion.setText((String)invitadoModificado[2]);
		 }
		 }else{
			 JOptionPane.showMessageDialog(null,"Debe seleccionar una fila");
		 }
	 }
	 public void actualizar() throws ClassNotFoundException, SQLException{

		 mdi.actualizar(txtnombre.getText(), txtapellido.getText(), txtdireccion.getText(), (String)sexo.getSelectedItem(), txtTelefono.getText(),(int)invitadoModificado[4]);
		 cargarTabla();
		 btnactualizar.setEnabled(false);
		 btnagregar.setEnabled(true);
		 btnagregarevento.setEnabled(true);
		 btneliminar.setEnabled(true);
		 btnatras.setEnabled(true);
		 btnagregarevento.setEnabled(false);
	 }

	 public void CargarEventos() throws SQLException{
		 String [] cabecera = {"Id ","Nombre del Evento ","Ubicacion ","Fecha "};
			modelo2.setColumnIdentifiers(cabecera);
			modelo2.setNumRows(0);
			mde.Mostrar();
			for(Evento e :mde.arrayEvento){
				modelo2.addRow(new Object[] {e.getId(),e.getNombre(),e.getUbicacion(),e.getFecha()});

			}
			TablaEventos.setModel(modelo2);	
	 }
	 
	 public void EnviarInvitacion(){
		 ManejadorDeInvitaciones  invitaciones = new ManejadorDeInvitaciones();
		 int row = table.getSelectedRow();
		 int row2 = TablaEventos.getSelectedRow();
		 if(row>=0 && row2 >= 0 ){
		 int invitado = (int)table.getValueAt(row,0);
		 int evento = (int)TablaEventos.getValueAt(row2, 0);
		 invitaciones.Invitar(invitado, evento);
		 }
		 btnagregar.setEnabled(true);
		 btnagregarevento.setEnabled(true);
		 btneliminar.setEnabled(true);
		 btnatras.setEnabled(true);
		 btnagregarevento.setEnabled(false);
		 limpiar ();
		 ActivarCampos();
	 }
	 
	 public void ActivarCampos(){
		 txtnombre.setEditable(true);
		 txtapellido.setEditable(true);
		 txtTelefono.setEditable(true);
		 txtdireccion.setEditable(true);
		 sexo.setEnabled(true);
	 }
	 
	 public void Desactivar(){
		 txtnombre.setEditable(false);
		 txtapellido.setEditable(false);
		 txtTelefono.setEditable(false);
		 txtdireccion.setEditable(false);
		 sexo.setEnabled(false);
	 }
	 
	 public void Cancelar(){
		 ActivarCampos();
		 limpiar();
		 btnactualizar.setEnabled(false);
		 btnagregar.setEnabled(true);
		 btnagregarevento.setEnabled(true);
		 btneliminar.setEnabled(true);
		 btnatras.setEnabled(true);
		 btnagregarevento.setEnabled(false);
		 btnCancelar.setVisible(false);
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
