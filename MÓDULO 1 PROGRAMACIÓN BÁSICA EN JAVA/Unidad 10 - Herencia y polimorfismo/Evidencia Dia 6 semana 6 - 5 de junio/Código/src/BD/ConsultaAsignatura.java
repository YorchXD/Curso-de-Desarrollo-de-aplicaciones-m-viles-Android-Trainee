package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ConsultaAsignatura
{	
	public static Map<Integer, String> listadoAsignaturas()
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, String> asignaturas = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarAsignaturas()}");			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				asignaturas.put(rs.getInt("id"), rs.getString("nombre"));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return asignaturas;
	}
	
	public static boolean registrarAsignatura(String nombre)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarAsignatura(?)}");
			cs.setString("in_nombre", nombre);
			cs.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}

	public static boolean actualizarNombreAsignatura(String nuevoNombre, int idAsignatura)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarNombreAsignatura(?,?)}");
			cs.setInt("in_id", idAsignatura);
			cs.setString("in_nombre", nuevoNombre);
			cs.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
