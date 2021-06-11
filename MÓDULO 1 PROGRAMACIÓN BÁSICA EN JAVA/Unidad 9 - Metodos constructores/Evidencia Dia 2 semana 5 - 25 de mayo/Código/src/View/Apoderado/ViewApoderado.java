package View.Apoderado;

import Controller.ApoderadoController;
import Model.Apoderado;
import Model.Estado;
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
	
	public static boolean confirmacionCambiarEstado(Estado estado)
	{
		String opcion;
		boolean validar = false;
		do
		{
			System.out.print("El estado actual es: " + estado + ". ¿Desea cambiar el estado?\n1. Si\n2. No\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>2)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}

		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>2);
		
		if(Integer.parseInt(opcion)==1)
		{
			return true;
		}
		return false;
	}

	public static void cambiarEstado()
	{
		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opcion para Habilitar o Deshabilitar un Profesor: ");
		System.out.println("--------------------------------------------------------- ");

		Apoderado apoderado = null;
		String run;
		Boolean confirmacion = false;

		run = solicitarRun();
		apoderado = ApoderadoController.buscarApoderado(run);

		if (apoderado != null)
		{
			apoderado.mostrarDatos();
			System.out.println("\n");

			confirmacion = confirmacionCambiarEstado(apoderado.getEstado());

			if (confirmacion)
			{
				System.out.println(ApoderadoController.cambiarEstadoApoderado(apoderado));
			}
		}
		else
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El apoderado no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
	}

}
