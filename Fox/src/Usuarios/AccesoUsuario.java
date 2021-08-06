package Usuarios;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Menu.Opciones;
import formularios.usuarios;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

public class AccesoUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtContraseña;
	private int cont=0;
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
		txtUsuario.setText(null);
		txtContraseña.setText(null);
		} 
	
	public Boolean verificarDatos(String tipoDato ,String dato) {
		PreparedStatement ps1;
		ResultSet rs1 ;
		Connection con = null;
		try {
			con= getConnection();
			ps1 = con.prepareStatement("select * from usuarios where "+tipoDato+" = '"+dato+"'");
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
					AccesoUsuario frame = new AccesoUsuario();
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
	public AccesoUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 255);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("inactiveCaption"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(10, 65, 57, 14);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(100, 62, 247, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 113, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("INGRESO DE USUARIOS");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(137, 11, 150, 14);
		contentPane.add(lblNewLabel_2);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(100, 110, 247, 20);
		contentPane.add(txtContraseña);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				
				try {
					con= getConnection();
					String pass,user;
					pass=String.valueOf(txtContraseña.getPassword());
					user=txtUsuario.getText();
					ps = con.prepareStatement("select * from usuarios where alias ='"+user+"' and contraseña ='"+pass+"'");
					rs = ps.executeQuery();
					if(rs.next()) {
						JOptionPane.showMessageDialog(null, "Acceso concedido");
						Opciones op = new Opciones(); 
						op.setVisible(true);
						dispose();
					}else {
						JOptionPane.showMessageDialog(null, "Acceso denegado usuario no registrado");
						if(verificarDatos("contraseña", pass)==false &&  verificarDatos("alias", user)==false){
							JOptionPane.showMessageDialog(null, "Usuario y Contraseña incorrecta");
							limpiarcajas();
						}else {
							if( verificarDatos("contraseña", pass)==false){
								JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
								txtContraseña.setText(null);
							}else {
								if(verificarDatos("alias", user)==false) {
									JOptionPane.showMessageDialog(null, "Usuario incorrecto");	
									txtUsuario.setText(null);
								}
							}
						}
						cont++;
					}
					if(cont==3) {
						JOptionPane.showMessageDialog(null, "Demasiados intentos fallidos");
						dispose();
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnIngresar.setBounds(137, 159, 94, 31);
		contentPane.add(btnIngresar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(253, 159, 94, 31);
		contentPane.add(btnSalir);
	}
}
