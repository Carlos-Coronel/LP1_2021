package Articulos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;

public class Articulo extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTextField txtBcodigo;
	private JTextField txtPc;
	private JTextField txtPv1;
	private JTextField txtPv2;
	private JTextField txtGrupo;
	private JTextField txtSubgrupo;
	private JTextField txtMarcas;
	private JTextField txtStock;
	private JTextField txtStockm;
	private JTextField txtNombreG;
	private JTextField txtNombreS;
	private JTextField txtNombreM;
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
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return con;
		
	}
	private void limpiarcajas() {
		txtCodigo.setText(null);
		txtBcodigo.setText(null);
		txtPc.setText(null);
		txtPv1.setText(null);
		txtPv2.setText(null);
		txtGrupo.setText(null);
		txtSubgrupo.setText(null);
		txtMarcas.setText(null);
		txtStock.setText(null);
		txtStockm.setText(null);
		txtNombre.setText(null);
		txtNombreG.setText(null);
		txtNombreS.setText(null);
		txtNombreM.setText(null);
		} 
	private void nombreTabla(int id, String nombreTabla){
		Connection con = null;
		PreparedStatement p2;
		ResultSet rs1;
		try {
			con= getConnection();
			p2 = con.prepareStatement("select * from " +nombreTabla+" where codigo = "+id);
			rs1 = p2.executeQuery();
		
			if(rs1.next()) {
				if(nombreTabla=="grupos") {
					txtNombreG.setText(rs1.getString("nombre"));
				}else {
					if(nombreTabla=="subgrupos") {
						txtNombreS.setText(rs1.getString("nombre"));	
					}else{
						if(nombreTabla=="marcas"){		
							txtNombreM.setText(rs1.getString("nombre"));	
						}
					}
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public boolean idGrupos(int idgrupos, String nombreTabla){
		Connection con = null;
		boolean c=false;
		try {
			PreparedStatement p;
			con= getConnection();
			p = con.prepareStatement("select * from "+nombreTabla+" where codigo = "+idgrupos);
			rs = p.executeQuery();
		
			if(rs.next()) {
				nombreTabla(idgrupos, nombreTabla);
				c=true;
			}else {
				if(nombreTabla=="grupos") {
					txtNombreG.setText("Grupo Inexistente");
					txtGrupo.setText(null);
				}else {
					if(nombreTabla=="subgrupos") {
						txtNombreS.setText("Subgrupo Inexistente");	
						txtSubgrupo.setText(null);
					}else{
						if(nombreTabla=="marcas"){		
							txtNombreM.setText("Marca Inexistente");
							txtMarcas.setText(null);
						}
					}
				}
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return c;
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Articulo frame = new Articulo();
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
	public Articulo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Articulos");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel.setBounds(178, 11, 80, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		lblNewLabel_1.setBounds(10, 50, 80, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Codigo:");
		lblNewLabel_2.setBounds(10, 88, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Codigo de barra:");
		lblNewLabel_3.setBounds(10, 120, 96, 14);
		contentPane.add(lblNewLabel_3);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombre.setBounds(68, 47, 264, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtCodigo.setBounds(66, 85, 266, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);
		
		txtBcodigo = new JTextField();
		txtBcodigo.setHorizontalAlignment(SwingConstants.LEFT);
		txtBcodigo.setBounds(116, 117, 216, 20);
		contentPane.add(txtBcodigo);
		txtBcodigo.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Precio de compra:");
		lblNewLabel_4.setBounds(10, 164, 109, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lbl1 = new JLabel("Precio de venta 1:");
		lbl1.setVerticalAlignment(SwingConstants.BOTTOM);
		lbl1.setBounds(10, 189, 117, 14);
		contentPane.add(lbl1);
		
		JLabel lbl2 = new JLabel("Precio de venta 2:");
		lbl2.setBounds(10, 214, 109, 14);
		contentPane.add(lbl2);
		
		txtPc = new JTextField();
		txtPc.setBounds(126, 161, 153, 20);
		contentPane.add(txtPc);
		txtPc.setColumns(10);
		
		txtPv1 = new JTextField();
		txtPv1.setBounds(126, 186, 153, 20);
		contentPane.add(txtPv1);
		txtPv1.setColumns(10);
		
		txtPv2 = new JTextField();
		txtPv2.setBounds(126, 214, 153, 20);
		contentPane.add(txtPv2);
		txtPv2.setColumns(10);
		
		JButton btnNewButton = new JButton("Modificar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("update articulos set cbarra=?,preciocosto = ?,precioventa1 = ?,precioventa2 = ?,stock = ?,stockm =?,idmarca=?,idgrupo=?,idsubgrupo=? where nombre = ?");
					ps.setString(1,txtBcodigo.getText());
					ps.setDouble(2,Double.parseDouble(txtPc.getText()));
					ps.setDouble(3,Double.parseDouble(txtPv1.getText()));
					ps.setDouble(4,Double.parseDouble(txtPv2.getText()));
					ps.setDouble(5,Double.parseDouble(txtStock.getText()));
					ps.setDouble(6,Double.parseDouble(txtStockm.getText()));
					ps.setInt(7, Integer.parseInt(txtMarcas.getText()));
					ps.setInt(8, Integer.parseInt(txtGrupo.getText()));
					ps.setInt(9, Integer.parseInt(txtSubgrupo.getText()));
					ps.setString(10,txtNombre.getText());
					int res = 0;
					boolean g,s,m;
					g=idGrupos(Integer.parseInt(txtGrupo.getText()), "grupos");
					s=idGrupos(Integer.parseInt(txtSubgrupo.getText()), "subgrupos");
					m=idGrupos(Integer.parseInt(txtMarcas.getText()), "marcas");
					
					if(g==true && s==true && m==true ) {
						res = ps.executeUpdate();
					}
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Articulo Modificado");
						limpiarcajas();	
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL MODIFICAR EL ARTICULO");
	
					}
					
					con.close();
					
					}catch (Exception e ) {
					System.err.println(e);
					}
				
			}
		});
		btnNewButton.setBounds(209, 251, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				
				try {
					con= getConnection();
					ps = con.prepareStatement("select * from articulos where nombre = ?");
					ps.setString(1,txtNombre.getText());
					rs = ps.executeQuery();
					if(rs.next()) {
						txtCodigo.setText(rs.getString("codigo"));
						txtBcodigo.setText(rs.getString("cbarra"));
						txtPc.setText(rs.getString("preciocosto"));
						txtPv1.setText(rs.getString("precioventa1"));
						txtPv2.setText(rs.getString("precioventa2"));
						txtStock.setText(rs.getString("stock"));
						txtStockm.setText(rs.getString("stockm"));
						txtGrupo.setText(rs.getString("idgrupo"));
						txtSubgrupo.setText(rs.getString("idsubgrupo"));
						txtMarcas.setText(rs.getString("idmarca"));
						nombreTabla(Integer.parseInt(rs.getString("idgrupo")), "grupos");
						nombreTabla(Integer.parseInt(rs.getString("idsubgrupo")), "subgrupos");
						nombreTabla(Integer.parseInt(rs.getString("idmarca")), "marcas");
					}else {
						JOptionPane.showMessageDialog(null, "no existe ningun articulo con ese nombre");
					}
					
					
					
				} catch (Exception e) {
					System.out.println(e);
				}
				
			}
		});
		btnBuscar.setBounds(308, 251, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null;
				try {
					con= getConnection();
					ps = con.prepareStatement("delete from articulos where nombre = ?");
					ps.setString(1,txtNombre.getText());
					int res = ps.executeUpdate();
					
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Articulo Eliminado");
						limpiarcajas();
						
						
						
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL ELIMINAR EL ARTICULO");
						limpiarcajas();
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnEliminar.setBounds(110, 251, 89, 23);
		contentPane.add(btnEliminar);
		
		JButton btnCargar = new JButton("Cargar");
		btnCargar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection con = null ;
				try {
					con = getConnection();
					ps = con.prepareStatement("INSERT INTO articulos(nombre,cbarra,preciocosto,precioventa1,precioventa2,stock,stockm,idmarca,idgrupo,idsubgrupo) values(?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1,txtNombre.getText());
					ps.setString(2,txtBcodigo.getText());
					ps.setDouble(3,Double.parseDouble(txtPc.getText()));
					ps.setDouble(4,Double.parseDouble(txtPv1.getText()));
					ps.setDouble(5,Double.parseDouble(txtPv2.getText()));
					ps.setDouble(6,Double.parseDouble(txtStock.getText()));
					ps.setDouble(7,Double.parseDouble(txtStockm.getText()));
					ps.setInt(8, Integer.parseInt(txtMarcas.getText()));
					ps.setInt(9, Integer.parseInt(txtGrupo.getText()));
					ps.setInt(10, Integer.parseInt(txtSubgrupo.getText()));
					int res = 0;
					boolean g,s,m;
					g=idGrupos(Integer.parseInt(txtGrupo.getText()), "grupos");
					s=idGrupos(Integer.parseInt(txtSubgrupo.getText()), "subgrupos");
					m=idGrupos(Integer.parseInt(txtMarcas.getText()), "marcas");
					
					if(g==true && s==true && m==true ) {
						res = ps.executeUpdate();
					}
					if (res > 0) {
						JOptionPane.showMessageDialog(null,"Articulo Cargado");
						limpiarcajas();
							
					}else {
						JOptionPane.showMessageDialog(null,"ERROR AL CARGAR EL ARTICULO");
					}
					
					con.close();
					
				}catch (Exception e ) {
					System.err.println(e);
			}
			}
		});
		btnCargar.setBounds(10, 251, 89, 23);
		contentPane.add(btnCargar);
		
		txtGrupo = new JTextField();
		txtGrupo.setBounds(444, 47, 86, 20);
		contentPane.add(txtGrupo);
		txtGrupo.setColumns(10);
		
		txtSubgrupo = new JTextField();
		txtSubgrupo.setBounds(444, 85, 86, 20);
		contentPane.add(txtSubgrupo);
		txtSubgrupo.setColumns(10);
		
		txtMarcas = new JTextField();
		txtMarcas.setBounds(444, 117, 86, 20);
		contentPane.add(txtMarcas);
		txtMarcas.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("IPGrupo:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_5.setBounds(376, 50, 58, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("IPSubgrupo:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(360, 88, 74, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("IPMarca:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_7.setBounds(376, 120, 59, 14);
		contentPane.add(lblNewLabel_7);
		
		txtStock = new JTextField();
		txtStock.setBounds(396, 183, 86, 20);
		contentPane.add(txtStock);
		txtStock.setColumns(10);
		
		txtStockm = new JTextField();
		txtStockm.setBounds(396, 211, 86, 20);
		contentPane.add(txtStockm);
		txtStockm.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Stock:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_8.setBounds(349, 186, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Stock Minimo:");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_9.setBounds(308, 214, 89, 14);
		contentPane.add(lblNewLabel_9);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarcajas();
			}
		});
		btnNewButton_1.setBounds(406, 251, 89, 23);
		contentPane.add(btnNewButton_1);
		
		txtNombreG = new JTextField();
		txtNombreG.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreG.setEditable(false);
		txtNombreG.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombreG.setBackground(SystemColor.control);
		txtNombreG.setBounds(540, 47, 218, 20);
		contentPane.add(txtNombreG);
		txtNombreG.setColumns(10);
		
		txtNombreS = new JTextField();
		txtNombreS.setHorizontalAlignment(SwingConstants.LEFT);
		txtNombreS.setEditable(false);
		txtNombreS.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombreS.setBackground(SystemColor.control);
		txtNombreS.setBounds(540, 85, 218, 20);
		contentPane.add(txtNombreS);
		txtNombreS.setColumns(10);
		
		txtNombreM = new JTextField();
		txtNombreM.setEditable(false);
		txtNombreM.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtNombreM.setBackground(SystemColor.control);
		txtNombreM.setBounds(540, 117, 218, 20);
		contentPane.add(txtNombreM);
		txtNombreM.setColumns(10);
	}
}
