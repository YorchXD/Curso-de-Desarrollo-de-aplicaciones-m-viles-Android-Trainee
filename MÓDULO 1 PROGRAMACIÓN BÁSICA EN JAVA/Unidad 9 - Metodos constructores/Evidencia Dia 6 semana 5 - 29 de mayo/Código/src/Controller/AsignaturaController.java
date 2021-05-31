package Controller;

import java.util.Map;

import BD.ConsultaAsignatura;
import View.Asignatura.ViewAsignatura;

public class AsignaturaController
{
	public static void crear()
	{
		ViewAsignatura.crear();
	}
	
	public static void verAsignaturasRegistradas()
	{
		ViewAsignatura.verListadoAsignatura(ConsultaAsignatura.listadoAsignaturas());
	}
	
	public static void modificar()
	{
		ViewAsignatura.modificarAsignatura(ConsultaAsignatura.listadoAsignaturas());
	}
	
	public static String buscarAsignatura(String nombre)
	{
		Map<Integer, String> asignaturas = ConsultaAsignatura.listadoAsignaturas();
		for (Map.Entry<Integer,String> asignatura : asignaturas.entrySet())
		{
			if(nombre.equalsIgnoreCase(asignatura.getValue()))
			{
				return asignatura.getValue();
			}
		}
		return null;
	}

	public static String registrarAsignatura(String nombre)
	{
		String mensaje = null;
		if(buscarAsignatura(nombre)==null)
		{
			if(ConsultaAsignatura.registrarAsignatura(nombre))
			{
				mensaje = "\nAsignatura registrada correctamente\n";
			}
			else
			{
				mensaje = "\nNo se han registrado la asignatura, intentelo nuevamente.\n";
			}
		}
		else
		{
			mensaje = "\nLa asignatura ya se encuentra registrada.\n";
		}
		
		return mensaje;
	}

	public static String actualizarNombreAsignatura(String nuevoNombre, int idAsignatura)
	{
		String mensaje = null;
		
		String buscarAsignatura = AsignaturaController.buscarAsignatura(nuevoNombre);
		if(buscarAsignatura==null)
		{
			if(ConsultaAsignatura.actualizarNombreAsignatura(nuevoNombre, idAsignatura))
			{
				mensaje = "\nAsignatura modificada correctamente\n";
			}
			else
			{
				mensaje = "\nNo se han registrado el curso, intentelo nuevamente.\n";
			}
		}
		else if(buscarAsignatura != null && !nuevoNombre.equals(buscarAsignatura))
		{
			if(ConsultaAsignatura.actualizarNombreAsignatura(nuevoNombre, idAsignatura))
			{
				mensaje = "\nAsignatura modificada correctamente\n";
			}
			else
			{
				mensaje = "\nNo se han registrado la asignatura, intentelo nuevamente.\n";
			}
		}
		else
		{
			mensaje = "\nLa asignatura ya se encuentra registrada.\n";
		}

		return mensaje;
	}
	

}
