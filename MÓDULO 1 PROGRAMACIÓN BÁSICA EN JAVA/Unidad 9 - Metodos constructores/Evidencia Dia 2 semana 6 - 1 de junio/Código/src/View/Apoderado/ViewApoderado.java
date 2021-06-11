package View.Apoderado;

import Controller.ApoderadoController;
import Model.Apoderado;
import Model.TipoUsuario;
import Model.Usuario;
import Utilidades.Utilidades;

public class ViewApoderado
{
	public static void crear()
	{
		Usuario usuario = null;
		String nombre, run, email;

		run = solicitarRun();
		usuario = ApoderadoController.buscarUsuario(run);
		if (usuario == null)
		{
			nombre = solicitarNombre();
			email = solicitarEmail();
			System.out.println(ApoderadoController.registrarApoderado(nombre, email, run));
		}
		else
		{
			if (usuario.getTipoUsuario() == TipoUsuario.APODERADO)
			{
				System.out.println("\nEl apoderado ya se encuentra registrado\n");
			}
			else
			{
				email = solicitarEmail();
				System.out.println(ApoderadoController.registrarCuenta(run, email));
			}

		}
	}

	public static String solicitarRun()
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run del apoderado, formato (XX.XXX.XXX-X): ");
			run = Utilidades.extracted().nextLine();
			validar = Utilidades.validarRun(run);
			if (!validar)
			{
				System.out.println("Debe ingresar un run valido. Favor de ingresar nuevamente.\n");
			}

		}
		while (!validar);

		run = run.replace(".", "");
		run = Utilidades.formatearRun(run);

		return run;
	}

	public static String solicitarEmail()
	{
		String email;
		boolean validar;
		do
		{
			System.out.print("Ingrese el email del apoderado: ");
			email = Utilidades.extracted().nextLine();
			validar = Utilidades.validarEmail(email);
			if (!validar)
			{
				System.out.println("Debe ingresar un email valido. Favor de ingresarlo nuevamente.\n");
			}
		}
		while (!validar);

		return email;
	}

	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre del apoderado: ");
		nombre = Utilidades.extracted().nextLine();

		return nombre;
	}

	public static void ver()
	{
		Apoderado apoderado = null;
		String run;

		run = solicitarRun();
		apoderado = ApoderadoController.buscarApoderado(run);
		if (apoderado != null)
		{
			apoderado.mostrarDatos();
			System.out.println("\n\n ");
		}
		else
		{
			System.out.println("\nEl apoderado no se encuentra registrado\n");
		}
		
	}

}
