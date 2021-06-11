package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Estado;
import Model.TipoUsuario;
import Model.Usuario;

public class ConsultaUsuario 
{
	/**
	 * Tras enviar los parametros solicitados, este los reenvia a la base de datos. La base de datos devolvera los datos del usuario en caso que lo encuentre.
	 * @param tipoUsuario
	 * @param email
	 * @param clave
	 * @return usuario con los datos en caso de encontrarlo. En caso conrtario devuelve null.
	 */
	public static Usuario IniciarSesion(TipoUsuario tipoUsuario, String email, String clave)
	{
		Connection conexion = Conexion.conectar();
		Usuario usuario = null;
		try
		{
			CallableStatement cs = conexion.prepareCall("{call iniciarSesion(?,?,?)}");
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				usuario = new Usuario(
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("clave"),
						Estado.valueOf(Estado.class, rs.getString("estado")),
						rs.getString("run"),
						TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario"))
						);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return usuario;
	}

	public static boolean registrarUsuario(String nombre, String run, String email, String clave, TipoUsuario tipoUsuario, String especialidad)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarUsuario(?,?,?,?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setString("in_run", run);
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());
			cs.setString("in_especialidad", especialidad);
			cs.executeQuery();
			return true;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean cambiarEstado(String run, Estado estado, TipoUsuario tipoUsuario)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoUsuario(?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_estado", estado.toString());
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
	
	public static boolean actualizarDatos(String nombre, String email, String clave, String especialidad, String run, TipoUsuario tipoUsuario)
	{
		Connection conexion = Conexion.conectar();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarUsuario(?,?,?,?,?,?)}");
			cs.setString("in_nombre", nombre);
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_especialidad", especialidad);
			cs.setString("in_run", run);
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
	
	public static boolean registrarCuenta(String run, String email, String clave, TipoUsuario tipoUsuario, String especialidad)
	{
		Connection conexion = Conexion.conectar();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarCuenta(?,?,?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_tipoUsuario", tipoUsuario.toString());
			cs.setString("in_especialidad", especialidad);
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
