package Controller;

import BD.ConsultaUsuario;
import View.Login.ViewLogin;
import Model.Apoderado;
import Model.Profesor;
import Model.TipoUsuario;
import Model.Usuario;

public class LoginController 
{
	/**
	 * Tras obtener los datos de acceso, se procede a buscar la cuenta en la BD para iniciar sesion.
	 * @param tipoUsuario
	 * @param email
	 * @param clave
	 * @return usuario o null
	 */
	public static Usuario validarDatos(TipoUsuario tipoUsuario, String email, String clave)
	{
		return ConsultaUsuario.IniciarSesion(tipoUsuario, email, clave);
	}
	
	/**
	 * Tras obtener al usuario logueado se procede a ver su tipo y se muestra la vista correspondiente del usuario.
	 * @param usuario
	 */
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

