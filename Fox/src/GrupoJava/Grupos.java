package GrupoJava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Grupos extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtClave;
	private JTextField txtNombre;
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
		//	txtCodigo.setText(null);
			txtNombre.setText(null);
			
		}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Grupos frame = new Grupos();
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
	
	
	
	public Grupos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("GRUPOS");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBounds(98, 27, 100, 14);
		contentPane.add(lblNewLabel);
		
		txtClave = new JTextField();
		txtClave.setBounds(95, 72, 129, 20);
		contentPane.add(txtClave);
		txtClave.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO grupos(nombre) values(?)");
					ps.setString(1,txtNombre.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Guardado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Guardar Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnGuardar.setBounds(55, 168, 89, 23);
		contentPane.add(btnGuardar);
		
		JLabel lblNewLabel_1 = new JLabel("Clave:");
		lblNewLabel_1.setBounds(10, 75, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setBounds(10, 125, 59, 14);
		contentPane.add(lblNewLabel_2);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(95, 121, 223, 22);
		contentPane.add(txtNombre);
		txtId = new JTextField();
		txtId.setBounds(378, 41, 82, 20);
		contentPane.add(txtId);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("update grupos set nombre = ? where nombre = ?");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtClave.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Modificado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Modificar el Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}

		});
		btnModificar.setBounds(154, 168, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from grupos where nombre = ?");
					ps.setString(1,txtClave.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Grupo Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Eliminar Grupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
				}
			}
			
		});
		btnEliminar.setBounds(253, 168, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnNewButton_3 = new JButton("Limpiar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
						txtClave.setText(null);
						txtNombre.setText(null);
						txtId.setText(null);
			}
		});
		btnNewButton_3.setBounds(55, 205, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Salir");
		btnNewButton_4.setBounds(179, 202, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("select * from grupos where nombre = ?");
					ps.setString(1,txtClave.getText());
					
					rs = ps.executeQuery();
					if(rs.next()) {
						txtId.setText(rs.getString("codigo"));
						
						txtNombre.setText(rs.getString("nombre"));
						//txtClave.setText(null);
					}else {
						JOptionPane.showMessageDialog(null, "no existe grupo con ese nombre");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		
		btnBuscar.setBounds(378, 71, 89, 23);
		contentPane.add(btnBuscar);
		
		
		
		JLabel lblNewLabel_3 = new JLabel("Id:");
		lblNewLabel_3.setBounds(344, 47, 24, 14);
		contentPane.add(lblNewLabel_3);
	}
}
