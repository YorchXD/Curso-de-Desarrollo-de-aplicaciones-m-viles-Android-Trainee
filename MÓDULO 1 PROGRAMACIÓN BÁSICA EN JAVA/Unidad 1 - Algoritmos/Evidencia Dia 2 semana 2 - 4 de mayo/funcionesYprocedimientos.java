import java.util.*;
import java.io.*;


public class funcionesYprocedimientos 
{
	// Funciones 
	public static double suma(double a, double b) {
		double respuesta;
		respuesta = a+b;
		return respuesta;
	}

	public static double resta(double a, double b) {
		double respuesta;
		respuesta = a-b;
		return respuesta;
	}

	// Procedimiento
	public static void menu() 
	{
		System.out.println("Menu de opciones: ");
		System.out.println("1. Sumar");
		System.out.println("2. Restar");
		System.out.println("3. Salir");
		System.out.print("Ingrese su opcion: ");
	}

	// Funcion principal
	public static void main(String[] args) throws IOException 
	{
		BufferedReader bufEntrada = new BufferedReader(new InputStreamReader(System.in));
		double a = 0;
		double b = 0;
		double respuesta = 0;
		int tipotarea = 0;
		System.out.println("Calculadora para sumar o restar 2 numeros");
		menu();
		tipotarea = Integer.parseInt(bufEntrada.readLine());
		while (tipotarea<1 || tipotarea>3) 
		{
			System.out.println("La opcion ingresada no es valida.");
			menu();
			tipotarea = Integer.parseInt(bufEntrada.readLine());
		}

		if (tipotarea==1) 
		{
			System.out.print("Ingrese el valor de a: ");
			a = Double.parseDouble(bufEntrada.readLine());
			System.out.print("Ingrese el valor de b: ");
			b = Double.parseDouble(bufEntrada.readLine());
			respuesta = suma(a,b);
			System.out.println(a+"+"+b+"="+respuesta);
		} 
		else 
		{
			if (tipotarea==2) 
			{
				System.out.print("Ingrese el valor de a: ");
				a = Double.parseDouble(bufEntrada.readLine());
				System.out.print("Ingrese el valor de b: ");
				b = Double.parseDouble(bufEntrada.readLine());
				respuesta = resta(a,b);
				System.out.println(a+"-"+b+"="+respuesta);
			} 
			else 
			{
				System.out.println("Ha salido del programa. Adios!!!");
			}
		}
	}
}

