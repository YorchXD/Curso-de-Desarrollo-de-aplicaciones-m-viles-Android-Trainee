import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		int cont = 0, n;
		Scanner lector = new Scanner(System.in);
		do
		{
			System.out.print("Ingrese un numero mayor o igual a 2: ");
			n = lector.nextInt();
		}
		while(n<2);
		lector.close();
		
		for(int i = 2; i<=n; i+=2, cont++)
		{
			System.out.print(i+" ");	
		}
		System.out.println("\nLa cantidad de numeros pares entre 2 y " + n + " es: " + cont);

	}

}
