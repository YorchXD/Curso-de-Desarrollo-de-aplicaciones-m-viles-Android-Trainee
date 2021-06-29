package View.Apoderado;

import Controller.ApoderadoController;
import Model.Apoderado;

import Utilidades.Utilidades;

public class ViewApoderado
{
	public static void crear()
	{
		String run = Utilidades.solicitarRun(Utilidades.APODERADO);
		String nombre = Utilidades.solicitarNombre(Utilidades.APODERADO);
		String email = Utilidades.solicitarEmail(Utilidades.APODERADO);
		String clave = Utilidades.solicitarClave(Utilidades.APODERADO);
		System.out.println("\n********************************************************");
		System.out.println(ApoderadoController.registrarApoderado(nombre, email, run, clave));
		System.out.println("********************************************************\n");
	}

	public static void ver()
	{
		Apoderado apoderado = null;
		String run = Utilidades.solicitarRun(Utilidades.APODERADO);
		apoderado = ApoderadoController.buscarApoderado(run);
		if (apoderado != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                  Datos del apoderado                 *");
			System.out.println("********************************************************\n");
			System.out.println(apoderado.mostrarDatos());
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El apoderado no se encuentra registrado        *");
			System.out.println("********************************************************\n");
		}
	}
	

	public static void cambiarEstado()
	{
		System.out.println("\n********************************************************");
		System.out.println("*         Habilitar o Deshabilitar un Apoderado        *");
		System.out.println("********************************************************\n");

		String run = Utilidades.solicitarRun(Utilidades.APODERADO);
		Apoderado apoderado = ApoderadoController.buscarApoderado(run);

		if (apoderado != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                  Datos del apoderado                 *");
			System.out.println("********************************************************\n");
			System.out.println(apoderado.mostrarDatos());
			System.out.println("********************************************************\n");
			Boolean confirmacion = Utilidades.confirmacionCambiarEstado(apoderado.getEstado(), Utilidades.APODERADO);

			if (confirmacion)
			{
				System.out.println(ApoderadoController.cambiarEstadoApoderado(apoderado));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El apoderado no se encuentra registrado        *");
			System.out.println("********************************************************\n");
		}
	}
	
	public static void modificar()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                   Modificar apoderado                *");
		System.out.println("********************************************************\n");
		
		String run = Utilidades.solicitarRun(Utilidades.APODERADO);
		Apoderado apoderado = ApoderadoController.buscarApoderado(run);

		if (apoderado != null)
		{
			String nombre = Utilidades.solicitarNombre(Utilidades.APODERADO);
			String email = Utilidades.solicitarEmail(Utilidades.APODERADO);
			String clave = Utilidades.solicitarClave(Utilidades.APODERADO);
			Boolean confirmacion = Utilidades.confirmacionModificarDatos(Utilidades.APODERADO);

			if (confirmacion)
			{
				System.out.println(ApoderadoController.actualizarApoderado(apoderado, nombre, email, clave));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El apoderado no se encuentra registrado        *");
			System.out.println("********************************************************\n");
		}
	}

}
