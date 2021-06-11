package Controller;

import java.util.Map;

import BD.ConsultaCurso;
import BD.ConsultaUnidad;
import Model.Asignatura;
import Model.Curso;
import Model.Estado;
import Model.Unidad;

public class UnidadController
{
	public static void crear()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		
		View.Unidad.ViewUnidad.crear(cursos);
	}
	
	public static String registrarUnidad(Asignatura asignatura, String nombreUnidad, int divAnual, int numeroUnidad)
	{
		String mensaje = "";
		Unidad unidad = new Unidad(nombreUnidad, numeroUnidad, divAnual, asignatura);

		if(unidad.registrarDatos())
		{
			mensaje = "\nLa unidad se ha registrado correctamente.\n";
		}
		else
		{
			mensaje = "\nLa unidad no se ha registrado. Por favor, intentelo nuevamente.\n";
		}
		return mensaje;
	}
	
	
	public static Map<Integer, Curso> obtenerTodosDatosCurso()
	{
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			Curso cursoAux = curso.getValue();
			ConsultaCurso.buscarAsignaturasCurso(cursoAux);
			for(Map.Entry<Integer,Asignatura> asignatura : cursoAux.getAsignaturas().entrySet())
			{
				ConsultaUnidad.listadoUnidades(asignatura.getValue());
			}
		}
		return cursos;
	}
	
	public static void verListadoUnidades()
	{
		Map<Integer, Curso> cursos = obtenerTodosDatosCurso();
		View.Unidad.ViewUnidad.verListadoUnidades(cursos);
	}
	
	public static void modificar()
	{
		Map<Integer, Curso> cursos = obtenerTodosDatosCurso();
		View.Unidad.ViewUnidad.modificarUnidad(cursos);
	}

	
	public static void cambiarEstadoUnidad()
	{
		Map<Integer, Curso> cursos = obtenerTodosDatosCurso();
		View.Unidad.ViewUnidad.cambiarEstadoUnidad(cursos);
	}

	public static String actualizarUnidad(Unidad unidad, String nombre, int divAnual, int numeroUnidad)
	{
		String mensaje = "";
		if(!unidad.getNombre().equals(nombre) || unidad.getDivision_anual()!=divAnual || unidad.getNumero_unidad()!=numeroUnidad)
		{
			unidad.setNombre(nombre);
			unidad.setDivision_anual(divAnual);
			unidad.setNumero_unidad(numeroUnidad);
			
			if(unidad.actualizarDatos())
			{
				mensaje = "\nLa unidad se ha modificado correctamente.\n";
			}
			else
			{
				mensaje = "\nLa unidad no se ha modificado. Por favor, intentelo nuevamente.\n";
			}
		}
		else
		{
			mensaje = "\nLa unidad no se ha modificado dado a que no existen cambios.\n";
		}
		return mensaje;
	}

	public static String actualizarEstadoUnidad(Unidad unidad)
	{
		String mensaje = null;
		if(unidad.getEstado().equals(Estado.HABILITADO))
		{
			unidad.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			unidad.setEstado(Estado.HABILITADO);
		}
		
		if(unidad.cambiarEstado())
		{
			mensaje = "\nLa unidad se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
	}
}
