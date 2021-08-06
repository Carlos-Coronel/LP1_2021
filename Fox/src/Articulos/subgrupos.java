package Articulos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class subgrupos extends JFrame {
	

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNombre;

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
		//	txtCodigo.setText(null);
			txtNombre.setText(null);
			
		}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					subgrupos frame = new subgrupos();
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
	public subgrupos() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 309);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Codigo:");
		lblNewLabel.setBounds(24, 81, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(24, 137, 60, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel SUBGRUPOS = new JLabel("SUBGRUPOS");
		SUBGRUPOS.setFont(new Font("Tahoma", Font.BOLD, 14));
		SUBGRUPOS.setBounds(211, 29, 103, 20);
		contentPane.add(SUBGRUPOS);
		
		txtCodigo = new JTextField();
		txtCodigo.setBounds(103, 78, 310, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(103, 134, 310, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO Subgrupos(nombre) values(?)");
					ps.setString(1,txtNombre.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Subgrupo Guardado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Guardar el subgrupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
				
				
				
			}
		});
		btnGuardar.setBounds(27, 193, 89, 23);
		contentPane.add(btnGuardar);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("select * from Subgrupos where nombre = ?");
					ps.setString(1,txtCodigo.getText());
					
					rs = ps.executeQuery();
					if(rs.next()) {
						txtCodigo.setText(rs.getString("codigo"));
						
						txtNombre.setText(rs.getString("nombre"));
						//txtClave.setText(null);
					}else {
						JOptionPane.showMessageDialog(null, "no existe un subgrupo con ese nombre");
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		});
		btnBuscar.setBounds(225, 193, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from Subgrupos where nombre = ?");
					ps.setString(1,txtCodigo.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"subgrupo Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al eliminar el subgrupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnEliminar.setBounds(126, 193, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("update subgrupos set nombre = ? where nombre = ?");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtCodigo.getText());
					
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Subgrupo Modificado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"Error al Modificar el Subgrupo");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
				}
			}
		});
		btnActualizar.setBounds(324, 193, 103, 23);
		contentPane.add(btnActualizar);
	}
}
