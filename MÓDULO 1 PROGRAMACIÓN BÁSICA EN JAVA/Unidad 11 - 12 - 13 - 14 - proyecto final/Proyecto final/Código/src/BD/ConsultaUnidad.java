package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Asignatura;
import Model.Estado;
import Model.Unidad;

public class ConsultaUnidad
{

	public static boolean registrarUnidad(String nombre, int numero_unidad, int div_anual, int refAsignatura)
	{
		Connection conexion = Conexion.conectar();
		try
		{			
			CallableStatement cs = conexion.prepareCall("{call registrarUnidad(?,?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setInt("in_numero_unidad", numero_unidad);
			cs.setInt("in_division_anual",div_anual);
			cs.setInt("in_refAsignatura", refAsignatura);
			cs.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	public static boolean listadoUnidades(Asignatura asignatura)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			asignatura.getUnidades().clear();
			CallableStatement cs = conexion.prepareCall("{call listarUnidades(?)}");	
			cs.setInt("in_refCursoAsignatura", asignatura.getId());
			ResultSet rs = cs.executeQuery();
			while(rs.next())
			{	
				Unidad unidad = new Unidad(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getInt("numero"),
						rs.getInt("divisionAnual"),
						asignatura,
						Estado.valueOf(Estado.class, rs.getString("estado"))
						);
				asignatura.getUnidades().put(unidad.getId(), unidad);
			}
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean actualizarDatosUnidad(String nombre, int numero_unidad, int division_anual, int id)
	{
		Connection conexion = Conexion.conectar();
		try
		{			
			CallableStatement cs = conexion.prepareCall("{call actualizarUnidad(?,?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setInt("in_numero_unidad", numero_unidad);
			cs.setInt("in_division_anual", division_anual);
			cs.setInt("in_id", id);
			cs.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean actualizarEstadoUnidad( int id, Estado estado)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoUnidad(?,?)}");
			cs.setInt("in_id", id);
			cs.setString("in_estado", estado.toString());
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
