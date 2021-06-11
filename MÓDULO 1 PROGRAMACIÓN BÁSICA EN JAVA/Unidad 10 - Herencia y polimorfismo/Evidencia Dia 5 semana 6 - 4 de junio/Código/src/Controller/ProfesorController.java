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

	public static Profesor buscarProfesor(String run)
	{
		return ConsultaProfesor.buscarProfesor(run);
	}

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

	public static String actualizarProfesor(Profesor profesor, String nombre, String email, String clave,
			String especialidad)
	{
		String mensaje = "";
		boolean validarCambio = false;
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
					validarCambio = true;
				}
				else
				{
					mensaje = "\nEl profesor no puede ser registrado ya que existe otro usuario ocn el rol de profesor que tiene el mismo correo\n";
				}
			}

			if (mensaje.equals(""))
			{
				if (!profesor.getNombre().equals(nombre))
				{
					profesor.setNombre(nombre);
					validarCambio = true;
				}

				if (!profesor.getClave().equals(clave))
				{
					profesor.setClave(clave);
					validarCambio = true;
				}

				if (!profesor.getEspecialidad().equals(especialidad))
				{
					profesor.setEspecialidad(especialidad);
					validarCambio = true;
				}

				if (validarCambio)
				{
					if (profesor.actualizarDatos())
					{
						mensaje = "\nSe ha modificado los datos del profesor\n";
					}
					else
					{
						mensaje = "\nNo se han registrado los cambios del profesor dado a que ha ocurrido un problema. Por favor vuelva a intentarlo.\n";
					}
				}
				else
				{
					mensaje = "\nNo existen cambios en el profesor.\n";
				}
			}
		}
		return mensaje;
	}
}
