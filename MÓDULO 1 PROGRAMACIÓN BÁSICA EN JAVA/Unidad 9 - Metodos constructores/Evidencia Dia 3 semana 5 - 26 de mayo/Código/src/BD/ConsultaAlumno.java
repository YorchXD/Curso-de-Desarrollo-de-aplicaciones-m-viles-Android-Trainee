package BD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Alumno;
import model.Estado;


public class ConsultaAlumno {

	public static Alumno consultarAlumno_run(String run) {
		Connection conexion = Conexion.conectar();
		Alumno alumno = null;
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarAlumno(?)}");
			cs.setString("in_run", run);
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				alumno = new Alumno (
						rs.getString("nombre"),
						rs.getString("run"),
						rs.getInt("edad"),
						Estado.valueOf(Estado.class, rs.getString("estado"))     
						);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return alumno;
	}
	public static String insertarAlumno(Alumno alumno) {
		String mensaje;
		Connection conexion = Conexion.conectar();
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarAlumno(?,?,?)}");
			cs.setString("in_nombre", alumno.getNombre());
			cs.setString("in_run", alumno.getRun());
			cs.setInt("in_edad", alumno.getEdad());
			cs.executeQuery();
			
			mensaje = "Se ha guardado exitosamente al alumno";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo guardad al alumno";
		}
		
		return mensaje;
	}
	
	public static String UpdateAlumno_estado(Alumno alumno) {
		String mensaje;
		Connection conexion = Conexion.conectar();
		String estado_update=null;
		
		if (alumno.getEstado().toString().equals("HABILITADO")) 
		{
			estado_update=Estado.values()[1].toString();
		}
		else 
		{
			estado_update=Estado.values()[0].toString();
		}
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarEstadoAlumno(?,?)}");
			cs.setString("in_run", alumno.getRun());
			cs.setString("in_estado", estado_update);
			cs.executeQuery();
			
			mensaje = "Se ha modificado exitosamente el estado del alumno";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del alumno";
		}
		
		return mensaje;
	}
	
	public static String UpdateAlumno(String nombre, String run, int edad) {
		String mensaje;
		Connection conexion = Conexion.conectar();
		
		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call actualizarAlumno(?,?,?)}");
			cs.setString("in_run", run);
			cs.setString("in_nombre", nombre);
			cs.setInt("in_edad", edad);
			cs.executeQuery();
			
			mensaje = "Se ha modificado exitosamente el estado del alumno";
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			mensaje = "No se pudo modificar el estado del alumno";
		}
		
		
		return mensaje;
	}
	
	
	
	
}
