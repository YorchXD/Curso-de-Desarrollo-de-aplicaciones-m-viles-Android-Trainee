package View.Login;

import Utilidades.Utilidades;

public class ViewLogin 
{
	
	public static void menuTipoUsuario()
	{
		System.out.println("Tipos de usuarios: ");
		System.out.println("1. Administrador");
		System.out.println("2. Profesor");
		System.out.println("3. Apoderado");
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static int solicitarTipoUsuario()
	{
		String opcion;
		boolean validar;
	
		System.out.println("################LOGIN#####################");
		do
		{
			menuTipoUsuario();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>3)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
			
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>3);

		return Integer.parseInt(opcion);
	}
	
	public static String solicitarEmail()
	{
		String email;
		boolean validar;
		do
		{
			System.out.print("Ingrese su email: ");
			email = Utilidades.extracted().nextLine();
			validar = Utilidades.validarEmail(email);
			if(!validar)
			{
				System.out.println("Debe ingresar un email valido. Favor de ingresarlo nuevamente.\n");
			}
			
		}while(!validar);
		
		return email;
	}
	
	public static String solicitarClave()
	{
		String clave;
		System.out.print("Ingrese su clave: ");
		clave = Utilidades.extracted().nextLine();
		return clave;
	}
}
