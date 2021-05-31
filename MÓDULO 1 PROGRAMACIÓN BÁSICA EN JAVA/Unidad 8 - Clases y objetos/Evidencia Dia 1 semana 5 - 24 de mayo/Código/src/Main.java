import controller.AdministradorController;
import controller.LoginController;
import model.Apoderado;
import model.Profesor;
import model.Usuario;

public class Main
{
	public static void main(String[] args)
	{
		do
		{
			Usuario usuario = LoginController.solicitarDatosAcceso();
			/* Si el usuario se encuentra, muestra los datos del usuario */
			
			switch (usuario.getTipoUsuario())
			{
				case "ADMINISTRADOR":
					AdministradorController.seleccionarOpcion(usuario);
					break;
				case "PROFESOR":
					Profesor profesor = new Profesor(usuario);
					profesor.mostrarDatos();
					System.out.println("Ir al controlador profesor");
					break;
				default:
					Apoderado apoderado = new Apoderado(usuario);
					apoderado.mostrarDatos();
					System.out.println("Ir al controlador apoderado");
					break;
			}
		}
		while (true);
	}
}
