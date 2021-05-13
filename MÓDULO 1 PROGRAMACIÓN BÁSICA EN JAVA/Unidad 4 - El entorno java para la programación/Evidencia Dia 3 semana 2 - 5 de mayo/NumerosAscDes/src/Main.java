import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		int num1, num2, num3;
		
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingrese el dato de la variable 1: ");
		num1 = lector.nextInt();
		System.out.println("Ingrese el dato de la variable 2: ");
		num2 = lector.nextInt();
		System.out.println("Ingrese el dato de la variable 3: ");
		num3 = lector.nextInt();
		lector.close();
		
		/*Orden ascendente*/
		System.out.println("\n");
		System.out.print("Numeros ordenados de forma ascendente: ");
		if(num1<=num2 && num1<=num3)
		{
			if(num2<=num3)
			{
				System.out.println(num1 + ", " + num2 + ", " + num3);
			}
			else
			{
				System.out.println(num1 + ", " + num3 + ", " + num2);
			}
		}
		else if(num2<=num1 && num2<=num3) 
		{
			if(num1<=num3)
			{
				System.out.println(num2 + ", " + num1 + ", " + num3);
			}
			else
			{
				System.out.println(num2 + ", " + num3 + ", " + num1);
			}
		}
		else
		{
			if(num1<=num2)
			{
				System.out.println(num3 + ", " + num1 + ", " + num2);
			}
			else
			{
				System.out.println(num3 + ", " + num2 + ", " + num1);
			}
		}
		
		/*Orden descendente*/
		System.out.print("Numeros ordenados de forma descendente: ");
		if(num1>=num2 && num1>=num3)
		{
			if(num2>=num3)
			{
				System.out.println(num1 + ", " + num2 + ", " + num3);
			}
			else
			{
				System.out.println(num1 + ", " + num3 + ", " + num2);
			}
		}
		else if(num2>=num1 && num2>=num3)
		{
			if(num1>=num3)
			{
				System.out.println(num2 + ", " + num1 + ", " + num3);
			}
			else
			{
				System.out.println(num2 + ", " + num3 + ", " + num1);
			}
		}
		else
		{
			if(num1>=num2)
			{
				System.out.println(num3 + ", " + num1 + ", " + num2);
			}
			else
			{
				System.out.println(num3 + ", " + num2 + ", " + num1);
			}
		}

	}

}
