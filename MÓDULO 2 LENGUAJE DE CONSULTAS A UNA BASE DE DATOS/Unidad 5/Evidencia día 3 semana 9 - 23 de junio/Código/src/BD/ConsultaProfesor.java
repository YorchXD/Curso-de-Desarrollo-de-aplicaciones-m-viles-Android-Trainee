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

public class ConsultaProfesor
{	
	public static Map<String, Profesor> buscarUsuarioProfesor(String run)
	{
		Connection conexion = Conexion.conectar();
		Map<String, Profesor> roles = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarUsuario(?)}");
			cs.setString("in_run", run);

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				Profesor profesor = new Profesor(	rs.getString("nombre"), 
													rs.getString("email"), 
													rs.getString("clave"),
													Estado.valueOf(Estado.class, rs.getString("estado")), 
													rs.getString("run"),
													TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")),
													rs.getString("especialidad"));
				roles.put(profesor.getTipoUsuario().toString(), profesor);
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
													TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")),
													rs.getString("especialidad"));
				profesores.put(profesor.getRun(), profesor);
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return profesores;
	}
}
