package Conector;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

	public class Conexion {
		static final String URL = "jdbc:postgresql://localhost:5432/FOXJAVA";
		static final String USER = "postgres";
		static final String PASS = "97620040";
		 
		public static Connection crearConexion() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conexion = DriverManager.getConnection(URL, USER, PASS);
		if (conexion != null) {
			System.out.print("Conectando con la Base de datos.....Conexion establecida...");
			return conexion;
			}
			System.out.print("Conectando con la Base de datos.....error de conexion...");
			return null;
			}
			
     	}
