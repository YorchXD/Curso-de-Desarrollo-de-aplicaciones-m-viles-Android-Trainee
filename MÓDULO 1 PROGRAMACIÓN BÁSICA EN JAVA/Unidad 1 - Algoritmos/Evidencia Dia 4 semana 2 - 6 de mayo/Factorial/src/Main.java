import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner lector = new Scanner(System.in);
		int n, cont = 1, resultado=1;
		
		/*Validacion de numero positivo*/
		do
		{
			System.out.print("Ingrese un numero mayoro o igual a cero: ");
			n = lector.nextInt();
		}
		while(n<0);
		
		/*Calculo factorial*/
		while(cont<=n)
		{
			resultado*= cont;
			cont++;
		}
		
		System.out.println("El factorial de " + n + " es igual a: " + resultado);
		
	}

}
