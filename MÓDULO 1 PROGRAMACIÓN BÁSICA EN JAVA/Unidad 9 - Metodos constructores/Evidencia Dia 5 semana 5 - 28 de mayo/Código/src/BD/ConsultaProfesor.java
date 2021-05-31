package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;


public class ConsultaProfesor {

	public static Profesor consultarProfesor_run(String run) {

		Connection conexion = Conexion.conectar();
		Profesor profesor = null;
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarProfesor(?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_tipoUsuario", TipoUsuario.PROFESOR.toString());
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				profesor = new Profesor (
						rs.getString("nombre"),
						rs.getString("email"),
						rs.getString("clave"),
						Estado.valueOf(Estado.class, rs.getString("estado")),
						rs.getString("run"),
						TipoUsuario.valueOf(TipoUsuario.class, rs.getString("tipoUsuario")),
						rs.getString("especialidad")
						);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return profesor;
	}
	
	public static String insertarProfesor(Profesor profesor) {
		String mensaje;
		Connection conexion = Conexion.conectar();
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarProfesor(?,?,?,?,?,?)}");
			cs.setString("in_nombre", profesor.getNombre());
			cs.setString("in_run", profesor.getRun());
			cs.setString("in_tipoUsuario", profesor.getTipoUsuario().toString());
			cs.setString("in_especialidad", profesor.getEspecialidad());
			cs.setString("in_email", profesor.getEmail());
			cs.setString("in_clave", profesor.getClave());
			cs.executeQuery();
			
			mensaje = "Se ha guardado exitosamente al profesor";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo guardad al profesor";
		}
		
		return mensaje;
	}

	public static String UpdateProfesor(String nombre, String email, String clave, String especialidad, String run) {
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
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del Profesor";
		}
		
		
		return mensaje;

	}
	
	public static String UpdateProfesor_estado(Profesor profesor) {
		String mensaje;
		Connection conexion = Conexion.conectar();
		String estado_update=null;
		
		if (profesor.getEstado().toString().equals("HABILITADO")) 
		{
			estado_update=Estado.values()[1].toString();
		}
		else 
		{
			estado_update=Estado.values()[0].toString();
		}
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoProfesor(?,?)}");
			cs.setString("in_run", profesor.getRun());
			cs.setString("in_estado", estado_update);
			cs.executeQuery();
			
			mensaje = "Se ha modificado exitosamente el estado del profesor";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del profesor";
		}
		
		return mensaje;
	}
	

}
