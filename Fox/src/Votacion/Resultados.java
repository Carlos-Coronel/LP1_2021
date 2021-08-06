package Votacion;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.print.DocFlavor.STRING;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Resultados extends JFrame {

	private JPanel contentPane;
	private JTextField txtLista;
	private JTextField txtTotal;

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
			System.out.print("Conexion exitosa");
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	
	public Boolean verificar(String dato) {
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resultados frame = new Resultados();
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
	public Resultados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Resultados de la votacion");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(109, 11, 228, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Lista:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_1.setBounds(25, 52, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Total de votos:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblNewLabel_2.setBounds(25, 89, 112, 14);
		contentPane.add(lblNewLabel_2);
		
		txtLista = new JTextField();
		txtLista.setBounds(81, 50, 256, 20);
		contentPane.add(txtLista);
		txtLista.setColumns(10);
		
		txtTotal = new JTextField();
		txtTotal.setEditable(false);
		txtTotal.setBounds(147, 87, 190, 20);
		contentPane.add(txtTotal);
		txtTotal.setColumns(10);
		
		JButton btnNewButton = new JButton("Salir");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton.setBounds(288, 127, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Calcular");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String dato = txtLista.getText();
				if(verificar(dato) == true) {
					Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("select sum(voto) from votos where lista= '"+dato+"'");
					rs = ps.executeQuery();
					if(rs.next()){
							txtTotal.setText(rs.getString(1)); 
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				}else{
					JOptionPane.showMessageDialog(null, "La lista no existe ");
					txtLista.setText(null);
					txtTotal.setText(null);
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		btnNewButton_1.setBounds(189, 127, 89, 23);
		contentPane.add(btnNewButton_1);
	}

}
