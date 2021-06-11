package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Alumno;
import Model.Estado;

public class ConsultaAlumno
{

	public static Alumno consultarAlumno_run(String run)
	{
		Connection conexion = Conexion.conectar();
		Alumno alumno = null;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarAlumno(?)}");
			cs.setString("in_run", run);

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				alumno = new Alumno(rs.getString("nombre"), rs.getString("run"), rs.getInt("edad"),
						Estado.valueOf(Estado.class, rs.getString("estado")));
				
				if(rs.getString("refApoderado")!=null)
				{
					alumno.setApoderado(ConsultaApoderado.buscarApoderado(rs.getString("refApoderado")));
				}
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return alumno;
	}

	public static boolean registrarAlumno(String nombre, String run, int edad)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarAlumno(?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setString("in_run", run);
			cs.setInt("in_edad", edad);
			int resp = cs.executeUpdate();
	        if (resp > 0) 
	        {
	        	return true;
	        }		
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public static boolean cambiarEstado(String run, Estado estado)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoAlumno(?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_estado", estado.toString());
			int resp = cs.executeUpdate();
	        if (resp > 0) 
	        {
	        	return true;
	        }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}

	public static boolean actualizarDatos(String nombre, String run, int edad)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarAlumno(?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_nombre", nombre);
			cs.setInt("in_edad", edad);
			int resp = cs.executeUpdate();
	        if (resp > 0) 
	        {
	        	return true;
	        }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	public static boolean asociarAlumnoApoderado(String runAlumno, String runApoderado)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call asociarAlumnoApoderado(?,?)}");
			cs.setString("in_runAlumno", runAlumno);
			cs.setString("in_runApoderado", runApoderado);
			int resp = cs.executeUpdate();
	        if (resp > 0) 
	        {
	        	return true;
	        }
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
}
