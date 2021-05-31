package Utilidades;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Utilidades 
{
	public static void validarRun(String run)
	{
		
	}
	
	public static void formatearRun(String run)
	{
		
	}
	
	public static boolean validarEmail(String email)
	{
		Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
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
