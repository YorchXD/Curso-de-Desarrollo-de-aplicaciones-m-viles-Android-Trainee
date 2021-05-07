import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		if((5==5)|(33==2))
		{
			System.out.println("entro");
		}
		
		
		int num1, num2, num3, menor, medio, mayor;
		
		Scanner lector = new Scanner(System.in);
		System.out.println("Ingrese el dato de la variable 1: ");
		num1 = lector.nextInt();
		System.out.println("Ingrese el dato de la variable 2: ");
		num2 = lector.nextInt();
		System.out.println("Ingrese el dato de la variable 3: ");
		num3 = lector.nextInt();
		lector.close();
		
		//Seleccionar numero mayo
		if(num1>=num2 && num1>=num3)
		{
			mayor = num1;
		}
		else if(num2>=num1 && num2>=num3)
		{
			mayor = num2;
		}
		else
		{
			mayor = num3;
		}
		
		//seleccionar numero menor
		if(num1<=num2 && num1<=num3)
		{
			menor = num1;
		}
		else if(num2<=num1 && num2<=num3)
		{
			menor = num2;
		}
		else
		{
			menor = num3;
		}
		
		medio = (num1+num2+num3)-menor-mayor;
		
		System.out.println("numeros ordenados de forma asc: " + menor + ", " + medio + ", " + mayor);
		System.out.println("numeros ordenados de forma desc: " + mayor + ", " + medio + ", " + menor);

	}

}
