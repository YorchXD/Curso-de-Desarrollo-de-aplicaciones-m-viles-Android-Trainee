package Controller;

import java.util.Map;

import BD.ConsultaAsignatura;
import View.Asignatura.ViewAsignatura;

public class AsignaturaController
{
	/*Metodos que activan la vista segun sea el caso*/
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
	/*Fin mostrar vistas*/
	
	/**
	 * Tras obtener el nombre de la asignatura, se obtiene un listado de asignatura que se encuentran registradas en la BD. Posteriormente se procede
	 * a buscar el nombre de la asignatura en el listado de asignaturas registradas.
	 * @param nombre
	 * @return asignatura o null.
	 */
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

	/**
	 * Tras obtener el nombre de la asignatura, se procede a buscar si existe en la base de datos. En caso de no encontrarlo, se registra.
	 * @param nombre
	 * @return "Asignatura registrada correctamente", "No se han registrado la asignatura, intentelo nuevamente" o "La asignatura ya se encuentra registrada"
	 */
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
	
	/**
	 * Tras obtener el id de la asignatura se procede a buscar si existe. En caso de encontrarla y el nuevo nombre es diferente al actual,
	 * se procede a actualizar la asignatura en la BD.
	 * @param nuevoNombre
	 * @param idAsignatura
	 * @return "Asignatura modificada correctamente" o "No se han registrado el curso, intentelo nuevamente"
	 */
	public static String actualizarNombreAsignatura(String nuevoNombre, int idAsignatura)
	{
		String mensaje = null;
		
		String buscarAsignatura = AsignaturaController.buscarAsignatura(nuevoNombre);
		if(buscarAsignatura==null || !nuevoNombre.equals(buscarAsignatura))
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
		else
		{
			mensaje = "\nLa asignatura ya se encuentra registrada.\n";
		}

		return mensaje;
	}
}
