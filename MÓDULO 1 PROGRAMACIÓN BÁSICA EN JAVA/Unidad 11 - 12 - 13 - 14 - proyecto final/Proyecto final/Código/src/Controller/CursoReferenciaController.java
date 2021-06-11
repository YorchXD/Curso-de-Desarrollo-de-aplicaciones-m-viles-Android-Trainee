package Controller;

import java.util.HashMap;
import java.util.Map;

import BD.ConsultaCurso;
import BD.ConsultaCursoReferencia;
import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import View.CursoReferencia.ViewCursoReferencia;

public class CursoReferenciaController
{

	public static void crear()
	{
		ViewCursoReferencia.crear();
	}

	public static void ver()
	{
		ViewCursoReferencia.ver();
	}

	public static void verAlumno()
	{
		ViewCursoReferencia.verAlumnos();
	}

	public static void asociarAlumno()
	{
		int anio = ViewCursoReferencia.solicitarAnio();
		Map<Integer, Curso_Referencia> cursos_ref = ConsultaCursoReferencia.verCursoReferencia(anio);
		ViewCursoReferencia.asociarAlumno(cursos_ref);
	}

	/**
	 * Tras obtener el id del curso referencia y del alumno, procede a registrar la relacion en la BD
	 * @param id_cursoReferencia
	 * @param refAlumno
	 * @return true o false
	 */
	public static boolean registrarCursoReferenciaAlumno(int id_cursoReferencia, String refAlumno)
	{
		return ConsultaCursoReferencia.registrarCursoReferenciaAlumno(id_cursoReferencia, refAlumno);
	}

	/**
	 * Se encarga de obtener un listado de curso registrado en la BD
	 * @return cursos o null
	 */
	public static Map<Integer, Curso> ObtenerCursosRegistrados()
	{
		Map<Integer, Curso> cursos = new HashMap<>();
		cursos = ConsultaCurso.listadoCurso();
		return cursos;
	}

	/*public static Map<Integer, Profesor> ObtenerProfesoresRegistrados()
	{
		Map<Integer, Profesor> profesores = new HashMap<>();
		//profesores= ProfesorController.obtenerListadoProfesores();
		return profesores;
	}*/

	/**
	 * Tras obtener los parametros principales de un curso referencia, se procede a buscar en la BD
	 * @param letra
	 * @param refProfesor
	 * @param refCurso
	 * @param anio
	 * @return id o 0
	 */
	public static int buscarCursoReferencia(String letra, String refProfesor, int refCurso, int anio)
	{
		return ConsultaCursoReferencia.buscarCursoReferencia(letra, refProfesor, refCurso, anio);
	}

	/**
	 * Tras obtener los parametros principales de un curso referencia, se procede a registrar en la BD
	 * @param letra
	 * @param refProfesor
	 * @param refCurso
	 * @param anio
	 * @return true o false
	 */
	public static boolean registrarCurso(String letra, String refProfesor, int refCurso, int anio)
	{
		return ConsultaCursoReferencia.RegistrarReferencia(letra, refProfesor, refCurso, anio);
	}

	/**
	 * Tras obtener el anio de la promocion, se proceden a buscar todos los cursos referencia que se imparte en ese anio
	 * @param anio
	 * @return listado de cursos referencia
	 */
	public static Map<Integer, Curso_Referencia> verCursoReferencia(int anio)
	{
		return ConsultaCursoReferencia.verCursoReferencia(anio);
	}
	
	/**
	 * Tras obtener el id del curso referencia, se procede a buscar a todos los alumnos asociados a este curso
	 * @param id_cursoReferencia
	 * @return listado de alumnos asociados al curso
	 */
	public static Map<Integer, Alumno> verAlumnoAsociados(int id_cursoReferencia)
	{
		return ConsultaCursoReferencia.verCursoReferenciaAlumno(id_cursoReferencia);
	}

}
