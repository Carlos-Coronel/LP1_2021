package formularios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;

public class usuarios extends JFrame {

	public static final String URL = "jdbc:postgresql://localhost:5432/FOXJAVA";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "97620040";
	
	PreparedStatement ps;
	ResultSet rs ;
	
	
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = (Connection) DriverManager.getConnection(URL,USERNAME,PASSWORD);
			JOptionPane.showMessageDialog(null,"Conexion exitosa");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	
	private void limpiarcajas() {
	//	txtcodigo.setText(null);
		txtnombre.setText(null);
		txtclave.setText(null);
		txtalias.setText(null);
		
	}

	
	

	private JPanel contentPane;
	private JTextField txtnombre;
	private JLabel lblNewLabel_1;
	private JButton btnLimpiar;
	private JButton btnSalir;
	private JLabel lblNewLabel_2;
	private JTextField txtalias;
	private JLabel lblNewLabel_3;
	private JTextField txtclave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarios frame = new usuarios();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public usuarios() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 458, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(29, 73, 54, 14);
		contentPane.add(lblNewLabel);
		
		txtnombre = new JTextField();
		txtnombre.setBounds(87, 70, 244, 20);
		contentPane.add(txtnombre);
		txtnombre.setColumns(10);
		 this.setLocationRelativeTo(null); 
		 
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO usuarios(nombre,contraseña,alias) values(?,?,?)");
					ps.setString(1,txtnombre.getText());
					ps.setString(2,txtclave.getText());
					ps.setString(3,txtalias.getText());
					
int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Usuario Guardado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Erro al guardar el usuario ");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			}
		});
		btnGuardar.setBounds(44, 166, 89, 23);
		contentPane.add(btnGuardar);
		
		lblNewLabel_1 = new JLabel("USUARIOS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(174, 11, 143, 36);
		contentPane.add(lblNewLabel_1);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiarcajas();
			}
		});
		btnLimpiar.setBounds(145, 166, 89, 23);
		contentPane.add(btnLimpiar);
		
		btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(244, 166, 89, 23);
		contentPane.add(btnSalir);
		
		lblNewLabel_2 = new JLabel("Clave:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(39, 104, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		txtalias = new JTextField();
		txtalias.setBounds(87, 135, 244, 20);
		contentPane.add(txtalias);
		txtalias.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Alias:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(45, 141, 38, 14);
		contentPane.add(lblNewLabel_3);
		
		txtclave = new JTextField();
		txtclave.setBounds(87, 101, 244, 20);
		contentPane.add(txtclave);
		txtclave.setColumns(10);
	}
}

