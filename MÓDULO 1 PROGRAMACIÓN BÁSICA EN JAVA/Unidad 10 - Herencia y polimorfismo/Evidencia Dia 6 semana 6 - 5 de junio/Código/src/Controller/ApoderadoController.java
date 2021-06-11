package Controller;

import java.util.Map;
import BD.ConsultaApoderado;
import Model.Apoderado;
import Model.Estado;
import Model.TipoUsuario;
import Model.Usuario;
import View.Apoderado.ViewApoderado;

public class ApoderadoController
{
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
	
	public static Usuario buscarUsuario(String run)
	{
		Usuario usuario = null;
		Map<String, Usuario> roles = ConsultaApoderado.buscarUsuario(run);
		
		if(roles.size()!=0)
		{
			if(roles.containsKey(TipoUsuario.APODERADO.toString()))
			{
				usuario= roles.get(TipoUsuario.APODERADO.toString());
			}
			else
			{
				if(roles.containsKey(TipoUsuario.ADMINISTRADOR.toString()))
				{
					usuario= roles.get(TipoUsuario.ADMINISTRADOR.toString());
				}
				else
				{
					usuario= roles.get(TipoUsuario.PROFESOR.toString());
				}
				usuario.setTipoUsuario(null);
			}
		}
		return usuario;
	}
	
	public static String registrarApoderado(String nombre, String email, String run)
	{
		String mensaje = "";
		Apoderado apoderado = new Apoderado(nombre,email, run, Estado.HABILITADO, run, TipoUsuario.APODERADO);
		Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(apoderado.getRun(), apoderado.getEmail(), apoderado.getTipoUsuario());
		if(apoderados.size()==0)
		{
			boolean validar = ConsultaApoderado.registrarApoderado(apoderado);
			if(validar==true)
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

	public static String registrarCuenta(String run, String email)
	{
		String mensaje = "";
		
		Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(run, email, TipoUsuario.APODERADO);
		if(apoderados.size()==0)
		{
			boolean validar = ConsultaApoderado.registrarCuentaApoderado(run, email, TipoUsuario.APODERADO);
			if(validar==true)
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
	
	public static Apoderado buscarApoderado(String run)
	{
		return ConsultaApoderado.buscarApoderado(run);
	}
	
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
		
		if(ConsultaApoderado.cambiarEstado(apoderado))
		{
			mensaje = "\nEl apoderado se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
	}
	
	public static String actualizarApoderado(Apoderado apoderado, String nombre, String email, String clave)
	{
		String mensaje = "";
		boolean validarCambio = false;
		if(!apoderado.getNombre().equals(nombre) || !apoderado.getEmail().equals(email) || !apoderado.getClave().equals(clave))
		{
			if(!apoderado.getEmail().equals(email))
			{
				Map<String, Apoderado> apoderados = ConsultaApoderado.validarCorreo(apoderado.getRun(), email, apoderado.getTipoUsuario());
				if(apoderados.size()==0)
				{
					apoderado.setEmail(email);
					validarCambio=true;
				}
				else
				{
					mensaje="\nEl apoderado no puede ser registrado ya que existe otro usuario ocn el rol de profesor que tiene el mismo correo\n";
				}
			}
			
			if(mensaje.equals(""))
			{
				if(!apoderado.getNombre().equals(nombre))
				{
					apoderado.setNombre(nombre);
					validarCambio=true;
				}
				
				if(!apoderado.getClave().equals(clave))
				{
					apoderado.setClave(clave);
					validarCambio=true;
				}
				
				if(validarCambio)
				{
					if(ConsultaApoderado.actualizarApoderado(apoderado))
					{
						mensaje = "\nSe ha modificado los datos del apoderado\n";
					}
					else
					{
						mensaje="\nNo se han registrado los cambios del apoderado dado a que ha ocurrido un problema. Por favor vuelva a intentarlo.\n";
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
