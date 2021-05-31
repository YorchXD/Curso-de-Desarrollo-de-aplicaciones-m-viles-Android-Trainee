package View.Curso;

import Utilidades.Utilidades;
import controller.CursoController;
import model.Nivel;
import model.Tipo_Division_Anual;

public class ViewCurso
{
	public static void nivelMenu()
	{
		Nivel[] cursos = Nivel.values();
		int cont = 1;
		System.out.println("\n\nSeleccione el nivel del curso a ingresar: ");
		for (Nivel nivel : cursos)
		{
			String [] nivelAux = nivel.toString().split("_");
			System.out.println(cont + ". " + nivelAux[0] + " " + nivelAux[1]);
			cont++;
		}
		System.out.print("\nIngrese su opcion: ");
	}
	
	
	public static Nivel solicitarNivel()
	{
		String opcion;
		boolean validar;
		do
		{
			nivelMenu();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Nivel.values().length)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Nivel.values().length);
		
		return Nivel.getNivel(Integer.parseInt(opcion));
	}
	
	
	public static void menuTipoDivisionAnual()
	{
		Tipo_Division_Anual[] tipoDivisionAnuales = Tipo_Division_Anual.values();
		int cont = 1;
		System.out.println("\n\nSeleccione el tipo de curso a ingresar: ");
		for (Tipo_Division_Anual tipoDivAnual : tipoDivisionAnuales)
		{
			System.out.println(cont + ". " + tipoDivAnual);
			cont++;
		}
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static Tipo_Division_Anual solicitarTipoDivisionAnual()
	{
		String opcion;
		boolean validar;
		do
		{
			menuTipoDivisionAnual();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Tipo_Division_Anual.values().length)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Tipo_Division_Anual.values().length);

		return Tipo_Division_Anual.values()[Integer.parseInt(opcion)-1];
	}
	
	
	public static void crear()
	{
		Nivel nivel = ViewCurso.solicitarNivel();
		Tipo_Division_Anual tipoDivisionAnual = ViewCurso.solicitarTipoDivisionAnual();
		if(CursoController.buscarCurso(nivel, tipoDivisionAnual)==null)
		{
			if(CursoController.registrarCurso(nivel, tipoDivisionAnual))
			{
				System.out.println("\nCurso registrado correctamente\n");
			}
			else
			{
				System.out.println("\nNo se han guardado cambios, intentelo nuevamente.\n");
			}
		}
		else
		{
			System.out.println("\nEl curso ya existe\n");
		}
	}
	
	public static void modificar()
	{
		
	}
	
	public static void ver()
	{
		
	}
	
}
