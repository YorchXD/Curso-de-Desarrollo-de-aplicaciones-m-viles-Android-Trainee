package Controller;

import java.util.HashMap;
import java.util.Map;

import BD.ConsultaAsignatura;
import BD.ConsultaCurso;
import BD.ConsultaCursoReferencia;
import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import Model.Profesor;
import View.Curso.ViewCurso;
import View.CursoReferencia.ViewCursoReferencia;

public class CursoReferenciaController {
	
	public static void crear()
	{
		ViewCursoReferencia.crear();
	}
	
	public static void ver() {
	
		ViewCursoReferencia.ver();
		
	}
	
	
	public static void verAlumno() {
	
		ViewCursoReferencia.verAlumnos();
		
	}

	public static void asociarAlumno() {
		
		int anio = ViewCursoReferencia.solicitarAnio();
		
		Map<Integer, Curso_Referencia> cursos_ref = ConsultaCursoReferencia.verCursoReferencia(anio);

		ViewCursoReferencia.asociarAlumno(cursos_ref);
		
	}
	
public static boolean registrarCursoReferenciaAlumno(int id_cursoReferencia, String refAlumno) {
		
		return ConsultaCursoReferencia.registrarCursoReferenciaAlumno(id_cursoReferencia,refAlumno);
		
	}

	
	

	public static Map<Integer, Curso> ObtenerCursosRegistrados()
	{
		Map<Integer, Curso> cursos = new HashMap<>(); 
		cursos= ConsultaCurso.listadoCurso();
		return cursos;
	}
	
	public static Map<Integer, Profesor> ObtenerProfesoresRegistrados()
	{
		Map<Integer, Profesor> profesores = new HashMap<>(); 
		//profesores= ProfesorController.obtenerListadoProfesores();
		return profesores;
	}
	
	public static int buscarCursoReferencia(String letra, String refProfesor, int refCurso, int anio)
	{
		return ConsultaCursoReferencia.buscarCursoReferencia(letra,refProfesor,refCurso,anio);
	}

	public static boolean registrarCurso(String letra, String refProfesor, int refCurso, int anio) {
		
		return ConsultaCursoReferencia.RegistrarReferencia(letra,refProfesor,refCurso,anio);
	}

	public static Map<Integer, Curso_Referencia> verCursoReferencia(int anio) {

		return ConsultaCursoReferencia.verCursoReferencia(anio);

	}


	public static Map<Integer, Alumno> verAlumnoAsociados(int id_cursoReferencia) {

		return ConsultaCursoReferencia.verCursoReferenciaAlumno(id_cursoReferencia);
	}


}
