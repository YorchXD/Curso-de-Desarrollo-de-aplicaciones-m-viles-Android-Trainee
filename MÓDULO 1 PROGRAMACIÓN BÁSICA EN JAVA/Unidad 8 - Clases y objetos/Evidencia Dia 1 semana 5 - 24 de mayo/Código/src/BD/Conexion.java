package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/acuclass";
	private static final String USUARIO = "Yorch";
	private static final String CLAVE = "M1y9B9a2sE0O1f0D4t0o8S";
	
	static 
	{
		try 
		{
			Class.forName(CONTROLADOR);
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error al cargar el controlador");
			e.printStackTrace();
		}
	}
	
	public static Connection conectar()
	{
		Connection conexion = null;
		try
		{
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
		}
		
		catch(SQLException e)
		{
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		return conexion;
	}	
}
