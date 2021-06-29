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
import Model.LetraCurso;
import Model.Nivel;
import Model.Profesor;
import Model.Tipo_Division_Anual;

public class ConsultaCursoReferencia
{
	public static Map<Integer, Curso> listadoCurso()
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, Curso> cursos = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarCursos()}");
			ResultSet rs = cs.executeQuery();
			while (rs.next())
			{
				Curso curso = new Curso(rs.getInt("id"), Nivel.valueOf(Nivel.class, rs.getString("nivel")),
						Tipo_Division_Anual.valueOf(Tipo_Division_Anual.class, rs.getString("tipoDivisionAnual")),
						Estado.valueOf(Estado.class, rs.getString("estado")));
				cursos.put(curso.getId(), curso);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return cursos;
	}

	public static int buscarCursoReferencia(int refLetra, String refProfesor, int refCurso, int anio)
	{
		Connection conexion = Conexion.conectar();
		int id = 0;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call buscarCursoReferencia(?,?,?,?)}");
			cs.setInt("in_refLetra", refLetra);
			cs.setInt("in_anio", anio);
			cs.setInt("in_refCurso", refCurso);
			cs.setString("in_refProfesorEncargado", refProfesor);

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				id = rs.getInt("existe");

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return id;
	}

	public static Boolean registrarCursoReferencia(int refLetra, String refProfesor, int refCurso, int anio)
	{
		Connection conexion = Conexion.conectar();
		boolean veficicacion = false;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarCursoReferencia(?,?,?,?)}");
			cs.setInt("in_refLetra", refLetra);
			cs.setInt("in_anio", anio);
			cs.setInt("in_refCurso", refCurso);
			cs.setString("in_refProfesorEncargado", refProfesor);
			cs.executeQuery();
			veficicacion = true;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return veficicacion;
	}

	public static Map<Integer, Curso_Referencia> verCursoReferencia(int anio)
	{

		Connection conexion = Conexion.conectar();
		Curso_Referencia curso_referencia = null;
		Curso curso = null;
		Profesor profesor = null;
		LetraCurso letra = null;
		Map<Integer, Curso_Referencia> cursos_referencia = new HashMap<>();

		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarCursosReferencia_anio (?)}");
			cs.setInt("in_anio", anio);
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				profesor = new Profesor(rs.getString("nombre"), 
										rs.getString("email"), 
										Estado.valueOf(rs.getString("estado")),
										rs.getString("run"),
										ConsultaProfesor.listarEspecialidadesProfesor(rs.getString("run")));

				curso = new Curso(	rs.getInt("idCurso"), 
									Nivel.valueOf(rs.getString("nivel")),
									Tipo_Division_Anual.valueOf(rs.getString("tipoDivisionAnual")),
									Estado.valueOf(rs.getString("estado")));
				
				letra = new LetraCurso(	rs.getInt("idLetra"),
									rs.getString("letra"));

				curso_referencia = new Curso_Referencia(rs.getInt("id"), 
														letra, 
														profesor,
														curso, 
														rs.getInt("anio"));
				cursos_referencia.put(curso_referencia.getId(), curso_referencia);

			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return cursos_referencia;
	}

	public static boolean registrarCursoReferenciaAlumno(int id_cursoReferencia, String refAlumno)
	{
		Connection conexion = Conexion.conectar();
		boolean veficicacion = false;

		try
		{
			CallableStatement cs = conexion.prepareCall("{call registrarCursoReferenciaAlumno(?,?)}");
			cs.setInt("in_refCursoReferencia", id_cursoReferencia);
			cs.setString("in_refAlumno", refAlumno);
			cs.executeQuery();
			veficicacion = true;

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return veficicacion;

	}

	public static Map<Integer, Alumno> verCursoReferenciaAlumno(int id_cursoReferencia)
	{

		Connection conexion = Conexion.conectar();
		Map<Integer, Alumno> alumnos = new HashMap<>();
		int contador = 0;
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarAlumnosCurso(?)}");
			cs.setInt("in_idCursoReferencia", id_cursoReferencia);
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{

				Alumno alumno = new Alumno(rs.getString("nombre"), rs.getString("run"));
				contador += 1;

				alumnos.put(contador, alumno);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return alumnos;

	}
	
	public static Map<Integer, LetraCurso> listarLetraCurso()
	{
		Connection conexion = Conexion.conectar();
		Map<Integer, LetraCurso> letras = new HashMap<>();
		try
		{
			CallableStatement cs = conexion.prepareCall("{call listarLetraCurso()}");
			cs.executeQuery();

			ResultSet rs = cs.executeQuery();

			while (rs.next())
			{
				LetraCurso letra = new LetraCurso(
						rs.getInt("id"),
						rs.getString("letra")
						);
				letras.put(letra.getId(), letra);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return letras;
	}
	
	
	
	
}
