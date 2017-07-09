package GuisPortero;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ClasesPortero.ManejadorDeAsistencia;
import ClasesPortero.ManejadorDeInvitaciones;

public class VentanaSecundaria extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtEvento;
	ButtonGroup grupo = new ButtonGroup();
	VentanaPrincipal principal = new VentanaPrincipal();
	public JLabel lblUsuario;
	int Id;
	DefaultTableModel modelo = new DefaultTableModel();
	int ID;
	private JTextField txtID;
	ManejadorDeAsistencia asistencia2 = new ManejadorDeAsistencia();
	private JLabel lblTotales,lblNohanLlegados;
	String asistencia;
	/**
	 * Create the frame.
	 */
	public VentanaSecundaria() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 542);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		mnOpciones.setIcon(new ImageIcon(VentanaSecundaria.class.getResource("/Iconos/1396563081_Notes.png")));
		menuBar.add(mnOpciones);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Buscar();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		mnOpciones.add(mntmBuscar);
		
		JMenuItem mntmRegistrarVisitaImprevista = new JMenuItem("Registrar visita Imprevista");
		mntmRegistrarVisitaImprevista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarImprevisto registro = new RegistrarImprevisto();
				registro.txtUsuario.setText(lblUsuario.getText());
				registro.setVisible(true);
				registro.idEvento = Id;
				VentanaSecundaria.this.dispose();
			}
		});
		mnOpciones.add(mntmRegistrarVisitaImprevista);
		
		JSeparator separator = new JSeparator();
		mnOpciones.add(separator);
		
		JMenu mnEventos = new JMenu("Eventos");
		mnEventos.setIcon(new ImageIcon(VentanaSecundaria.class.getResource("/Iconos/1396585069_x-office-calendar.png")));
		menuBar.add(mnEventos);
		
		JMenuItem mntmProximosEventos = new JMenuItem("Proximos Eventos");
		mntmProximosEventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProximosEventos eventos = new ProximosEventos();
				eventos.btnAtras.setVisible(true);
				eventos.idEvento = Id;
				eventos.setVisible(true);
				VentanaSecundaria.this.dispose();
			}
		});
		mnEventos.add(mntmProximosEventos);
		
		JMenu mnOpciones_1 = new JMenu("Opciones");
		mnOpciones_1.setIcon(new ImageIcon(VentanaSecundaria.class.getResource("/Iconos/1397243662_gear_wheel.png")));
		menuBar.add(mnOpciones_1);
		
		JMenuItem mntmAtras = new JMenuItem("Atras");
		mntmAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal principal = new VentanaPrincipal();
				principal.setVisible(true);
				VentanaSecundaria.this.dispose();
			}
		});
		mnOpciones_1.add(mntmAtras);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 290, 619, 168);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CargarDatos();
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblNombre.setBounds(38, 90, 96, 27);
		contentPane.add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblApellido.setBounds(38, 142, 96, 27);
		contentPane.add(lblApellido);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelefono.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblTelefono.setBounds(38, 195, 96, 27);
		contentPane.add(lblTelefono);
		
		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblEvento.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblEvento.setBounds(38, 252, 96, 27);
		contentPane.add(lblEvento);
		
		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		txtNombre.setBounds(189, 90, 171, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setEditable(false);
		txtApellido.setColumns(10);
		txtApellido.setBounds(189, 142, 171, 20);
		contentPane.add(txtApellido);
		
		txtTelefono = new JTextField();
		txtTelefono.setEditable(false);
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(189, 195, 171, 20);
		contentPane.add(txtTelefono);
		
		txtEvento = new JTextField();
		txtEvento.setEditable(false);
		txtEvento.setColumns(10);
		txtEvento.setBounds(189, 249, 171, 20);
		contentPane.add(txtEvento);
		
		JPanel panel = new JPanel();
		panel.setBounds(447, 62, 210, 137);
		contentPane.add(panel);
		panel.setLayout(null);
		
		final JCheckBox jcbLllego = new JCheckBox("Llego");
		jcbLllego.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(jcbLllego.isSelected()){
					asistencia = "Si";
				}
			}
		});
		jcbLllego.setBounds(56, 55, 102, 31);
		panel.add(jcbLllego);
		
		grupo.add(jcbLllego);
		JLabel label = new JLabel("Asistencia");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.ITALIC, 18));
		label.setBounds(56, 12, 102, 31);
		panel.add(label);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setIcon(new ImageIcon(VentanaSecundaria.class.getResource("/Iconos/1396562736_save.png")));
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Asistencia();
					conseguirId(Id);
					jcbLllego.setSelected(true);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			}
		});
		btnConfirmar.setBounds(47, 93, 115, 31);
		panel.add(btnConfirmar);
		
		lblUsuario = new JLabel("");
		lblUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsuario.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblUsuario.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblUsuario.setBounds(467, 11, 164, 41);
		contentPane.add(lblUsuario);
		
		JLabel lblId = new JLabel("ID");
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setFont(new Font("Tahoma", Font.ITALIC, 15));
		lblId.setBounds(38, 47, 96, 27);
		contentPane.add(lblId);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setColumns(10);
		txtID.setBounds(189, 47, 171, 20);
		contentPane.add(txtID);
		
		lblTotales = new JLabel();
		lblTotales.setBounds(457, 203, 189, 27);
		contentPane.add(lblTotales);
		
		lblNohanLlegados = new JLabel("Invitados Que no han llegado");
		lblNohanLlegados.setBounds(457, 252, 189, 27);
		contentPane.add(lblNohanLlegados);
		
		try {
			conseguirId(Id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		setLocationRelativeTo(null);
		

	}
	public void conseguirId(int id) throws SQLException{
		this.Id = id;
		this.ID = id;
	
	
		 String [] cabecera = {"ID","Nombre ","Apellido ","Telefono ","Evento","Asistencia"};
		 ManejadorDeInvitaciones invitaciones = new ManejadorDeInvitaciones();
	        modelo.setColumnIdentifiers(cabecera);
	        modelo.setRowCount(0);
	        invitaciones.Mostrar(id);
	        invitaciones.InvitadosRestantes(id);
		for(ClasesPortero.Invitaciones invi:invitaciones.Arrayinvitados){
		modelo.addRow(new Object[] {invi.getID(),invi.getNombre(),invi.getApellido(),invi.getTelefono(),invi.getEvento(),invi.getAsistencia()});
		}
	        table.setModel(modelo);	
	        lblTotales.setText("Invitados Totales" + " "+invitaciones.Arrayinvitados.size());
	        lblNohanLlegados.setText("Invitados Restantes" + " " +invitaciones.ArrayRestantes);
	}
	
	public void CargarDatos(){
		 int row= table.getSelectedRow();
		 if(row >= 0){			 
			 txtID.setText(Integer.toString( (int) modelo.getValueAt(row, 0)));
			 txtNombre.setText((String) modelo.getValueAt(row, 1));
			 txtApellido.setText((String) modelo.getValueAt(row, 2));
			 txtTelefono.setText((String) modelo.getValueAt(row, 3));
			 txtEvento.setText((String) modelo.getValueAt(row, 4));
		 }else{
	        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fila.", "Error",JOptionPane.ERROR_MESSAGE);
	        }		        
	 }
	
	public void Asistencia() throws ClassNotFoundException, SQLException{
		asistencia2.Asistencia(asistencia,txtID.getText());
	}
	
	public void Buscar() throws SQLException{

		 String [] cabecera = {"ID","Nombre ","Apellido ","Telefono ","Evento","Asistencia"};
		 String Nombre = JOptionPane.showInputDialog(null, "Ingrese el Nombre o El Apellido del Invitado");
		 ManejadorDeInvitaciones invitaciones = new ManejadorDeInvitaciones();
	        modelo.setColumnIdentifiers(cabecera);
	        modelo.setRowCount(0);
	        invitaciones.BuscarInvitado(ID,Nombre);
	        if(invitaciones.ArrayBuscar.isEmpty()){
	        	JOptionPane.showMessageDialog(null,"Error");
	        	try {
	    			conseguirId(Id);
	    		} catch (SQLException e1) {
	    			e1.printStackTrace();
	    		}
	    		
	        }
	        else{
		for(ClasesPortero.Invitaciones invi:invitaciones.ArrayBuscar){
		modelo.addRow(new Object[] {invi.getID(),invi.getNombre(),invi.getApellido(),invi.getTelefono(),invi.getEvento(),invi.getAsistencia()});
		}
	        table.setModel(modelo);	
	}
	}
}
