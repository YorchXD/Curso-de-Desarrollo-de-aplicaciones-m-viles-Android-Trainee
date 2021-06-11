package Controller;

import java.util.Map;

import BD.ConsultaProfesor;
import View.Profesor.ViewProfesor;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

public class ProfesorController
{
	/* Metodos que activan la vista segun sea el caso */
	public static void crear()
	{
		ViewProfesor.crear();
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
	 * Tras obtener el run del usuario, se procede a buscar sus respectivas cuentas (tanto de profesor como de apoderado y administrador, si es que existen). En caso de encontrar la cuenta de profesor del run
	 * ingresado, se envia la instancia de profesor, en caso de encontrar una de las cuentas del usuario pero no es profesor, solo se envian sus datos pero con
	 * el parametro tipoUsuario igual a null y en caso de no encontrar al usuario, se devuelve null.
	 * @param run
	 * @return null, profesor con tipoUsuario = TipoUsuario.PROFESOR o profesor con tipoUsuario = null
	 */
	public static Profesor buscarUsuario(String run)
	{
		Profesor profesor = null;
		Map<String, Profesor> roles = ConsultaProfesor.buscarUsuarioProfesor(run);

		if (roles.size() != 0)
		{
			if (roles.containsKey(TipoUsuario.PROFESOR.toString()))
			{
				profesor = roles.get(TipoUsuario.PROFESOR.toString());
			}
			else
			{
				if (roles.containsKey(TipoUsuario.ADMINISTRADOR.toString()))
				{
					profesor = roles.get(TipoUsuario.ADMINISTRADOR.toString());
				}
				else
				{
					profesor = roles.get(TipoUsuario.APODERADO.toString());
				}
				profesor.setTipoUsuario(null);
			}
		}
		return profesor;
	}

	/**
	 * Se busca los usuarios con sus respectivos roles que tengan en comun el RUN y se crean como Profesor, pero solo en caso de ser profesor se conserva su tipo, en caso contrario,
	 * el parametro tipoUsario se setea como null
	 * @param run
	 * @return null en caso de no encontrar al usuario. Profesor con su tipo de usuario (PROFESOR) en caso de serlo o apoderado con su tipo de usuario null
	 * en caso de encontrar al usuario pero que no es de tipoUsuario apoderado
	 */
	public static String registrarProfesor(Profesor profesor)
	{
		String mensaje = "";
		Map<String, Profesor> profesores = ConsultaProfesor.validarCorreo(profesor.getRun(), profesor.getEmail(),
				profesor.getTipoUsuario());
		if (profesores.size() == 0)
		{
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
		return mensaje;
	}
	
	/**
	 * Tras tener la instancia del profesor, se procede a validar que no exista otro profesor que tenga asociado el mismo email 
	 * que el profesor a registrar la cuenta. En caso de no encontrar usuarios, se procede a registrar la cuenta del profesor.
	 * @param profesor
	 * @return "Se ha guardado exitosamente al apoderado", "No se pudo guardado al apoderado" o "El profesor no puede ser registrado 
	 * con la cuenta ya que existe otro usuario con el mismo correo"
	 */
	public static String registrarCuenta(Profesor profesor)
	{
		String mensaje = "";
		profesor.setTipoUsuario(TipoUsuario.PROFESOR);

		Map<String, Profesor> profesores = ConsultaProfesor.validarCorreo(profesor.getRun(), profesor.getEmail(),
				profesor.getTipoUsuario());
		if (profesores.size() == 0)
		{
			if (profesor.registrarCuenta())
			{
				mensaje = "\nSe ha guardado exitosamente al apoderado\n";
			}
			else
			{
				mensaje = "\nNo se pudo guardado al apoderado\n";
			}
		}
		else
		{
			mensaje = "\nEl profesor no puede ser registrado con la cuenta ya que existe otro usuario con el mismo correo\n";
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
	 * @param especialidad
	 * @return "El profesor no puede ser registrado ya que existe otro usuario con el rol de profesor que tiene el mismo correo", "Se ha modificado los datos 
	 * del apoderado", "No se han registrado los cambios del apoderado dado a que ha ocurrido un problema. Por favor vuelva a intentarlo" o No existen cambios en el profesor
	 */
	public static String actualizarProfesor(Profesor profesor, String nombre, String email, String clave,
			String especialidad)
	{
		String mensaje = "";
		if (!profesor.getNombre().equals(nombre) || !profesor.getEmail().equals(email)
				|| !profesor.getClave().equals(clave) || !profesor.getEspecialidad().equals(especialidad))
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
				profesor.setEspecialidad(especialidad);
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
