import java.util.Scanner;

public class Main
{
	public static Scanner lector = new Scanner(System.in);
	public static int ingresarNumero(int opcion)
	{
		
		int respuesta;
		switch(opcion)
		{
			case 1:
				do
				{
					System.out.print("Ingrese un numero distinto de cero: ");
					respuesta = lector.nextInt();
					if(respuesta==0)
					{
						System.out.println("El numero debe ser distinto de cero.");
					}
				}
				while(respuesta==0);
				break;
			case 2:
				do
				{
					System.out.print("Ingrese un numero mayor a 2: ");
					respuesta = lector.nextInt();
					if(respuesta<=2)
					{
						System.out.println("El numero debe ser mayor a 2.");
					}
				}
				while(respuesta<=0);
				break;
			default:
				do
				{
					System.out.print("Ingrese un numero mayor a 2: ");
					respuesta = lector.nextInt();
					if(respuesta<=2)
					{
						System.out.println("El numero debe ser mayor a 2.");
					}
				}
				while(respuesta<=0);
				break;
		}
		return respuesta;
	}
	
	public static void menu()
	{
		System.out.println("###################################Menu###################################");
		System.out.println("# 1. Validar si un numero es impar                                       #");
		System.out.println("# 2. Validar si un numero es primo                                       #");
		System.out.println("# 3. Contar la cantidad de numero primos entre 2 y el numero ingresado   #");
		System.out.println("# 4. Salir                                                               #");
		System.out.println("##########################################################################");
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static void esImpar(int numero)
	{
		if(numero%2!=0)
		{
			System.out.println("Es impar");
		}
		else
		{
			System.out.println("El numero no es impar");
		}
	}
	
	public static void esPrimo(int numero)
	{
		boolean esPrimo = true;
		
		
		for(int i = 2; i<numero && esPrimo; i++)
		{
			if(numero%i==0)
			{
				esPrimo = false;
			}
		}
		
		if(esPrimo)
		{
			System.out.println("El numero es primo");
		}
		else
		{
			System.out.println("El numero no es primo");
		}
		
	}
	
	public static void contarPrimos(int numero)
	{	
		
		/*Llenando una lista con todos los numeros*/
		boolean [] listado = new boolean[numero+1];
		for(int i=0; i<=numero;i++)
		{
			listado[i] = true;
		}
		
		/*Descartando los numeros que no son primos*/
		for(int i=2; i*i<=numero; i++)
		{
			if(listado[i]==true)
			{
				for(int j = i*2; j<=numero; j+=i)
				{
					listado[j]=false;
				}
			}
		}
		
		/*Impresión de los numeros*/
		int cont = 0;
		for(int i = 2; i<=numero; i++)
		{
			if(listado[i]== true)
			{
				System.out.print(i + " ");
				cont++;
			}
		}
		
		System.out.println("\nLa cantidad de numeros primos es: " + cont);	
	}
	
	public static void main(String[] args)
	{
		int opcion, numero;
		
		do
		{
			menu();
			opcion = lector.nextInt();
			switch(opcion)
			{
				case 1:
					numero = ingresarNumero(opcion);
					esImpar(numero);
					break;
				case 2:
					numero = ingresarNumero(opcion);
					esPrimo(numero);
					break;
				case 3:
					numero = ingresarNumero(opcion);
					contarPrimos(numero);
					break;
				case 4:
					System.out.println("Adios!!!");
					break;
				default:
					System.out.println("La opcion ingresada no es valida.");
					break;
			}
		}
		while(opcion!=4);
		lector.close();
	}
}
