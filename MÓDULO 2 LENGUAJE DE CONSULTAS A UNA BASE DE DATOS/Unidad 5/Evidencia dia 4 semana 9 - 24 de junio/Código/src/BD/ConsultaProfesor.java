package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Model.Especialidad;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

public class ConsultaProfesor
{		
	public static Profesor buscarProfesor(String run)
	{
		Connection conexion = Conexion.conectar();
		Profesor profesor = null;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarUsuarioRunTipo(?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_tipoUsuario", TipoUsuario.PROFESOR.toString());

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				profesor = new Profesor(rs.getString("nombre"), 
										rs.getString("email"), 
										rs.getString("clave"),
										Estado.valueOf(Estado.class, rs.getString("estado")), 
										rs.getString("run"),
										TipoUsuario.PROFESOR,
										listarEspecialidadesProfesor(rs.getString("run")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return profesor;
	}
	
	public static Map<String, Profesor> validarCorreo(String run, String email, TipoUsuario tipoUsuario)
	{	
		Connection conexion = Conexion.conectar();
		Map<String, Profesor> profesores = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call validarExistenciaCuenta(?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_email", email);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());

			ResultSet rs = cs.executeQuery();
			while (rs.next())
			{
				Profesor profesor = new Profesor(	rs.getString("nombre"), 
													rs.getString("email"), 
													rs.getString("clave"),
													Estado.valueOf(Estado.class, rs.getString("estado")), 
													rs.getString("run"),
													tipoUsuario,
													listarEspecialidadesProfesor(rs.getString("run")));
				profesores.put(profesor.getRun(), profesor);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return profesores;
	}
	
	public static Map<Integer, Especialidad> listarEspecialidadesProfesor(String run)
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarEspecialidadesProfesor(?)}");
			cs.setString("in_refProfesor", run);
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();
			
			while (rs.next())
			{
				Especialidad especialidad = new Especialidad(
						rs.getInt("id"),
						rs.getString("nombre")
						);
				especialidades.put(especialidad.getId(), especialidad);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return especialidades;
	}
	
	public static Map<String, Profesor> listarProfesoresHabilitados()
	{
		Connection conexion = Conexion.conectar();
		Map<String, Profesor> profesores = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarProfesoresHabilitados()}");
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();
			
			while (rs.next())
			{
				Profesor profesor = new Profesor(
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("run"),
						listarEspecialidadesProfesor(rs.getString("run")));
				profesores.put(profesor.getRun(), profesor);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return profesores;
	}
	
	public static Map<Integer, Especialidad> listarEspecialidades()
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarEspecialidades()}");
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();
			
			while (rs.next())
			{
				Especialidad especialidad = new Especialidad(
						rs.getInt("id"),
						rs.getString("nombre")
						);
				especialidades.put(especialidad.getId(), especialidad);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return especialidades;
	}
	
	public static boolean asociarEspecialidad(String refProfesor, int refEspecialidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call asociarEspecialidadProfesor(?,?)}");
			cs.setString("in_refProfesor", refProfesor);
			cs.setInt("in_refEspecialidad", refEspecialidad);
			cs.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean desvincularEspecialidad(String refProfesor, int refEspecialidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call desvincularEspecialidadProfesor(?,?)}");
			cs.setString("in_refProfesor", refProfesor);
			cs.setInt("in_refEspecialidad", refEspecialidad);
			cs.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	
}
