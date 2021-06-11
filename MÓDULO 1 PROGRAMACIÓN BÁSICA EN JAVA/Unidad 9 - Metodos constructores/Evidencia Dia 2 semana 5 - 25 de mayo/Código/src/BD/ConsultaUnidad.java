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

	public static boolean registrarUnidad(Unidad unidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{			
			CallableStatement cs = conexion.prepareCall("{call registrarUnidad(?,?,?,?)}");
			cs.setString("in_nombre", unidad.getNombre());
			cs.setInt("in_numero_unidad", unidad.getNumero_unidad());
			cs.setInt("in_division_anual", unidad.getDivision_anual());
			cs.setInt("in_refAsignatura", unidad.getAsignatura().getId());
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

	public static boolean actualizarDatosUnidad(Unidad unidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{			
			CallableStatement cs = conexion.prepareCall("{call actualizarUnidad(?,?,?,?)}");
			cs.setString("in_nombre", unidad.getNombre());
			cs.setInt("in_numero_unidad", unidad.getNumero_unidad());
			cs.setInt("in_division_anual", unidad.getDivision_anual());
			cs.setInt("in_id", unidad.getId());
			cs.executeUpdate();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean actualizarEstadoUnidad(Unidad unidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoUnidad(?,?)}");
			cs.setInt("in_id", unidad.getId());
			cs.setString("in_estado", unidad.getEstado().toString());
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
