package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;
import Model.Usuario;

public class ConsultaProfesor
{
	public static boolean insertarProfesor(Profesor profesor) 
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarUsuario(?,?,?,?,?,?)}");
			cs.setString("in_nombre", profesor.getNombre());
			cs.setString("in_run", profesor.getRun());
			cs.setString("in_email", profesor.getEmail());
			cs.setString("in_clave", profesor.getClave());
			cs.setString("in_tipoUsuario", profesor.getTipoUsuario().toString());
			cs.setString("in_especialidad", profesor.getEspecialidad());
			cs.executeQuery();
			
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean registrarCuenta(String run, String email, TipoUsuario tipoUsuario)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarCuenta(?,?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_email", email);
			cs.setString("in_clave", run);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());
			cs.executeQuery();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static Map<String, Usuario> buscarUsuario(String run)
	{
		Connection conexion = Conexion.conectar();
		Map<String, Usuario> roles = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarUsuario(?)}");
			cs.setString("in_run", run);

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				Usuario usuario = new Usuario(rs.getString("nombre"), rs.getString("email"), rs.getString("clave"),
						Estado.valueOf(Estado.class, rs.getString("estado")), rs.getString("run"),
						TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")));
				roles.put(usuario.getTipoUsuario().toString(), usuario);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return roles;
	}
	
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
										TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")),
										rs.getString("especialidad"));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return profesor;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	public static String UpdateProfesor(String nombre, String email, String clave, String especialidad, String run)
	{
		String mensaje;
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarProfesor(?,?,?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_especialidad", especialidad);
			cs.setString("in_run", run);
			cs.executeQuery();

			mensaje = "Se ha modificado exitosamente el estado del Profesor";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del Profesor";
		}

		return mensaje;

	}

	public static String UpdateProfesor_estado(Profesor profesor)
	{
		String mensaje;
		Connection conexion = Conexion.conectar();
		String estado_update = null;

		if (profesor.getEstado().toString().equals("HABILITADO"))
		{
			estado_update = Estado.values()[1].toString();
		}
		else
		{
			estado_update = Estado.values()[0].toString();
		}

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoProfesor(?,?)}");
			cs.setString("in_run", profesor.getRun());
			cs.setString("in_estado", estado_update);
			cs.executeQuery();

			mensaje = "Se ha modificado exitosamente el estado del profesor";
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del profesor";
		}

		return mensaje;
	}
}
