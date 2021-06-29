package Utilidades;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Model.Estado;

import java.util.Scanner;

public class Utilidades
{
	public static final String ALUMNO = "del alumno";
	public static final String APODERADO = "del apoderado";
	public static final String CURSO = "del curso";
	public static final String ASIGNATURA = "de la asignatura";
	public static final String PROFESOR = "del profesor";
	public static final String UNIDAD = "de la unidad";
	public static final String ADMINISTRADOR = "del administrador";
	
	public static boolean validarRun(String run)
	{
		boolean validacion = false;
		try
		{
			run = run.toUpperCase();
			run = run.replace(".", "");
			run = run.replace("-", "");
			int rutAux = Integer.parseInt(run.substring(0, run.length() - 1));

			char dv = run.charAt(run.length() - 1);

			int m = 0, s = 1;
			for (; rutAux != 0; rutAux /= 10)
			{
				s = (s + rutAux % 10 * (9 - m++ % 6)) % 11;
			}
			if (dv == (char) (s != 0 ? s + 47 : 75))
			{
				validacion = true;
			}

		}
		catch (java.lang.NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return validacion;
	}

	public static String formatearRun(String run)
	{
		String cuerpo, dv;

		if (!run.contains("-"))
		{
			cuerpo = run.substring(0, run.length() - 1);
			dv = run.substring(run.length() - 1).toUpperCase();

			/* Formatear RUN */
			run = cuerpo + '-' + dv;
		}

		return run;
	}

	public static boolean validarEmail(String email)
	{
		Pattern pattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email);

		if (mather.find() == true)
		{
			return true;
		}
		return false;
	}

	public static boolean esNumero(String numero)
	{
		try
		{
			Integer.parseInt(numero);
			return true;
		}
		catch (NumberFormatException nfe)
		{
			return false;
		}
	}

	public static Scanner extracted()
	{
		return new Scanner(System.in);
	}
	
	/**
	 * Pide al usuario que ingrese el email. A su vez, se valida que lo ingresado tenga el formato de email
	 * @return email
	 */
	public static String solicitarEmail(String rol)
	{
		String email;
		boolean validar;
		do
		{
			System.out.print("Ingrese el email "+ rol +": ");
			email = extracted().nextLine();
			validar = validarEmail(email);
			if(!validar)
			{
				System.out.println("Debe ingresar un email valido. Favor de ingresarlo nuevamente.\n");
			}
			
		}
		while(!validar);
		
		return email;
	}
	
	public static String solicitarNombre(String rol)
	{
		String nombre;
		System.out.print("Ingrese el nombre " + rol + ": ");
		nombre = Utilidades.extracted().nextLine();

		return nombre;
	}
	
	/**
	 * Pide al usuario que ingrese la clave
	 * @return clave
	 */
	public static String solicitarClave(String rol)
	{
		String clave;
		System.out.print("Ingrese la clave " + rol + ": ");
		clave = extracted().nextLine();
		return clave;
	}
	
	public static String solicitarRun(String rol)
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run " + rol + ", formato (XX.XXX.XXX-X): ");
			run = extracted().nextLine();
			validar = Utilidades.validarRun(run);
			if (!validar)
			{
				System.out.println("Debe ingresar un run valido. Favor de ingresar nuevamente.\n");
			}

		}
		while (!validar);

		run = run.replace(".", "");
		run = formatearRun(run);

		return run;
	}
	
	public static boolean confirmacionCambiarEstado(Estado estado, String rol)
	{
		String opcion;
		boolean validar = false;
		do
		{
			System.out.print("El estado actual es: " + estado
					+ ". ¿Desea cambiar el estado "+ rol + "?\n1. Si\n2. No\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if (!validar)
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion = "-1";
			}
			else if (Integer.parseInt(opcion) < 1 || Integer.parseInt(opcion) > 2)
			{
				System.out.println(
						"La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}

		}
		while (Integer.parseInt(opcion) < 1 || Integer.parseInt(opcion) > 2);

		if (Integer.parseInt(opcion) == 1)
		{
			return true;
		}
		return false;
	}
	
	public static boolean confirmacionModificarDatos(String rol)
	{
		String opcion;
		boolean validar = false;
		do
		{
			System.out.print("¿Desea modificar los datos "+ rol + "?\n1. Si\n2. No\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if (!validar)
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion = "-1";
			}
			else if (Integer.parseInt(opcion) < 1 || Integer.parseInt(opcion) > 2)
			{
				System.out.println(
						"La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}

		}
		while (Integer.parseInt(opcion) < 1 || Integer.parseInt(opcion) > 2);

		if (Integer.parseInt(opcion) == 1)
		{
			return true;
		}
		return false;
	}
}
