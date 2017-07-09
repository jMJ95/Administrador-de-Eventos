package GuisPortero;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ClasesPortero.ManejadorDeImprevistos;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class RegistrarImprevisto extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	public JTextField txtUsuario;
	private JTextArea txtRazon;
	ManejadorDeImprevistos  imprevisto = new ManejadorDeImprevistos ();
	VentanaSecundaria secundaria = new VentanaSecundaria();
	private JLabel lblUsuario;
	public  int idEvento;

	/**
	 * Create the frame.
	 */
	public RegistrarImprevisto() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 551, 524);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(8, 0, 518, 67);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRegistrarVisitaImprevista = new JLabel("Registrar Visita Imprevista");
		lblRegistrarVisitaImprevista.setFont(new Font("Tw Cen MT", Font.PLAIN, 30));
		lblRegistrarVisitaImprevista.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarVisitaImprevista.setBounds(84, 0, 354, 56);
		panel.add(lblRegistrarVisitaImprevista);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(SystemColor.activeCaption);
		panel_1.setBounds(60, 126, 414, 348);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(172, 56, 186, 26);
		panel_1.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(172, 110, 186, 26);
		panel_1.add(txtApellido);
		
		txtUsuario = new JTextField();
		txtUsuario.setEditable(false);
		txtUsuario.setColumns(10);
		txtUsuario.setBounds(172, 168, 133, 26);
		panel_1.add(txtUsuario);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(174, 223, 184, 114);
		panel_1.add(scrollPane);
		
		txtRazon = new JTextArea();
		txtRazon.setLineWrap(true);
		scrollPane.setViewportView(txtRazon);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblNewLabel.setBounds(39, 54, 123, 26);
		panel_1.add(lblNewLabel);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblApellido.setBounds(37, 108, 123, 26);
		panel_1.add(lblApellido);
		
		lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblUsuario.setBounds(37, 166, 123, 26);
		panel_1.add(lblUsuario);
		
		JLabel lblRazonPorLa = new JLabel("Razon de Entrada");
		lblRazonPorLa.setFont(new Font("Tw Cen MT", Font.PLAIN, 18));
		lblRazonPorLa.setBounds(24, 246, 133, 59);
		panel_1.add(lblRazonPorLa);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.setIcon(new ImageIcon(RegistrarImprevisto.class.getResource("/Iconos/ANTERIOR.JPG")));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaSecundaria secundaria = new VentanaSecundaria();
				secundaria.setVisible(true);
				RegistrarImprevisto.this.dispose();
				try {
					secundaria.conseguirId(idEvento);
				} catch (SQLException el) {
					// TODO Auto-generated catch block
					el.printStackTrace();
				}
			}
		});
		btnAtras.setBounds(98, 77, 120, 38);
		contentPane.add(btnAtras);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon(RegistrarImprevisto.class.getResource("/Iconos/1396563123_Add.png")));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					Ingresar();
			}
		});
		btnRegistrar.setBounds(316, 78, 120, 38);
		contentPane.add(btnRegistrar);
		
		setLocationRelativeTo(null);
		
	}
	
	public void Ingresar(){
		imprevisto.Ingresar(txtNombre.getText(), txtApellido.getText(), txtUsuario.getText(), txtRazon.getText());
		Limpiar();
	}
	
	public void Limpiar(){
		txtNombre.setText("");
		txtApellido.setText("");
		txtRazon.setText("");
	}
}
