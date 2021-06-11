package Controller;

import Model.Usuario;
import View.Usuario.ViewUsuario;

public class UsuarioController
{
	private static Usuario usuario;
	
	public static void viewAdministrador(Usuario usuario)
	{
		setUsuario(usuario);
		ViewUsuario.seleccionarOpcionAdministrador();
	}

	public static Usuario getUsuario()
	{
		return usuario;
	}

	public static void setUsuario(Usuario usuario)
	{
		UsuarioController.usuario = usuario;
	}
}