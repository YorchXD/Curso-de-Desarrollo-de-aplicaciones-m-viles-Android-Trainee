package controller;

import BD.ConsultaUsuario;
import View.Login.ViewLogin;
import model.Usuario;

public class LoginController 
{
	public static Usuario solicitarDatosAcceso()
	{
		Usuario usuario = null;
		int tipoUsuario;
		String email, clave;
		
		do
		{
			tipoUsuario = ViewLogin.solicitarTipoUsuario();
			email = ViewLogin.solicitarEmail();
			clave = ViewLogin.solicitarClave();
			usuario = ConsultaUsuario.IniciarSesion(tipoUsuario, email, clave);
			if(usuario==null)
			{
				System.out.println("Los datos ingresados no son correctos o puede que no este registrado. Favor de verificarlos e ingreselos nuevamente.");
			}
		}
		while(usuario==null);
		
		return usuario;
	}
	
}
