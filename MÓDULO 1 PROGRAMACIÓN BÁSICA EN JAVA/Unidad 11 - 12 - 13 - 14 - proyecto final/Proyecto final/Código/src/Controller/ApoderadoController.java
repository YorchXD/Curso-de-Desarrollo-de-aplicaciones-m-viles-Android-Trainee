package Controller;

import java.util.Map;
import BD.ConsultaApoderado;
import Model.Apoderado;
import Model.Estado;
import Model.TipoUsuario;
import View.Apoderado.ViewApoderado;

public class ApoderadoController
{
	/*Metodos que activan la vista segun sea el caso*/
	public static void crear()
	{
		ViewApoderado.crear();
	}
	
	public static void ver()
	{
		ViewApoderado.ver();
	}
	
	public static void modificar()
	{
		ViewApoderado.modificar();
	}
	
	public static void cambiarEstado()
	{
		ViewApoderado.cambiarEstado();
	}
	/*Fin mostrar vistas*/
	
	/**
	 * Se busca los usuarios con sus respectivos roles que tengan en comun el RUN y se crean como Apoderado, pero solo en caso de ser apoderado se conserva su tipo, en caso contrario,
	 * el parametro tipoUsario se setea como null
	 * @param run
	 * @return null en caso de no encontrar al usuario. Apoderado con su tipo de usuario (Apoderado) en caso de serlo o apoderado con su tipo de usuario null
	 * en caso de encontrar al usuario pero que no es de tipoUsuario apoderado
	 */
	public static Apoderado buscarUsuario(String run)
	{
		Apoderado apoderado = null;
		Map<String, Apoderado> roles = ConsultaApoderado.buscarUsuarioApoderado(run);
		
		if(roles.size()!=0)
		{
			if(roles.containsKey(TipoUsuario.APODERADO.toString()))
			{
				apoderado= roles.get(TipoUsuario.APODERADO.toString());
			}
			else
			{
				if(roles.containsKey(TipoUsuario.ADMINISTRADOR.toString()))
				{
					apoderado= roles.get(TipoUsuario.ADMINISTRADOR.toString());
				}
				else
				{
					apoderado= roles.get(TipoUsuario.PROFESOR.toString());
				}
				apoderado.setTipoUsuario(null);
			}
		}
		return apoderado;
	}
	
	/**
	 * Tras obtener los parametros iniciales de apoderado, se crea una instancia de apoderado, posteriormente se procede a buscar usuarios de tipo apoderado
	 * que sean diferente al RUN ingresado y que tengan el mismo email. Si en el listado obtenido es igual a cero, se procede a registrar al apoderado y se 
	 * crea su cuenta.
	 * @param nombre
	 * @param email
	 * @param run
	 * @return "Se ha guardado exitosamente al apoderado", "No se pudo guardado al apoderado" o "El apoderado no puede ser registrado ya que existe otro usuario 
	 * con el mismo correo"
	 */
	public static String registrarApoderado(String nombre, String email, String run)
	{
		String mensaje = "";
		Apoderado apoderado = new Apoderado(nombre,email, run, run);
		Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(apoderado.getRun(), apoderado.getEmail(), apoderado.getTipoUsuario());
		if(apoderados.size()==0)
		{
			if(apoderado.registrarDatos())
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
			mensaje="\nEl apoderado no puede ser registrado ya que existe otro usuario con el mismo correo\n";
		}
		return mensaje;
	}
	
	/**
	 * Tras obtener la instancia del apoderado, se procede a buscar usuarios de tipo apoderado que sean diferente al RUN ingresado y 
	 * que tengan el mismo email. Si en el listado obtenido es igual a cero, se procede a registrar la cuenta del apoderado.
	 * @param apoderado
	 * @return "Se ha guardado exitosamente al apoderado", "No se pudo guardado al apoderado" o "El apoderado no puede ser registrado 
	 * ya que existe otro usuario con el mismo correo"
	 */
	public static String registrarCuenta(Apoderado apoderado)
	{
		String mensaje = "";
		apoderado.setTipoUsuario(TipoUsuario.APODERADO);
		Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(apoderado.getRun(), apoderado.getEmail(), apoderado.getTipoUsuario());
		if(apoderados.size()==0)
		{
			if(apoderado.registrarCuenta())
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
			mensaje="\nEl apoderado no puede ser registrado ya que existe otro usuario con el mismo correo\n";
		}
		return mensaje;
	}
	
	/**
	 * Tras obtener el run del apoderado, lo manda al metodo que se conecta con la base de datos para que obtenga los datos del apoderado.
	 * @param run
	 * @return apoderado o null
	 */
	public static Apoderado buscarApoderado(String run)
	{
		return ConsultaApoderado.buscarApoderado(run);
	}
	
	/**
	 * Tras obtener la instancia de apoderado, se procede a cambiar su estado. Si este ultimo se encuentra "HABILITADO" se cambia por "DESHABILITADO" y 
	 * viceversa. Posteriormente se procede a registrar el cambio en la BD.
	 * @param apoderado
	 * @return "El apoderado se ha modificado correctamente" o "No se han guardado cambios, intentelo nuevamente"
	 */
	public static String cambiarEstadoApoderado(Apoderado apoderado)
	{
		String mensaje = null;
		if(apoderado.getEstado().equals(Estado.HABILITADO))
		{
			apoderado.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			apoderado.setEstado(Estado.HABILITADO);
		}
		
		if(apoderado.cambiarEstado())
		{
			mensaje = "\nEl apoderado se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
	}
	
	/**
	 * Tras obtener la instancia del apoderado y los nuevos datos del usuario, se procede a verificar que existan cambios. Lo primero es verificar si 
	 * existe cambio en el email y si es el caso que cambio, se procede a verificar que no exista otro usuario del tipo apoderado que tenga el mismo email. 
	 * Si no existen usuario con el mismo email y del tipo apoderado, se setea la variable email. Posteriormente se procede a verificar si existen cambios
	 * en los demas parametros del usuario y se cambian por el nuevo dato. Finalmente, tras aplicar los cambios a la instancia, se le ordena que se registren
	 * los datos en la BD.
	 * @param apoderado
	 * @param nombre
	 * @param email
	 * @param clave
	 * @return "El apoderado no puede ser registrado ya que existe otro usuario con el rol de apoderado que tiene el mismo correo", "Se ha modificado los datos 
	 * del apoderado", "No se han registrado los cambios del apoderado dado a que ha ocurrido un problema. Por favor vuelva a intentarlo" o No existen cambios en el profesor
	 */
	public static String actualizarApoderado(Apoderado apoderado, String nombre, String email, String clave)
	{
		String mensaje = "";
		if(!apoderado.getNombre().equals(nombre) || !apoderado.getEmail().equals(email) || !apoderado.getClave().equals(clave))
		{
			if(!apoderado.getEmail().equals(email))
			{
				Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(apoderado.getRun(), email, apoderado.getTipoUsuario());
				if(apoderados.size()==0)
				{
					apoderado.setEmail(email);
				}
				else
				{
					mensaje="\nEl apoderado no puede ser registrado ya que existe otro usuario con el rol de profesor que tiene el mismo correo\n";
				}
			}
			
			if(mensaje.equals(""))
			{
				apoderado.setNombre(nombre);
				apoderado.setClave(clave);
				if(apoderado.registrarCuenta())
				{
					mensaje = "\nSe ha modificado los datos del apoderado\n";
				}
				else
				{
					mensaje="\nNo se han registrado los cambios del apoderado dado a que ha ocurrido un problema. Por favor vuelva a intentarlo.\n";
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
