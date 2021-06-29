package Controller;

import java.util.HashMap;
import java.util.Map;

import BD.ConsultaCurso;
import BD.ConsultaCursoReferencia;
import BD.ConsultaProfesor;
import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import Model.LetraCurso;
import Model.Profesor;
import View.CursoReferencia.ViewCursoReferencia;

public class CursoReferenciaController
{

	public static void crear()
	{
		Map<Integer, LetraCurso> letras = ConsultaCursoReferencia.listarLetraCurso();
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		Map<String, Profesor> profesores = ConsultaProfesor.listarProfesoresHabilitados();
		ViewCursoReferencia.crear(letras, cursos, profesores);
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
		ViewCursoReferencia.asociarAlumno();
	}

	/**
	 * Tras obtener el id del curso referencia y del alumno, procede a registrar la relacion en la BD
	 * @param id_cursoReferencia
	 * @param refAlumno
	 * @return true o false
	 */
	public static String registrarCursoReferenciaAlumno(Curso_Referencia cursoReferencia, String refAlumno)
	{
		String mensaje = "";
		if (ConsultaCursoReferencia.registrarCursoReferenciaAlumno(cursoReferencia.getId(), refAlumno))
		{

			mensaje = "\n Alumno registrado correctamente\n";
		}
		else
		{
			mensaje = "\n El alumno no se ha podido registrar, intentelo nuevamente\n";
		}
		return mensaje;
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


	/**
	 * Tras obtener los parametros principales de un curso referencia, se procede a verificar su existencia. En caso de no existir
	 * se registran los datos en la BD
	 * @param letra
	 * @param refProfesor
	 * @param refCurso
	 * @param anio
	 * @return "Curso promocion registrado correctamente", "No se han guardado cambios, intentelo nuevamente" o "El curso promocion ya existe"
	 */
	public static String registrarCurso(LetraCurso letra, Profesor profesorEncargado, int refCurso, int anio)
	{
		String mensaje = "";
		if (ConsultaCursoReferencia.buscarCursoReferencia(letra.getId(), profesorEncargado.getRun(), refCurso, anio) == 0)
		{
			if (ConsultaCursoReferencia.registrarCursoReferencia(letra.getId(), profesorEncargado.getRun(), refCurso, anio))
			{
				mensaje = "\nCurso promocion registrado correctamente\n";
			}
			else
			{
				mensaje ="\nNo se han guardado cambios, intentelo nuevamente\n";
			}
		}
		else
		{
			mensaje = "\nEl curso promocion ya existe\n";
		}
		return mensaje;
	}

	/**
	 * Tras obtener el anio de la promocion, se proceden a buscar todos los cursos referencia que se imparte en ese anio
	 * @param anio
	 * @return listado de cursos referencia
	 */
	public static Map<Integer, Curso_Referencia> buscarCursoReferencia(int anio)
	{
		return ConsultaCursoReferencia.verCursoReferencia(anio);
	}
	
	/**
	 * Tras obtener el id del curso referencia, se procede a buscar a todos los alumnos asociados a este curso
	 * @param id_cursoReferencia
	 * @return listado de alumnos asociados al curso
	 */
	public static Map<Integer, Alumno> verAlumnoAsociados(Curso_Referencia cursoReferencia)
	{
		return ConsultaCursoReferencia.verCursoReferenciaAlumno(cursoReferencia.getId());
	}

}
