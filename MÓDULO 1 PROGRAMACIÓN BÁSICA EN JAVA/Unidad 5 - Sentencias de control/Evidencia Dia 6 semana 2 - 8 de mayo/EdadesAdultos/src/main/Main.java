package main;

import java.util.Scanner;

public class Main
{
	
	public static void mostrarArreglo(int edades [])
	{
		for(int i = 0; i<edades.length; i++)
		{
			System.out.print(edades[i] + " ");
		}
	}
	
	public static int [] ordenarAscendente(int edades [])
	{
		for (int i = 0; i < edades.length; i++) 
		{
	        for (int j = 0; j < edades.length-i-1; j++) 
	        {
	            if(edades[j] > edades[j+1])
	            {
	                int tmp = edades[j+1];
	                edades[j+1] = edades[j];
	                edades[j] = tmp;
	            }
	        }
	    }
		return edades;
	}
	
	public static int [] ingresarEdades( int [] edades, int tam)
	{
		Scanner lector = new Scanner(System.in);
		int cont = 0, edad;
		while(cont<tam)
		{
			do
			{
				System.out.print("Ingrese la edad de la persona: ");
				edad = lector.nextInt();
				
				if(edad<0 || edad>150)
				{
					System.out.println("La edad de la persona no puede ser inferior a 0 o mayor a 150");
				}
			}
			while(edad<0 || edad>150);
			
			if(edad>=18)
			{
				edades[cont] = edad;
				cont++;
			}
		}
		lector.close();
		return edades;
	}
	
	public static void main(String[] args)
	{
		int tam = 10;
		int [] edades = new int[tam];
		
		/*Ingresa las edades mayores de edad al arreglo*/
		edades = ingresarEdades(edades, tam);
		
		/*Muestra el arreglo sin ordenar*/
		System.out.println("Edades sin ordenar");
		mostrarArreglo(edades);
		
		/*Ordenar arreglo*/
		edades = ordenarAscendente(edades);
		
		/*Muestra el arreglo ordenado*/
		System.out.println("\nEdades ordenadas");
		mostrarArreglo(edades);

	}

}
