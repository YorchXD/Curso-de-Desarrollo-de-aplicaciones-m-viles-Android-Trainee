package Controller;

import java.util.Map;

import BD.ConsultaAsignatura;
import BD.ConsultaCurso;
import Model.Asignatura;
import Model.Curso;
import Model.Estado;
import Model.Nivel;
import Model.Tipo_Division_Anual;
import View.Curso.ViewCurso;

public class CursoController
{
	/*Metodos que activan la vista segun sea el caso*/
	public static void crear()
	{
		ViewCurso.crear();
	}

	public static void ver()
	{
		ViewCurso.ver();
	}
	
	public static void verCursosRegistrados()
	{
		ViewCurso.verListadoCurso(ConsultaCurso.listadoCurso());
	}

	public static void modificar()
	{
		ViewCurso.modificar(ConsultaCurso.listadoCurso());
	}
	
	public static void asociarAsignatura()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		Map<Integer, String> asignaturas = ConsultaAsignatura.listadoAsignaturas();
		ViewCurso.asociarAsignatura(cursos, asignaturas);
	}
	
	public static void cambiarEstadoAsignatura()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		
		ViewCurso.cambiarEstadoAsignatura(cursos);
	}
	
	public static void verListadoAsignaturasCurso()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		ViewCurso.verListadoAsignaturasCurso(cursos);
	}
	
	/*Fin mostrar vistas*/
	
	/**
	 * Tras obtener el nivel del curso (Primero, segundo, tercero,...) y el tipo de division anual (trimestral, semestral o anual) se procede a consulta
	 * si existe un registro de un curso con estos parametros.
	 * @param nivel
	 * @param tipoDivisionAnual
	 * @return curso o null
	 */
	public static Curso buscarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		return ConsultaCurso.buscarCurso(nivel, tipoDivisionAnual);
	}
	
	/**
	 * Tras obtener el nivel del curso (Primero, segundo, tercero,...) y el tipo de division anual (trimestral, semestral o anual) se valida que el curso no
	 * no se encuentre registrado. Si ocurre lo mencionado con anteriorirdad, procede a registrar el curso en la base de datos.
	 * @param nivel
	 * @param tipoDivisionAnual
	 * @return "Curso registrado correctamente", "No se han guardado cambios, intentelo nuevamente" o "El curso ya existe"
	 */
	public static String registrarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		String mensaje = "";
		if (buscarCurso(nivel, tipoDivisionAnual) == null)
		{
			if (ConsultaCurso.registrarCurso(nivel, tipoDivisionAnual))
			{
				mensaje = "\nCurso registrado correctamente\n";
			}
			else
			{
				mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
			}
		}
		else
		{
			mensaje = "\nEl curso ya existe\n";
		}
		
		return mensaje;
	}
	
	/**
	 * Tras obtener la instancia del curso, se procede a setear el atributo estado. Si es "HABILITADO" cambia a "DESHABILITADO" y viceversa. Posteriormente
	 * se procede a registrar en la base de datos.
	 * @param curso
	 * @return "El curso se ha modificado correctamente" o "No se han guardado cambios, intentelo nuevamente."
	 */
	public static String actualizarEstadoCurso(Curso curso)
	{
		String mensaje = "";
		if(curso.getEstado().equals(Estado.HABILITADO))
		{
			curso.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			curso.setEstado(Estado.HABILITADO);
		}
		
		if(ConsultaCurso.actualizarEstadoCurso(curso))
		{
			mensaje = "\nEl curso se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\\nNo se han guardado cambios, intentelo nuevamente.\\n";
		}
		
		return mensaje;
	}
	
	/**
	 * Tras obtener el id del curso y de asignatura, se procede a buscar si existe una relacion entre ellos. En caso de no encontrar la existencia
	 * de la relacion entre ambas instancias, se procede a registrarla en la BD
	 * @param idCurso
	 * @param idAsignatura
	 * @return "Asociacion registrada correctamente", "No se han registrado la asociacion, intentelo nuevamente" o "La asociacion ya se encuentra registrada"
	 */
	public static String registrarAsociacionCurso(int idCurso, int idAsignatura)
	{
		String mensaje = null;
		int buscarAsociacion = ConsultaCurso.existenciaAsociacionCurso(idCurso, idAsignatura);
		if(buscarAsociacion==0)
		{
			if(ConsultaCurso.registrarAsociacionCurso(idCurso, idAsignatura))
			{
				mensaje = "\nAsociacion registrada correctamente\n";
			}
			else
			{
				mensaje = "\nNo se han registrado la asociacion, intentelo nuevamente.\n";
			}
		}
		else
		{
			mensaje = "\nLa asociacion ya se encuentra registrada.\n";
		}

		return mensaje;
	}
	
	/**
	 * Tras obtener la instancia de la asignatura, se procede a setear el atributo estado. Si es "HABILITADO" cambia a "DESHABILITADO" y viceversa. Posteriormente
	 * se procede a registrar en la BD.
	 * @param asignatura
	 * @return "La asignatura se ha modificado correctamente" o "No se han guardado cambios, intentelo nuevamente"
	 */
	public static String cambiarEstadoAsignatura(int refCurso, Asignatura asignatura)
	{
		String mensaje = null;
		if(asignatura.getEstado().equals(Estado.HABILITADO))
		{
			asignatura.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			asignatura.setEstado(Estado.HABILITADO);
		}
		
		if(ConsultaCurso.actualizarEstadoAsignatura(refCurso, asignatura))
		{
			mensaje = "\nLa asignatura se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
	}
}
