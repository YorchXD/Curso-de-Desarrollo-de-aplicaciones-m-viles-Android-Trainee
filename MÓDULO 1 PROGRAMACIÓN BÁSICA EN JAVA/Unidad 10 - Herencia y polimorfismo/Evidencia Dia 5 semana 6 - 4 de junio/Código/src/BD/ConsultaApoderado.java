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

public class ConsultaApoderado
{
	public static Map<String, Apoderado> buscarUsuarioApoderado(String run)
	{
		Connection conexion = Conexion.conectar();
		Map<String, Apoderado> roles = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarUsuario(?)}");
			cs.setString("in_run", run);
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				Apoderado apoderado = new Apoderado(
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("clave"),
						Estado.valueOf(Estado.class, rs.getString("estado")),
						rs.getString("run"),
						TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario"))
						);
				roles.put(apoderado.getTipoUsuario().toString(), apoderado);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return roles;
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
}
