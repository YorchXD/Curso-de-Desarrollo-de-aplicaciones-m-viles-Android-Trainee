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
		boolean validar = ConsultaApoderado.registrarApoderado(apoderado);
		if(validar==true)
		{
			mensaje = "\nSe ha guardado exitosamente al apoderado\n";
		}
		else
		{
			mensaje = "\nNo se pudo guardado al apoderado\n";
		}
		return mensaje;
	}

	public static String registrarCuenta(String run, String email)
	{
		String mensaje = "";
		boolean validar = ConsultaApoderado.registrarCuentaApoderado(run, email, TipoUsuario.APODERADO);
		if(validar==true)
		{
			mensaje = "\nSe ha guardado exitosamente al apoderado\n";
		}
		else
		{
			mensaje = "\nNo se pudo guardado al apoderado\n";
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

}
