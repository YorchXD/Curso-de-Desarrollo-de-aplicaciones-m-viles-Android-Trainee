package Controller;

import java.util.Map;

import BD.ConsultaApoderado;
import BD.ConsultaProfesor;
import View.Profesor.ViewProfesor;
import Model.Profesor;
import Model.TipoUsuario;
import Model.Usuario;


public class ProfesorController {

	
	/*Metodos que activan la vista segun sea el caso*/
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
	/*Fin mostrar vistas*/
	
	
	
	public static Usuario buscarUsuario(String run)
	{
		Usuario usuario = null;
		Map<String, Usuario> roles = ConsultaApoderado.buscarUsuario(run);
		
		if(roles.size()!=0)
		{
			if(roles.containsKey(TipoUsuario.PROFESOR.toString()))
			{
				usuario= roles.get(TipoUsuario.PROFESOR.toString());
			}
			else
			{
				if(roles.containsKey(TipoUsuario.ADMINISTRADOR.toString()))
				{
					usuario= roles.get(TipoUsuario.ADMINISTRADOR.toString());
				}
				else
				{
					usuario= roles.get(TipoUsuario.APODERADO.toString());
				}
				usuario.setTipoUsuario(null);
			}
		}
		return null;
	}
	
	public static String registrarProfesor(Profesor profesor)
	{
		String mensaje = "";
		boolean validar = ConsultaProfesor.insertarProfesor(profesor);
		if(validar==true)
		{
			mensaje = "\nSe ha guardado exitosamente al profesor\n";
		}
		else
		{
			mensaje = "\nNo se pudo guardado al profesor\n";
		}
		return mensaje;
	}
	
	public static String registrarCuenta(String run, String email)
	{
		String mensaje = "";
		boolean validar = ConsultaProfesor.registrarCuenta(run, email, TipoUsuario.PROFESOR);
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
	
	public static Profesor buscarProfesor(String run)
	{
		return ConsultaProfesor.buscarProfesor(run);
	}
	
	
	
	
	
	
	
	/*Faltan por arreglar*/
	public static String actualizarProfesor(String nombre, String email, String clave, String especialidad, String run)
	{
		return ConsultaProfesor.UpdateProfesor(nombre, email, clave,especialidad, run);
	}
	
	public static String modificarEstadoProfesor(Profesor profesor)
	{
		return ConsultaProfesor.UpdateProfesor_estado(profesor);
	}
	/*Fin falta por arreglar*/
}
