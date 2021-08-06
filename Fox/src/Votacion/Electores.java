package Votacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class Electores extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCedula;
	private JTextField txtPartido;

	/**
	 * Launch the application.
	 */
	
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
			System.out.println("Conectando con la Base de datos.....Conexion establecida...");
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	private void limpiarcajas() {
		txtNombre.setText(null);
		txtPartido.setText(null);
		txtCedula.setText(null);
		} 
	
	public Boolean verificarDatos(int dato) {
		PreparedStatement ps1;
		ResultSet rs1 ;
		Connection con = null;
		try {
			con= getConnection();
			ps1 = con.prepareStatement("select * from electores where cedula = "+dato);
			rs1 = ps1.executeQuery();
			if(rs1.next()){
					return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Electores frame = new Electores();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Electores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 64, 65, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cedula:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 95, 56, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Partido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 126, 56, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Electores");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(178, 24, 112, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(85, 61, 262, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(85, 92, 262, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtPartido = new JTextField();
		txtPartido.setBounds(85, 123, 262, 20);
		contentPane.add(txtPartido);
		txtPartido.setColumns(10);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO electores(nombre,cedula,partido) values(?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setInt(2,Integer.parseInt(txtCedula.getText()));
					ps.setString(3,txtPartido.getText());
					int res;
					if(verificarDatos(Integer.parseInt(txtCedula.getText())) == true ) {
						res = 0;
						txtCedula.setText(null);
						JOptionPane.showMessageDialog(null,"Error la cedula ya existe");
					}else {
						res = ps.executeUpdate();
					}
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Los datos del elector han cargado");
						limpiarcajas();
		
					}else {
						JOptionPane.showMessageDialog(null,"Error al cargar los datos del elector");
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
			}
		});
		btnNewButton.setBounds(74, 172, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarcajas();
			}
		});
		btnNewButton_1.setBounds(272, 172, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from electores where nombre = ?");
					ps.setString(1,txtNombre.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Elector Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al eliminar al elector");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnNewButton_2.setBounds(173, 172, 89, 23);
		contentPane.add(btnNewButton_2);
	}

}
