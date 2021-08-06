package Votacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Candidatos extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCargo;
	private JTextField txtPartido;
	private JTextField txtLista;

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
		txtLista.setText(null);
		txtPartido.setText(null);
		txtCargo.setText(null);
		} 
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Candidatos frame = new Candidatos();
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
	public Candidatos() {
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
		
		JLabel lblNewLabel_1 = new JLabel("Cargo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 95, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Partido:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 126, 65, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Candidato");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(169, 23, 112, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(85, 61, 251, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCargo = new JTextField();
		txtCargo.setBounds(85, 92, 251, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtPartido = new JTextField();
		txtPartido.setBounds(85, 123, 251, 20);
		contentPane.add(txtPartido);
		txtPartido.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Lista:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(10, 157, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		txtLista = new JTextField();
		txtLista.setBounds(85, 154, 251, 20);
		contentPane.add(txtLista);
		txtLista.setColumns(10);
		
		JButton btnNewButton = new JButton("Cargar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO candidatos(nombre,cargo,partido,lista) values(?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtCargo.getText());
					ps.setString(3,txtPartido.getText());
					ps.setInt(4, Integer.parseInt(txtLista.getText()));
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Los datos del candidato han cargado");
						limpiarcajas();
		
					}else {
						JOptionPane.showMessageDialog(null,"Error al cargar los datos del candidato");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
			}
		});
		btnNewButton.setBounds(32, 196, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarcajas();
			}
		});
		btnNewButton_1.setBounds(247, 196, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Eliminar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from candidatos where nombre = ?");
					ps.setString(1,txtNombre.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"candidato Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Eliminar al candidato");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnNewButton_2.setBounds(141, 196, 89, 23);
		contentPane.add(btnNewButton_2);
	}
}
