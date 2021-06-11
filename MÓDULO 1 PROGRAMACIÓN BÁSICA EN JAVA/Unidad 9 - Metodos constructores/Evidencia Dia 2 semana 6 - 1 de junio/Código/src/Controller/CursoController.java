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
	public static Curso buscarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		return ConsultaCurso.buscarCurso(nivel, tipoDivisionAnual);
	}
	
	public static boolean registrarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		return ConsultaCurso.registrarCurso(nivel, tipoDivisionAnual);
	}
	
	public static boolean actualizarEstadoCurso(Curso curso)
	{
		if(curso.getEstado().equals(Estado.HABILITADO))
		{
			curso.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			curso.setEstado(Estado.HABILITADO);
		}
		return ConsultaCurso.actualizarEstadoCurso(curso);
	}
	
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
		ViewCurso.modificar();
	}

	public static void asociarAsignatura()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		Map<Integer, String> asignaturas = ConsultaAsignatura.listadoAsignaturas();
		ViewCurso.asociarAsignatura(cursos, asignaturas);
	}

	public static String registrarAsociacionCurso(int idCurso, int idAsignatura)
	{
		String mensaje = null;
		int buscarAsociacion = ConsultaCurso.buscarAsociacionCurso(idCurso, idAsignatura);
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
	
	public static void verListadoAsignaturasCurso()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		ViewCurso.verListadoAsignaturasCurso(cursos);
	}

	public static String cambiarEstadoAsignatura(Asignatura asignatura)
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
		
		if(ConsultaCurso.actualizarEstadoAsignatura(asignatura))
		{
			mensaje = "\nLa asignatura se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
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


}
