package controller;

import BD.ConsultaUsuario;
import View.Login.ViewLogin;
import model.Apoderado;
import model.Profesor;
import model.TipoUsuario;
import model.Usuario;

public class LoginController 
{
	
	public static Usuario validarDatos(TipoUsuario tipoUsuario, String email, String clave)
	{
		return ConsultaUsuario.IniciarSesion(tipoUsuario, email, clave);
	}
	
	public static void accesos(Usuario usuario)
	{
		switch (usuario.getTipoUsuario().toString())
		{
			case "ADMINISTRADOR":
				UsuarioController.viewAdministrador(usuario);
				break;
			case "PROFESOR":
				Profesor profesor = new Profesor(usuario);
				//profesor.mostrarDatos();
				System.out.println("Ir al controlador profesor");
				break;
			default:
				Apoderado apoderado = new Apoderado(usuario);
				//apoderado.mostrarDatos();
				System.out.println("Ir al controlador apoderado");
				break;
		}
	}

	public static void viewLogin()
	{
		ViewLogin.solicitarDatos();
	}
	
}

