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
		System.out.println("\n********************************************************");
		System.out.println("*             Seleccione el tipo de usuario            *");
		System.out.println("********************************************************\n");
		
		for (int i = 0; i< tipoUsuarios.length; i++)
		{
			System.out.println((i+1) + ". " + tipoUsuarios[i]);
		}
		System.out.println("********************************************************\n");
		
		System.out.print("Ingrese su opcion: ");
	}
	
	public static TipoUsuario solicitarTipoUsuario()
	{
		String opcion;
		boolean validar;
	
		System.out.println("\n********************************************************");
		System.out.println("************************Login***************************");
		System.out.println("********************************************************\n");
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
	
	public static void solicitarDatos()
	{
		TipoUsuario tipoUsuario;
		String email, clave;
		Usuario usuario = null;
		do
		{
			tipoUsuario =solicitarTipoUsuario();
			String rol = "";
			
			if(tipoUsuario.equals(TipoUsuario.ADMINISTRADOR))
			{
				rol = Utilidades.ADMINISTRADOR;
			}
			else if(tipoUsuario.equals(TipoUsuario.PROFESOR))
			{
				rol = Utilidades.PROFESOR;
			}
			else
			{
				rol = Utilidades.APODERADO;
			}
			
			email = Utilidades.solicitarEmail(rol);
			clave = Utilidades.solicitarClave(rol);
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
