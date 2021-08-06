package Conector;

import java.sql.Connection;
import java.sql.SQLException;


public class Test {

	public static void main(String[] args) {
		
		try {
			Connection con = Conexion.crearConexion();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		

	}