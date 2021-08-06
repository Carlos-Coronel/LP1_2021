package SegundaParcial;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class automotores extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	
	public static final String URL = "jdbc:postgresql://localhost:5432/FOXJAVA";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "97620040";
	
	PreparedStatement ps;
	ResultSet rs ;
	
	private JTextField txtCodigo;
	private JTextField txtNombre;
	private JTextField txtColor;
	private JTextField txtModelo;
	private JTextField txtMotor;
	private JTextField txtChasis;
	private JTextField txtMarca;
	private JTextField txtPrecio;
	private JTextField txtChapa;
	private JTextField txtKilometraje;
	private JTextField txtAño;
	
	
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
		txtColor.setText(null);
		txtModelo.setText(null);
		txtMotor.setText(null);
		txtChasis.setText(null);
		txtMarca.setText(null);
		txtPrecio.setText(null);
		txtChapa.setText(null);
		txtKilometraje.setText(null);
		txtAño.setText(null);
		}
	
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					automotores frame = new automotores();
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
	public automotores() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Automotores");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setBounds(260, 11, 107, 20);
		contentPane.add(lblNewLabel);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(82, 57, 196, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(82, 88, 196, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
				con= getConnection();
				ps = con.prepareStatement("select * from automotores where nombre = ?");
				ps.setString(1,txtNombre.getText());
				rs = ps.executeQuery();
				if(rs.next()) {
					txtCodigo.setText(rs.getString("codigo"));
					txtColor.setText(rs.getString("color"));
					txtAño.setText(rs.getString("año"));
					txtMarca.setText(rs.getString("marca"));
					txtModelo.setText(rs.getString("modelo"));
					txtPrecio.setText(rs.getString("precio"));
					txtKilometraje.setText(rs.getString("kilometraje"));
					txtChapa.setText(rs.getString("chapa"));
					txtChasis.setText(rs.getString("chasis"));
					txtMotor.setText(rs.getString("motor"));
					
				}else {
					JOptionPane.showMessageDialog(null, "no existe ningun articulo con ese nombre");
				}
				
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnBuscar.setBounds(378, 334, 89, 23);
		contentPane.add(btnBuscar);
		
		txtColor = new JTextField();
		txtColor.setBounds(82, 119, 196, 20);
		contentPane.add(txtColor);
		txtColor.setColumns(10);
		
		txtModelo = new JTextField();
		txtModelo.setBounds(82, 150, 196, 20);
		contentPane.add(txtModelo);
		txtModelo.setColumns(10);
		
		txtMotor = new JTextField();
		txtMotor.setBounds(82, 181, 196, 20);
		contentPane.add(txtMotor);
		txtMotor.setColumns(10);
		
		txtChasis = new JTextField();
		txtChasis.setBounds(82, 212, 196, 20);
		contentPane.add(txtChasis);
		txtChasis.setColumns(10);
		
		txtMarca = new JTextField();
		txtMarca.setBounds(378, 91, 171, 20);
		contentPane.add(txtMarca);
		txtMarca.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setBounds(378, 122, 171, 20);
		contentPane.add(txtPrecio);
		txtPrecio.setColumns(10);
		
		txtChapa = new JTextField();
		txtChapa.setBounds(82, 243, 196, 20);
		contentPane.add(txtChapa);
		txtChapa.setColumns(10);
		
		txtKilometraje = new JTextField();
		txtKilometraje.setBounds(92, 274, 171, 20);
		contentPane.add(txtKilometraje);
		txtKilometraje.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Codigo:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(10, 63, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(10, 94, 62, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Color:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(10, 125, 44, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Motor:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(8, 187, 46, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Chasis:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(10, 218, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Modelo:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(8, 153, 46, 17);
		contentPane.add(lblNewLabel_6);
		
		txtAño = new JTextField();
		txtAño.setBounds(378, 57, 171, 20);
		contentPane.add(txtAño);
		txtAño.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("A\u00F1o:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(332, 63, 35, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_4_1 = new JLabel("Chapa:");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_1.setBounds(10, 249, 46, 14);
		contentPane.add(lblNewLabel_4_1);
		
		JLabel lblNewLabel_4_2 = new JLabel("Marca:");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_2.setBounds(322, 94, 46, 14);
		contentPane.add(lblNewLabel_4_2);
		
		JLabel lblNewLabel_4_3 = new JLabel("Precio:");
		lblNewLabel_4_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_3.setBounds(322, 125, 46, 14);
		contentPane.add(lblNewLabel_4_3);
		
		JLabel lblNewLabel_4_4 = new JLabel("Kilometraje:");
		lblNewLabel_4_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4_4.setBounds(10, 277, 78, 14);
		contentPane.add(lblNewLabel_4_4);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(477, 334, 89, 23);
		contentPane.add(btnSalir);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("update automotores set marca=?,kilometraje=?,color=?,año=?,motor=?,precio=?,chasis=?,modelo=?,chapa=? where nombre = ?");
					ps.setString(1,txtMarca.getText());
					ps.setInt(2,Integer.parseInt(txtKilometraje.getText()));
					ps.setString(3,txtColor.getText());
					ps.setInt(4,Integer.parseInt(txtAño.getText()));
					ps.setInt(5,Integer.parseInt(txtMotor.getText()));
					ps.setDouble(6,Double.parseDouble(txtPrecio.getText()));
					ps.setString(7,txtChasis.getText());
					ps.setString(8,txtModelo.getText());
					ps.setString(9,txtChapa.getText());
					ps.setString(10,txtNombre.getText());
					int res;
					if(txtChasis.getText().equals(txtChapa.getText())){
						res=0;
						JOptionPane.showMessageDialog(null,"La chapa y el chasis deben ser distintos");
					}else {
						 res = ps.executeUpdate();
					}
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Los datos del vehiculo an sido modificados");
						limpiarcajas();
							
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL MODIFIACAR LOS DATOS DEL VEHICULO");
					}
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnModificar.setBounds(174, 334, 89, 23);
		contentPane.add(btnModificar);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO automotores(nombre,marca,kilometraje,color,año,motor,precio,chasis,modelo,chapa) values(?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtMarca.getText());
					ps.setInt(3,Integer.parseInt(txtKilometraje.getText()));
					ps.setString(4,txtColor.getText());
					ps.setInt(5,Integer.parseInt(txtAño.getText()));
					ps.setInt(6,Integer.parseInt(txtMotor.getText()));
					ps.setDouble(7,Double.parseDouble(txtPrecio.getText()));
					ps.setString(8,txtChasis.getText());
					ps.setString(9,txtModelo.getText());
					ps.setString(10,txtChapa.getText());
					int res;
					if(txtChasis.getText().equals(txtChapa.getText())){
						res=0;
						JOptionPane.showMessageDialog(null,"La chapa y el chasis deben ser distintos");
					}else {
						 res = ps.executeUpdate();
					}
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Vehiculo guardado en el sitema");
						limpiarcajas();
							
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL GUARDAR EL VEHICULO");
						
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnGuardar.setBounds(71, 334, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from automotores where nombre = ?");
					ps.setString(1,txtNombre.getText());
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Vehiculo eliminado del sistema");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL ELIMINAR EL VEHICULO");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnEliminar.setBounds(278, 334, 89, 23);
		contentPane.add(btnEliminar);
	}
}
