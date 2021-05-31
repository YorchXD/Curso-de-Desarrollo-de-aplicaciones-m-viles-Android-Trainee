package View.Login;

import Utilidades.Utilidades;
import Controller.LoginController;
import Model.TipoUsuario;
import Model.Usuario;

public class ViewLogin 
{
	
	public static void menuTipoUsuario()
	{
		TipoUsuario[] tipoUsuarios = TipoUsuario.values();
		System.out.println("Tipos de usuarios: ");
		
		for (int i = 0; i< tipoUsuarios.length; i++)
		{
			System.out.println((i+1) + ". " + tipoUsuarios[i]);
		}
		
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static TipoUsuario solicitarTipoUsuario()
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

		return TipoUsuario.getTipoUsuario(Integer.parseInt(opcion));
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
	
	public static void solicitarDatos()
	{
		TipoUsuario tipoUsuario;
		String email, clave;
		Usuario usuario = null;
		do
		{
			tipoUsuario =solicitarTipoUsuario();
			email = solicitarEmail();
			clave = solicitarClave();
			usuario = LoginController.validarDatos(tipoUsuario, email, clave);
			
			if(usuario==null)
			{
				System.out.println("Los datos ingresados no son correctos o puede que no este registrado. Favor de verificarlos e ingreselos nuevamente.");
			}
		}
		while(usuario==null);
		
		LoginController.accesos(usuario);
		
	}
}
