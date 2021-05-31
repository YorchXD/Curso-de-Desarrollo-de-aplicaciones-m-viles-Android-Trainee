package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Estado;
import model.TipoUsuario;
import model.Usuario;

public class ConsultaUsuario 
{
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
			System.out.println("email :" + email);
			System.out.println("clave :" + clave);
			System.out.println("tipo_usuario:" + tipoUsuario.toString());
			
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

}
