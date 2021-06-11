package View.Apoderado;

import Controller.ApoderadoController;
import Model.Apoderado;
import Model.Estado;
import Model.TipoUsuario;
import Utilidades.Utilidades;

public class ViewApoderado
{
	public static void crear()
	{
		Apoderado apoderado = null;
		String nombre, run, email;

		run = solicitarRun();
		apoderado = ApoderadoController.buscarUsuario(run);
		if (apoderado == null)
		{
			nombre = solicitarNombre();
			email = solicitarEmail();
			System.out.println(ApoderadoController.registrarApoderado(nombre, email, run));
		}
		else
		{
			if (apoderado.getTipoUsuario() == TipoUsuario.APODERADO)
			{
				System.out.println("\n********************************************************");
				System.out.println("*       El apoderado ya se encuentra registrado        *");
				System.out.println("********************************************************\n");
			}
			else
			{
				apoderado.setEmail(solicitarEmail());
				System.out.println(ApoderadoController.registrarCuenta(apoderado));
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
	
	public static String solicitarClave()
	{
		String clave;
		System.out.print("Ingrese la clave del apoderado: ");
		clave = Utilidades.extracted().nextLine();
		return clave;
	}

	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre del apoderado: ");
		nombre = Utilidades.extracted().nextLine();
		return nombre;
	}
	
	public static boolean solicitarRespuesta()
	{
		String mensaje = "NO";
		boolean respuesta = false;
		do
		{
			System.out.print("¿Desea modificar al apoderado? (SI o NO): ");
			mensaje = Utilidades.extracted().nextLine().toUpperCase();
		}
		while (!mensaje.equals("SI") && !mensaje.equals("NO"));

		if (mensaje.equals("SI"))
		{
			respuesta = true;
		}
		
		return respuesta;
	}

	public static void ver()
	{
		Apoderado apoderado = null;
		String run;

		run = solicitarRun();
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
		System.out.println("\n********************************************************");
		System.out.println("*         Habilitar o Deshabilitar un Apoderado        *");
		System.out.println("********************************************************\n");

		Apoderado apoderado = null;
		String run;
		Boolean confirmacion = false;

		run = solicitarRun();
		apoderado = ApoderadoController.buscarApoderado(run);

		if (apoderado != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                  Datos del apoderado                 *");
			System.out.println("********************************************************\n");
			System.out.println(apoderado.mostrarDatos());
			System.out.println("********************************************************\n");
			confirmacion = confirmacionCambiarEstado(apoderado.getEstado());

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
		Apoderado apoderado = null;
		String nombre, run, email, clave;
		Boolean confirmacion = false;

		run = solicitarRun();
		apoderado = ApoderadoController.buscarApoderado(run);

		if (apoderado != null)
		{
			nombre = solicitarNombre();
			email = solicitarEmail();
			clave = solicitarClave();
			confirmacion = solicitarRespuesta();

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
