package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Model.Apoderado;
import Model.Estado;
import Model.TipoUsuario;
import Model.Usuario;

public class ConsultaApoderado
{
	public static boolean registrarApoderado(Apoderado apoderado)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarUsuario(?,?,?,?,?,?)}");
			cs.setString("in_nombre", apoderado.getNombre());
			cs.setString("in_run", apoderado.getRun());
			cs.setString("in_email", apoderado.getEmail());
			cs.setString("in_clave", apoderado.getClave());
			cs.setString("in_tipoUsuario", apoderado.getTipoUsuario().toString());
			cs.setString("in_especialidad", null);
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
			
			while(rs.next())
			{
				Usuario usuario = new Usuario(
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("clave"),
						Estado.valueOf(Estado.class, rs.getString("estado")),
						rs.getString("run"),
						TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario"))
						);
				roles.put(usuario.getTipoUsuario().toString(), usuario);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return roles;
	}

	public static boolean registrarCuentaApoderado(String run, String email, TipoUsuario tipoUsuario)
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

	public static Apoderado buscarApoderado(String run)
	{
		Connection conexion = Conexion.conectar();
		Apoderado apoderado = null;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarUsuarioRunTipo(?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_tipoUsuario", TipoUsuario.APODERADO.toString());

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				apoderado = new Apoderado(rs.getString("nombre"), 
										rs.getString("email"), 
										rs.getString("clave"),
										Estado.valueOf(Estado.class, rs.getString("estado")), 
										rs.getString("run"),
										TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")));
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return apoderado;
	}
	
	public static boolean cambiarEstado(Apoderado apoderado)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoUsuario(?,?,?)}");
			cs.setString("in_run", apoderado.getRun());
			cs.setString("in_estado", apoderado.getEstado().toString());
			cs.setString("in_tipoUsuario", apoderado.getTipoUsuario().toString());
			cs.executeQuery();

			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return false;
	}
	
	public static Map<String, Apoderado> validarCorreo(String run, String email, TipoUsuario tipoUsuario)
	{	
		Connection conexion = Conexion.conectar();
		Map<String, Apoderado> apoderados = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call validarExistenciaCuenta(?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_email", email);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());

			ResultSet rs = cs.executeQuery();
			while (rs.next())
			{
				Apoderado apoderado = new Apoderado(rs.getString("nombre"), 
													rs.getString("email"), 
													rs.getString("clave"),
													Estado.valueOf(Estado.class, rs.getString("estado")), 
													rs.getString("run"),
													TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")));
				apoderados.put(apoderado.getRun(), apoderado);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return apoderados;
	}
	
	public static boolean actualizarApoderado(Apoderado apoderado)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarUsuario(?,?,?,?,?,?)}");
			cs.setString("in_nombre", apoderado.getNombre());
			cs.setString("in_email", apoderado.getEmail());
			cs.setString("in_clave", apoderado.getClave());
			cs.setString("in_especialidad", null);
			cs.setString("in_run", apoderado.getRun());
			cs.setString("in_tipoUsuario", apoderado.getTipoUsuario().toString());
			cs.executeQuery();
			return true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
