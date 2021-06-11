package Utilidades;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Utilidades
{
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
}
