package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import java.sql.CallableStatement;
import java.sql.Connection;

import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import Model.Estado;
import Model.Nivel;
import Model.Profesor;
import Model.TipoUsuario;
import Model.Tipo_Division_Anual;
import Model.Usuario;

public class ConsultaCursoReferencia {

	public static Map<Integer, Curso> listadoCurso()
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, Curso> cursos = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarCursos()}");			
			ResultSet rs = cs.executeQuery();
			while(rs.next())
			{
				Curso curso = new Curso(
						rs.getInt("id"),
						Nivel.valueOf(Nivel.class, rs.getString("nivel")),
						Tipo_Division_Anual.valueOf(Tipo_Division_Anual.class, rs.getString("tipoDivisionAnual")),
						Estado.valueOf(Estado.class, rs.getString("estado"))
						);
				cursos.put(curso.getId(), curso);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return cursos;
	}
	
	public static int buscarCursoReferencia(String letra, String refProfesor, int refCurso, int anio)
	{
		Connection conexion = Conexion.conectar();
		int id=0;

		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarCursoReferencia(?,?,?,?)}");
			cs.setString("in_letra", letra);
			cs.setInt("in_anio", anio);
			cs.setInt("in_refCurso", refCurso);
			cs.setString("in_refProfesorEncargado", refProfesor);
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				id = rs.getInt("id");

			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return id;
	}
	
	public static Boolean RegistrarReferencia(String letra, String refProfesor, int refCurso, int anio)
	{
		Connection conexion = Conexion.conectar();
		boolean veficicacion =false;

		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call RegistrarCursoReferencia(?,?,?,?)}");
			cs.setString("in_letra", letra);
			cs.setInt("in_anio", anio);
			cs.setInt("in_refCurso", refCurso);
			cs.setString("in_refProfesorEncargado", refProfesor);
			cs.executeQuery();
			veficicacion=true;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return veficicacion;
	}

	public static Map<Integer, Curso_Referencia>  verCursoReferencia(int anio) {
		
		
		Connection conexion = Conexion.conectar();
		Curso_Referencia curso_referencia=null;
		Curso curso=null;
		Profesor profesor=null;
		Map<Integer, Curso_Referencia> cursos_referencia = new HashMap<>();

		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call ListarCursosReferencia_anio (?)}");
			cs.setInt("in_anio", anio);
			cs.executeQuery();
			
			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				profesor = new Profesor(rs.getString("nombre"), 
										"", 
										"",
										Estado.HABILITADO, 
										rs.getString("run"),
										TipoUsuario.PROFESOR,
										rs.getString("especialidad"));
				
				curso = new Curso(
						0,
						Nivel.valueOf(Nivel.class, rs.getString("nivel")),
						Tipo_Division_Anual.valueOf(Tipo_Division_Anual.class, rs.getString("tipoDivisionAnual")),
						Estado.valueOf(Estado.class, rs.getString("estado"))
						);
				
				curso_referencia = new Curso_Referencia(rs.getInt("id"), rs.getString("letra").charAt(0), profesor,curso,rs.getInt("año"));
				cursos_referencia.put(curso_referencia.getId(),curso_referencia);
				
			}
			

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return cursos_referencia;
	}

	public static boolean registrarCursoReferenciaAlumno(int id_cursoReferencia, String refAlumno) {

		Connection conexion = Conexion.conectar();
		boolean veficicacion =false;

		
		try
		{
			CallableStatement cs = conexion.prepareCall("{call RegistrarCursoReferenciaAlumno(?,?)}");
			cs.setInt("in_refCursoReferencia", id_cursoReferencia);
			cs.setString("in_refAlumno", refAlumno);
			cs.executeQuery();
			veficicacion=true;

		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return veficicacion;
		
	}

	public static Map<Integer, Alumno> verCursoReferenciaAlumno(int id_cursoReferencia) {
		
		Connection conexion = Conexion.conectar();
		Map<Integer, Alumno> alumnos = new HashMap<>();
		int contador=0;
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarAlumnosCurso(?)}");			
			cs.setInt("in_idCursoReferencia", id_cursoReferencia);
			cs.executeQuery();
			
			ResultSet rs = cs.executeQuery();
			
			while(rs.next())
			{
				
				
				Alumno alumno = new Alumno(
						rs.getString("nombre"),
						rs.getString("run"));
				contador+=1;

				alumnos.put(contador, alumno);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return alumnos;
	
		


	}
}
