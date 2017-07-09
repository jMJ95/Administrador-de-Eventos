package GuisAdministrador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ClasesAdministrador.Evento;
import ClasesAdministrador.ManejadorDeEvento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class VentanaDeEventos extends JFrame {
	DefaultTableModel modelo = new DefaultTableModel();
	ManejadorDeEvento mde = new ManejadorDeEvento();
	int id_evento = 0;
	JButton btnAgregarEvento ,btnEliminarEvento,btnModificarEvento,btnActualizar;
	private JPanel contentPane;
	private JTextField NombreEvento;
	private JTextField UbicacionEvento;
	private JTable table;
	private JFormattedTextField fecha;
	private JPanel panelDatos;
	private JButton btnCancelar;

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public VentanaDeEventos() throws SQLException, ParseException {
		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 766, 593);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBounds(0, 0, 750, 50);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblMantenimientoDeEventos = new JLabel("Mantenimiento De Eventos");
		lblMantenimientoDeEventos.setBounds(188, 5, 355, 37);
		lblMantenimientoDeEventos.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeEventos.setForeground(Color.BLUE);
		lblMantenimientoDeEventos.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblMantenimientoDeEventos.setBackground(SystemColor.activeCaption);
		panel.add(lblMantenimientoDeEventos);

		btnAgregarEvento = new JButton("Agregar Evento");
		btnAgregarEvento.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/1396563123_Add.png")));
		btnAgregarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(VerificarCampos()){
				String Nombre = NombreEvento.getText();
				String Ubicacion = UbicacionEvento.getText();
				String Fecha = fecha.getText();
				new ManejadorDeEvento().Ingresar(Nombre, Ubicacion, Fecha);
				try {
					actualizarTabla();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				limpiarText();
			}
			}
		});
		btnAgregarEvento.setBounds(6, 72, 146, 43);
		contentPane.add(btnAgregarEvento);

		btnEliminarEvento = new JButton("Eliminar Evento");
		btnEliminarEvento.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/1396563109_Delete.png")));
		btnEliminarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					EliminarDatos();
					actualizarTabla();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnEliminarEvento.setBounds(462, 72, 146, 43);
		contentPane.add(btnEliminarEvento);

		btnModificarEvento = new JButton("Modificar Evento");
		btnModificarEvento.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/1396563081_Notes.png")));
		btnModificarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarDatos();
				btnCancelar.setVisible(true);
				try {
					actualizarTabla();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnModificarEvento.setBounds(158, 72, 146, 43);
		contentPane.add(btnModificarEvento);

		panelDatos = new JPanel();
		panelDatos.setLayout(null);
		panelDatos.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, SystemColor.textHighlight, SystemColor.activeCaption, SystemColor.activeCaption));
		panelDatos.setBounds(178, 135, 394, 220);
		contentPane.add(panelDatos);

		NombreEvento = new JTextField();
		NombreEvento.setColumns(10);
		NombreEvento.setBounds(154, 40, 232, 20);
		panelDatos.add(NombreEvento);

		UbicacionEvento = new JTextField();
		UbicacionEvento.setColumns(10);
		UbicacionEvento.setBounds(154, 100, 232, 20);
		panelDatos.add(UbicacionEvento);

		JLabel lblEvento = new JLabel("Evento");
		lblEvento.setBounds(26, 32, 118, 29);
		panelDatos.add(lblEvento);

		JLabel lblUbicacin = new JLabel("Ubicaci\u00F3n");
		lblUbicacin.setBounds(26, 93, 118, 29);
		panelDatos.add(lblUbicacin);

		JLabel lblFecha = new JLabel("Fecha (yyyy/mm/dd)");
		lblFecha.setBounds(26, 154, 118, 29);
		panelDatos.add(lblFecha);
		
		MaskFormatter formatter = new MaskFormatter("####-##-##");
		
		fecha = new JFormattedTextField(formatter);
		fecha.setFont(new Font("Tahoma", Font.PLAIN, 13));
		fecha.setBounds(154, 158, 116, 20);
		panelDatos.add(fecha);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 394, 750, 149);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/1396563105_refresh.png")));
		btnActualizar.setEnabled(false);
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 actualizarDatos(id_evento);
				try {
					actualizarTabla();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				limpiarText();
			}
		});
		btnActualizar.setBounds(310, 72, 146, 43);
		contentPane.add(btnActualizar);
		
		JButton button = new JButton("Atras");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principal principal = new Principal();
				principal.setVisible(true);
				VentanaDeEventos.this.dispose();
			}
		});
		button.setBounds(614, 72, 126, 42);
		contentPane.add(button);
		button.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/ANTERIOR.JPG")));
		button.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar();
				btnCancelar.setVisible(false);
			}
		});
		btnCancelar.setVisible(false);
		btnCancelar.setIcon(new ImageIcon(VentanaDeEventos.class.getResource("/Iconos/1396563109_Delete.png")));
		btnCancelar.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		btnCancelar.setBounds(614, 148, 126, 42);
		contentPane.add(btnCancelar);

		MostrarDatos();
		
		setLocationRelativeTo(null);
	}
	
	public void MostrarDatos() throws SQLException{
		String [] cabecera = {"Id ","Nombre del Evento ","Ubicacion ","Fecha "};
		modelo.setColumnIdentifiers(cabecera);
		modelo.setNumRows(0);
		mde.Mostrar();
		for(Evento e :mde.arrayEvento){
			modelo.addRow(new Object[] {e.getId(),e.getNombre(),e.getUbicacion(),e.getFecha()});

		}
		table.setModel(modelo);	
	}

	public void actualizarTabla() throws SQLException{
		modelo.setRowCount(0);
		mde.Mostrar();
		for(Evento e :mde.arrayEvento){
			modelo.addRow(new Object[] {e.getId(),e.getNombre(),e.getUbicacion(),e.getFecha()});

		}
	}
	
	 public void EliminarDatos() throws SQLException{
	        int row= table.getSelectedRow();
	        if(row >= 0){
	        int eventoSeleccionado= (int) table.getValueAt(row, 0);
	        mde.Eliminar(eventoSeleccionado);
	        modelo.removeRow(row);
	        }else{
	        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fila.", "Error",JOptionPane.ERROR_MESSAGE);
	        }
	    }
	 
	 public void actualizarDatos(int id){
		 
	        String Nombre = NombreEvento.getText();
	        String Ubicacion = UbicacionEvento.getText();
	        String Fecha = fecha.getText();
	        mde.Modificar(id,Nombre,Ubicacion,Fecha);
	        btnActualizar.setEnabled(false);
	        btnAgregarEvento.setEnabled(true); 
	        btnEliminarEvento.setEnabled(true); 
	        btnModificarEvento.setEnabled(true);
	 }
	 
	 public void limpiarText(){
		 NombreEvento.setText(null);
		 UbicacionEvento.setText(null);
		 fecha.setText(null);
	 }
	 
	 public void modificarDatos(){
		 int row= table.getSelectedRow();
		 if(row >= 0){
		        id_evento= (int) table.getValueAt(row, 0);
		        String Nombre = (String)modelo.getValueAt(row, 1);
		        String Ubicacion = (String)modelo.getValueAt(row, 2);
		        String Fecha = (String)modelo.getValueAt(row, 3);
		        NombreEvento.setText(Nombre);
		        UbicacionEvento.setText(Ubicacion);
		        fecha.setText(Fecha);
		        btnActualizar.setEnabled(true);
		        btnAgregarEvento.setEnabled(false); 
		        btnEliminarEvento.setEnabled(false); 
		        btnModificarEvento.setEnabled(false);
		 }else{
	        	JOptionPane.showMessageDialog(null, "Debe seleccionar una fila.", "Error",JOptionPane.ERROR_MESSAGE);
	        }		        
	 }
	 
	 public boolean VerificarCampos(){
			JTextField txt = new JTextField();

			for ( Component c:panelDatos.getComponents()){
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
	 
	 public void Cancelar(){
		 btnActualizar.setEnabled(false);
	        btnAgregarEvento.setEnabled(true); 
	        btnEliminarEvento.setEnabled(true); 
	        btnModificarEvento.setEnabled(true);
	        limpiarText();
	 }
}
