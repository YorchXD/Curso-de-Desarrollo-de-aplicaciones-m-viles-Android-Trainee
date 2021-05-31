package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.TipoUsuario;
import model.Usuario;

public class ConsultaUsuario 
{
	public static Usuario IniciarSesion(int tipoUsuario, String email, String clave)
	{
		Connection conexion = Conexion.conectar();
		Usuario usuario = null;
		try
		{
			CallableStatement cs = conexion.prepareCall("{call iniciarSesion(?,?,?)}");
			cs.setString("in_email", email);
			cs.setString("in_clave", clave);
			cs.setString("in_tipoUsuario", TipoUsuario.values()[tipoUsuario-1].toString());
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				usuario = new Usuario(
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("clave"),
						rs.getString("estado"),
						rs.getString("run"),
						rs.getString("tipoUsuario")
						);
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return usuario;
	}

}
