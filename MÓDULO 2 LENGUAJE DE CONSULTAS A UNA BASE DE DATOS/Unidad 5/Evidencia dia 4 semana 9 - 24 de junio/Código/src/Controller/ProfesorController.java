package Controller;

import java.util.Map;

import BD.ConsultaProfesor;
import View.Profesor.ViewProfesor;
import Model.Especialidad;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

public class ProfesorController
{
	/* Metodos que activan la vista segun sea el caso */
	public static void crear()
	{
		ViewProfesor.crear(ConsultaProfesor.listarEspecialidades());
	}

	public static void ver()
	{
		ViewProfesor.ver();
	}

	public static void modificar()
	{
		ViewProfesor.modificar();
	}

	public static void cambiarEstado()
	{
		ViewProfesor.cambiarEstado();
	}
	/* Fin mostrar vistas */

	/**
	 * Se valida primero si el profesor se encuentra registrado. En caso de no estar registrado, se procede a validar si el correo
	 * lo tiene asociado otro usuario profesor. En caso de que no este registrado el profesor y el email no esté asociado a un profesor
	 * se procede a registrarlo.
	 * @param nombre
	 * @param run
	 * @param email
	 * @param clave
	 * @param especialidad
	 * @return "Se ha guardado exitosamente al profesor", "No se pudo guardado los datos del profesor", "El profesor no puede ser registrado
	 * con la cuenta ya que existe otro usuario con el mismo correo" o "El profesor ya se encuentra registrado"
	 */
	public static String registrarProfesor(String nombre, String run, String email, String clave, Map<Integer, Especialidad> especialidades)
	{
		String mensaje = "";
		Profesor profesorAux = ConsultaProfesor.buscarProfesor(run);
		if(profesorAux == null)
		{
			Map<String, Profesor> profesores = ConsultaProfesor.validarCorreo(run, email, TipoUsuario.PROFESOR);
			if (profesores.size() == 0)
			{
				Profesor profesor = new Profesor(nombre, email, clave, run, especialidades);
				if (profesor.registrarDatos())
				{
					mensaje = "\nSe ha guardado exitosamente al profesor\n";
				}
				else
				{
					mensaje = "\nNo se pudo guardado los datos del profesor\n";
				}
			}
			else
			{
				mensaje = "\nEl profesor no puede ser registrado con la cuenta ya que existe otro usuario con el mismo correo\n";
			}
		}
		else
		{
			mensaje = "\nEl profesor ya se encuentra registrado\n";
		}
		return mensaje;
	}

	/**
	 * Tras obtener el run del profesor, se procede a buscar en la BD.
	 * @param run
	 * @return profesor o null
	 */
	public static Profesor buscarProfesor(String run)
	{
		return ConsultaProfesor.buscarProfesor(run);
	}
	
	/**
	 * Tras tener la instancia del profesor, se procede a cambiar el estado del profesor. En caso de que el atributo estado se encuentre en "HABILITADO",
	 * se cambia a "DESHABILITADO" y viceversa.
	 * @param profesor
	 * @return "El profesor se ha modificado correctamente" o "No se han guardado cambios, intentelo nuevamente"
	 */
	public static String cambiarEstadoProfesor(Profesor profesor)
	{
		String mensaje = null;
		if (profesor.getEstado().equals(Estado.HABILITADO))
		{
			profesor.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			profesor.setEstado(Estado.HABILITADO);
		}

		if (profesor.cambiarEstado())
		{
			mensaje = "\nEl profesor se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}

		return mensaje;
	}

	/**
	 * Tras obtener la instancia del profesor y los nuevos datos del usuario, se procede a verificar que existan cambios. Lo primero es verificar si 
	 * existe cambio en el email y si es el caso que cambio, se procede a verificar que no exista otro usuario del tipo apoderado que tenga el mismo email. 
	 * Si no existen usuario con el mismo email y del tipo profesor, se setea la variable email. Posteriormente se procede a verificar si existen cambios
	 * en los demas parametros del usuario y se cambian por el nuevo dato. Finalmente, tras aplicar los cambios a la instancia, se le ordena que se registren
	 * los datos en la BD.
	 * @param profesor
	 * @param nombre
	 * @param email
	 * @param clave
	 * @return "El profesor no puede ser registrado ya que existe otro usuario con el rol de profesor que tiene el mismo correo", "Se ha modificado los datos 
	 * del apoderado", "No se han registrado los cambios del apoderado dado a que ha ocurrido un problema. Por favor vuelva a intentarlo" o No existen cambios en el profesor
	 */
	public static String actualizarProfesor(Profesor profesor, String nombre, String email, String clave)
	{
		String mensaje = "";
		if (!profesor.getNombre().equals(nombre) || !profesor.getEmail().equals(email)
				|| !profesor.getClave().equals(clave))
		{
			if (!profesor.getEmail().equals(email))
			{
				Map<String, Profesor> profesores = ConsultaProfesor.validarCorreo(profesor.getRun(), email,
						profesor.getTipoUsuario());
				if (profesores.size() == 0)
				{
					profesor.setEmail(email);
				}
				else
				{
					mensaje = "\nEl profesor no puede ser registrado ya que existe otro usuario con el rol de profesor que tiene el mismo correo\n";
				}
			}

			if (mensaje.equals(""))
			{
				profesor.setNombre(nombre);
				profesor.setClave(clave);
				if (profesor.actualizarDatos())
				{
					mensaje = "\nSe ha modificado los datos del profesor\n";
				}	
				else
				{
					mensaje = "\nNo se ha modificado los datos del profesor debido a que ocurrio un problema. Intentelo denuevo porfavor.\n";
				}
			}
		}
		else
		{
			mensaje = "\nNo existen cambios en el profesor.\n";
		}
		return mensaje;
	}
}
