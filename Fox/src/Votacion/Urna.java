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

public class Urna extends JFrame {

	private JPanel contentPane;
	private JTextField txtLista;
	private int cont = 2;
	/**
	 * Launch the application.
	 */
	

	public static final String URL = "jdbc:postgresql://localhost:5432/FOXJAVA";
	public static final String USERNAME = "postgres";
	public static final String PASSWORD = "97620040";
	
	PreparedStatement ps;
	ResultSet rs ;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JTextField txtNombre;
	private JTextField txtCargo;
	private JTextField txtPartido;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	
	
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
	public int result(String dato){
		PreparedStatement ps2;
		ResultSet rs2 ;
		Connection con = null;
		try {
			con= getConnection();
			ps2 = con.prepareStatement("select sum(voto) from votos where lista= '"+dato+"'");
			rs2 = ps2.executeQuery();
			if(rs2.next()){
					return Integer.parseInt(rs2.getString(1));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}
	public void sumaVotos() {
		Connection con = null;
		PreparedStatement ps4;
		try {
			con= getConnection();
			ps4 = con.prepareStatement("update resultados set votostotales  = ? where lista = ?");
			ps4.setInt(1,result(txtLista.getText()));
			ps4.setInt(2,Integer.parseInt(txtLista.getText()));
			
			int res = ps4.executeUpdate();
			
			if (res > 0) {
				System.out.println("Resultado actualizado");
				
			}else {
				System.out.println("Error al actualizar el resultado");
				insertar();
			}
			
			con.close();
			
		}catch (Exception e ) {
			System.err.println(e);
		}
		
	}
	public void insertar() {
		Connection con = null ;
		PreparedStatement ps3;
		try {
			con = getConnection();
			ps3 = con.prepareStatement("INSERT INTO resultados(lista,votostotales) values(?,?)");
			ps3.setInt(1,Integer.parseInt(txtLista.getText()));
			ps3.setInt(2,result(txtLista.getText()));
			int res = ps3.executeUpdate();
			
			if (res > 0) {
				System.out.println("Resultados cargados ");;

			}else {
				System.out.println("Error al cargar los resultados");
			}
			
			con.close();
			
		}catch (Exception e ) {
			System.err.println(e);
	}
	}

	public Boolean verificarDatos(String dato) {
		PreparedStatement ps1;
		ResultSet rs1 ;
		Connection con = null;
		try {
			con= getConnection();
			ps1 = con.prepareStatement("select * from candidatos where lista = '"+dato+"'");
			rs1 = ps1.executeQuery();
			if(rs1.next()){
					return true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return false;
	}
	
	private void datos(int num){
		Connection con = null;
		PreparedStatement p;
		ResultSet r;
		try {
			con= getConnection();
			p = con.prepareStatement("select * from candidatos where lista = "+num);
			r = p.executeQuery();
		
			if(r.next()) {
				txtNombre.setText(r.getString("nombre"));
				txtCargo.setText(r.getString("cargo"));		
				txtPartido.setText(r.getString("partido"));	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Urna frame = new Urna();
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
	public Urna() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 327);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Votacion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(173, 11, 84, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lista:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(26, 72, 43, 14);
		contentPane.add(lblNewLabel_1);
		
		txtLista = new JTextField();
		txtLista.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtLista.setBounds(104, 67, 257, 26);
		contentPane.add(txtLista);
		txtLista.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Votar");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cont=1;
				datos(Integer.parseInt(txtLista.getText()));
			}
		});
		btnNewButton_1.setBounds(156, 227, 106, 23);
		contentPane.add(btnNewButton_1);
		
		btnNewButton = new JButton("Salir");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(156, 261, 106, 23);
		contentPane.add(btnNewButton);
		
		btnNewButton_2 = new JButton("Voto nulo");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cont=0;
				datos(Integer.parseInt(txtLista.getText()));
			}
		});
		btnNewButton_2.setBounds(40, 227, 106, 23);
		contentPane.add(btnNewButton_2);
		
		txtNombre = new JTextField();
		txtNombre.setBackground(SystemColor.text);
		txtNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombre.setEnabled(false);
		txtNombre.setBounds(104, 109, 257, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCargo = new JTextField();
		txtCargo.setBackground(SystemColor.text);
		txtCargo.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtCargo.setEnabled(false);
		txtCargo.setBounds(104, 140, 257, 20);
		contentPane.add(txtCargo);
		txtCargo.setColumns(10);
		
		txtPartido = new JTextField();
		txtPartido.setBackground(SystemColor.text);
		txtPartido.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtPartido.setEnabled(false);
		txtPartido.setBounds(104, 171, 257, 20);
		contentPane.add(txtPartido);
		txtPartido.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Confirmar");
		btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(cont==2) {
					JOptionPane.showMessageDialog(null,"Selecione una forma de votar");
				}else {
					Connection con = null ;
					try {
						
						String lis;
						lis=String.valueOf(txtLista.getText());
						con = getConnection();
						ps = con.prepareStatement("INSERT INTO  votos(voto,lista,cargo,partido,nombre) values(?,?,?,?,?)");
						ps.setInt(1,cont);
						ps.setInt(2, Integer.parseInt(txtLista.getText()));
						ps.setString(3, txtCargo.getText());
						ps.setString(4, txtPartido.getText());
						ps.setString(5, txtNombre.getText());
						int res;
						if(verificarDatos(lis)==true){
							res = ps.executeUpdate();
						}else{
							 res=0;
							 JOptionPane.showMessageDialog(null,"La lista no existe");
						}
						
						if (res > 0) {
							JOptionPane.showMessageDialog(null,"Voto Confirmado");
							sumaVotos();
							dispose();
							
							
						}else {
							JOptionPane.showMessageDialog(null,"Error al votar");
						}
						
						con.close();
						
					}catch (Exception e ) {
						System.err.println(e);
				}
				}
				
			}
		});
		btnNewButton_3.setBounds(272, 227, 106, 23);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(26, 112, 68, 14);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Cargo:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(26, 143, 46, 14);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Partido:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_4.setBounds(26, 174, 68, 14);
		contentPane.add(lblNewLabel_4);
	}
}
