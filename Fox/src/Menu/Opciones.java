package Menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Articulos.Articulo;
import Articulos.Marcas;
import Articulos.subgrupos;
import Clientes.Cliente;
import GrupoJava.Grupos;
import Usuarios.AccesoUsuario;
import formularios.usuarios;

import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Opciones extends JFrame {

	private JPanel contentPane;
	private JPanel panel = new ImagenFondo();
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Opciones frame = new Opciones();
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
	public Opciones() {
		panel.setBounds(0, 11, 548, 387);
		getContentPane().add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 473);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Usuarios");
		ImageIcon  Imagen =new ImageIcon ("C:\\Users\\CarlosJein\\Downloads\\usuario2.png");
		ImageIcon img = new ImageIcon(Imagen.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
		mnNewMenu.setIcon(img);
		
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Nuevo Usuario");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarios u1 = new usuarios();
				u1.setVisible(true);
				u1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Clientes");
		ImageIcon  Imagen2 = new ImageIcon("C:\\Users\\CarlosJein\\Downloads\\clientes2.jpg");
		mnNewMenu_1.setIcon( new ImageIcon(Imagen2.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.BOLD, 12));
		mnNewMenu_1.setForeground(new Color(0, 0, 0));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Operaciones");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente c1 = new Cliente();
				c1.setVisible(true);
				c1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Productos");
		ImageIcon Imagen3 = new ImageIcon("C:\\Users\\CarlosJein\\Downloads\\art2.jpg");
		mnNewMenu_2.setIcon(new ImageIcon(Imagen3.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
		mnNewMenu_2.setForeground(new Color(0, 0, 0));
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.BOLD, 12));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Marcas");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Marcas m1 = new Marcas();
				m1.setVisible(true);
				m1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Grupos");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Grupos g1 = new Grupos();
				g1.setVisible(true);
				g1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Subgrupos");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				subgrupos s1 = new subgrupos();
				s1.setVisible(true);
				s1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Articulos");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Articulo a1 = new Articulo();
				a1.setVisible(true);
				a1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_4);
		
		getContentPane().setLayout(null);

	}
	public class ImagenFondo extends JPanel{
		@Override
		public void paint(Graphics g) {
			ImageIcon fondo = new ImageIcon("C:\\Users\\CarlosJein\\Downloads\\img3.jpg");
			g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	}
}
