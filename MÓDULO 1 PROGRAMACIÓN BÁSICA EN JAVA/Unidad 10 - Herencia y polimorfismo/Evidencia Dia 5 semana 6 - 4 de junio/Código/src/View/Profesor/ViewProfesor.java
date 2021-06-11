package View.Profesor;

import Utilidades.Utilidades;
import Controller.ProfesorController;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

public class ViewProfesor
{
	public static void crear()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                    Crear profesor                    *");
		System.out.println("********************************************************\n");
		
		Profesor profesor = null;
		String nombre, run, especialidad, email, clave;
		Estado estado;
		TipoUsuario tipoUsuario;
		
		run = solicitarRun();
		profesor = ProfesorController.buscarUsuario(run);
		if (profesor == null)
		{
			nombre = solicitarNombre();
			email = solicitarEmail();
			clave = solicitarClave();
			especialidad = solicitarEspecialidad();
			estado = Estado.HABILITADO;
			tipoUsuario = TipoUsuario.PROFESOR;
			Profesor profesorAux = new Profesor(nombre, email, clave, estado, run, tipoUsuario, especialidad);
			System.out.println(ProfesorController.registrarProfesor(profesorAux));
		}
		else
		{
			if (profesor.getTipoUsuario() == TipoUsuario.PROFESOR)
			{
				System.out.println("\n********************************************************");
				System.out.println("*       El profesor ya se encuentra registrado         *");
				System.out.println("********************************************************\n");
			}
			else
			{
				profesor.setEspecialidad(solicitarEspecialidad());
				profesor.setEmail(solicitarEmail());
				System.out.println(ProfesorController.registrarCuenta(profesor));
			}
		}
	}

	public static void ver()
	{		
		Profesor profesor = null;
		String run;

		run = solicitarRun();
		profesor = ProfesorController.buscarProfesor(run);
		if (profesor != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del profesor                 *");
			System.out.println("********************************************************\n");
			System.out.println(profesor.mostrarDatos());
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void modificar()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                   Modificar profesor                 *");
		System.out.println("********************************************************\n");
		Profesor profesor = null;
		String nombre, run, especialidad, email, clave;
		Boolean confirmacion = false;

		run = solicitarRun();
		profesor = ProfesorController.buscarProfesor(run);

		if (profesor != null)
		{
			nombre = solicitarNombre();
			email = solicitarEmail();
			clave = solicitarClave();
			especialidad = solicitarEspecialidad();
			confirmacion = solicitarRespuesta();

			if (confirmacion)
			{
				System.out.println(ProfesorController.actualizarProfesor(profesor, nombre, email, clave, especialidad));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void cambiarEstado()
	{
		System.out.println("\n********************************************************");
		System.out.println("*         Habilitar o Deshabilitar un Profesor         *");
		System.out.println("********************************************************\n");

		Profesor profesor = null;
		String run;
		Boolean confirmacion = false;

		run = solicitarRun();
		profesor = ProfesorController.buscarProfesor(run);

		if (profesor != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del profesor                 *");
			System.out.println("********************************************************\n");
			System.out.println(profesor.mostrarDatos());
			System.out.println("********************************************************\n");

			confirmacion = solicitarRespuesta();

			if (confirmacion)
			{
				System.out.println(ProfesorController.cambiarEstadoProfesor(profesor));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre del profesor: ");
		nombre = Utilidades.extracted().nextLine();
		return nombre;
	}

	public static String solicitarRun()
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run del profesor, formato (XX.XXX.XXX-X): ");
			run = Utilidades.extracted().nextLine();
			validar = Utilidades.validarRun(run);
			if (!validar)
			{
				System.out.println("Debe ingresar un run valido. Favor de ingresar nuevamente.\n");
			}

		}
		while (!validar);

		run = run.replace(".", "");
		run = Utilidades.formatearRun(run);
		return run;
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
			if (!validar)
			{
				System.out.println("Debe ingresar un email valido. Favor de ingresarlo nuevamente.\n");
			}
		}
		while (!validar);

		return email;
	}

	public static String solicitarClave()
	{
		String clave;
		System.out.print("Ingrese su clave: ");
		clave = Utilidades.extracted().nextLine();
		return clave;
	}

	public static String solicitarEspecialidad()
	{
		String especialidad;
		System.out.print("Ingrese su especialidad: ");
		especialidad = Utilidades.extracted().nextLine();
		return especialidad;
	}

	public static boolean solicitarRespuesta()
	{
		String mensaje = "NO";
		boolean respuesta = false;
		do
		{
			System.out.print("Desea modificar al profesor (SI o NO): ");
			mensaje = Utilidades.extracted().nextLine().toUpperCase();
		}
		while (!mensaje.equals("SI") && !mensaje.equals("NO"));

		if (mensaje.equals("SI"))
		{
			respuesta = true;
		}
		
		return respuesta;
	}
}
