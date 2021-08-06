package Clientes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class Cliente extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCedula;
	private JTextField txtApellido;
	private JTextField txtEdad;
	private JTextField txtFecha;

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
			JOptionPane.showMessageDialog(null,"Conexion exitosa");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	private void limpiarcajas() {
		txtCodigo.setText(null);
		txtNombre.setText(null);
		txtDireccion.setText(null);
		txtTelefono.setText(null);
		txtCedula.setText(null);
		txtApellido.setText(null);
		txtEdad.setText(null);
		txtFecha.setText(null);
	}
	private int calcularEdad() {
        java.util.Date date = new java.util.Date(Date.valueOf(txtFecha.getText()).getTime());
        java.util.Calendar fechaNacimiento = Calendar.getInstance();
        java.util.Calendar fechaActual = Calendar.getInstance();
        fechaNacimiento.setTime(date);
        int añoNacimiento = fechaNacimiento.get(Calendar.YEAR);
        int añoActual = fechaActual.get(Calendar.YEAR);
        int edad = añoActual-añoNacimiento;
        return edad;
       
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente();
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
	public Cliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Clientes");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
		lblNewLabel.setBounds(222, 11, 72, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(22, 45, 52, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(22, 70, 60, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Apellido:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(22, 95, 60, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Edad:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(22, 205, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Fecha de nacimiento:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(22, 241, 129, 19);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Cedula:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(22, 180, 52, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Telefono:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBackground(Color.BLACK);
		lblNewLabel_7.setBounds(22, 155, 65, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Direccion:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(22, 124, 65, 14);
		contentPane.add(lblNewLabel_8);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(92, 42, 304, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(92, 67, 304, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setBounds(92, 121, 304, 20);
		contentPane.add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(92, 152, 304, 20);
		contentPane.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCedula = new JTextField();
		txtCedula.setBounds(92, 177, 304, 20);
		contentPane.add(txtCedula);
		txtCedula.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setBounds(92, 92, 304, 20);
		contentPane.add(txtApellido);
		txtApellido.setColumns(10);
		
		txtEdad = new JTextField();
		txtEdad.setBounds(92, 202, 304, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);
		
		txtFecha = new JTextField();
		txtFecha.setBounds(161, 240, 154, 20);
		contentPane.add(txtFecha);
		txtFecha.setColumns(10);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("update clientes set nombre = ?,apellido = ?,fechanacimiento = ?,edad = ?,telefono = ?,direccion = ? where cedula = ?");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtApellido.getText());
					ps.setDate(3,Date.valueOf(txtFecha.getText()));
					ps.setInt(4,Integer.parseInt(txtEdad.getText()));
					ps.setInt(5,Integer.parseInt(txtTelefono.getText()));
					ps.setString(6,txtDireccion.getText());
					ps.setInt(7,Integer.parseInt(txtCedula.getText()));
					int res;
					if(Integer.parseInt(txtEdad.getText())>=18 && Integer.parseInt(txtEdad.getText()) == calcularEdad()) {
						res = ps.executeUpdate();
					}else {
						if(Integer.parseInt(txtEdad.getText()) != calcularEdad()) {
							JOptionPane.showMessageDialog(null,"La edad no concuerda con la fecha");
							res=0;
							txtEdad.setText(null);
						}else {
							JOptionPane.showMessageDialog(null,"EL cliente no posee la edad suficiente para ser ingresado al sistema");
							res=0;
							txtEdad.setText(null);
						}
						
					}
					if (res > 0) {
						JOptionPane.showMessageDialog(null," Los datos del cliente han sido modificados");
						limpiarcajas();
						
						
					}else {
						JOptionPane.showMessageDialog(null,"No se a podido modificar los datos del cliente");
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(109, 283, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from clientes where cedula = ?");
					ps.setInt(1,Integer.parseInt(txtCedula.getText()));
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"El cliente ha sido eliminado del sistema");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"No se a podido eliminar al cliente del sistema");
						limpiarcajas();
					}
					
					con.close();
					
					}catch (Exception e ) {
					System.err.println(e);
					}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.setBounds(208, 283, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("select * from clientes where cedula = ?");
					ps.setInt(1,Integer.parseInt(txtCedula.getText()));
					
					rs = ps.executeQuery();
					if(rs.next()) {
						txtCodigo.setText(rs.getString("codigo"));
						txtNombre.setText(rs.getString("nombre"));
						txtApellido.setText(rs.getString("apellido"));
						txtFecha.setText(rs.getString("fechanacimiento"));
						txtEdad.setText(rs.getString("edad"));
						txtTelefono.setText(rs.getString("telefono"));
						txtDireccion.setText(rs.getString("direccion"));
	
					}else {
						JOptionPane.showMessageDialog(null, "No existe ningun cliente con esos datos en el sistema");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setBounds(307, 283, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Guardar");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO clientes(nombre,apellido,fechanacimiento,edad,cedula,telefono,direccion) values(?,?,?,?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtApellido.getText());
					ps.setDate(3,Date.valueOf(txtFecha.getText()));
					ps.setInt(4,Integer.parseInt(txtEdad.getText()));
					ps.setInt(5,Integer.parseInt(txtCedula.getText()));
					ps.setInt(6,Integer.parseInt(txtTelefono.getText()));
					ps.setString(7,txtDireccion.getText());
					int res;
					if(Integer.parseInt(txtEdad.getText())>=18 && Integer.parseInt(txtEdad.getText()) == calcularEdad()) {
						res = ps.executeUpdate();
					}else {
						if(Integer.parseInt(txtEdad.getText()) != calcularEdad()) {
							JOptionPane.showMessageDialog(null,"La edad no concuerda con la fecha");
							res=0;
							txtEdad.setText(null);
						}else {
							JOptionPane.showMessageDialog(null,"EL cliente no posee la edad suficiente para ser ingresado al sistema");
							res=0;
							txtEdad.setText(null);
						}
					}
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Cliente Guardado en el Sistema");
						limpiarcajas();
						
						
					}else {
						JOptionPane.showMessageDialog(null,"EL CLIENTE NO SE HA PODIDO GUARDAR EN EL SISTEMA");
						
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.setBounds(10, 283, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Limpiar");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarcajas();
			}
		});
		btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_4.setBounds(404, 283, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Calcular Edad");
		btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtEdad.setText(String.valueOf(calcularEdad()));
			}
		});
		btnNewButton_5.setBounds(325, 239, 115, 23);
		contentPane.add(btnNewButton_5);
	}

}
