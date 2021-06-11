package View.Alumno;

import Utilidades.Utilidades;
import Controller.AlumnoController;
import Model.Alumno;

public class ViewAlumno
{

	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre del alumno: ");
		nombre = Utilidades.extracted().nextLine();

		return nombre;
	}

	public static String solicitarRun(String rol)
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run del " + rol + ", formato (XX.XXX.XXX-X): ");
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

	public static int solicitarEdad()
	{
		int edad = 0;

		do
		{
			try
			{
				System.out.print("Ingrese la edad del alumno: ");
				edad = Utilidades.extracted().nextInt();

			}
			catch (Exception e)
			{
				System.out.print("Debe ingresar un numero, ");
			}

		}
		while (edad == 0);

		return edad;
	}

	public static boolean solicitarRespuesta()
	{
		String mensaje = "NO";
		boolean respuesta = false;
		do
		{

			System.out.print("Desea modificar al alumno (SI o NO): ");
			mensaje = Utilidades.extracted().nextLine().toUpperCase();

		}
		while (!mensaje.equals("SI") && !mensaje.equals("NO"));

		if (mensaje.equals("SI"))
			respuesta = true;

		return respuesta;
	}

	public static void crear()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                      Crear alumno                    *");
		System.out.println("********************************************************\n");
		Alumno alumno = null;
		int edad;
		String nombre, run;
		String mensaje = "";

		run = solicitarRun("Alumno");
		alumno = AlumnoController.buscarAlumno(run);
		if (alumno == null)
		{
			nombre = solicitarNombre();
			edad = solicitarEdad();
			mensaje = AlumnoController.registrarAlumno(nombre, run, edad);

			System.out.println("\n********************************************************");
			System.out.println(mensaje);
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*         El alumno ya se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void ver()
	{
		Alumno alumno = null;
		String run;

		run = solicitarRun("Alumno");
		alumno = AlumnoController.buscarAlumno(run);
		if (alumno != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del alumno                   *");
			System.out.println("********************************************************\n");
			System.out.println(alumno.mostrarDatos());
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*         El alumno no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void modificar()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                    Modificar alumno                  *");
		System.out.println("********************************************************\n");
		Alumno alumno = null;
		String run;
		Boolean confirmacion = false;

		run = solicitarRun("Alumno");
		alumno = AlumnoController.buscarAlumno(run);
		if (alumno != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del alumno                   *");
			System.out.println("********************************************************\n");
			System.out.println(alumno.mostrarDatos());
			System.out.println("********************************************************\n");
			confirmacion = solicitarRespuesta();

			if (confirmacion)
			{
				alumno.setNombre(solicitarNombre());
				alumno.setEdad(solicitarEdad());
				System.out.println("\n********************************************************");
				System.out.println(AlumnoController.actualizarAlumno(alumno));
				System.out.println("********************************************************\n");
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*         El alumno no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void cambiarEstado()
	{
		System.out.println("\n********************************************************");
		System.out.println("*           Habilitar o Deshabilitar un Alumno         *");
		System.out.println("********************************************************\n");

		Alumno alumno = null;
		String run, mensaje = "";
		Boolean confirmacion = false;

		run = solicitarRun("Alumno");
		alumno = AlumnoController.buscarAlumno(run);

		if (alumno != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del alumno                   *");
			System.out.println("********************************************************\n");
			System.out.println(alumno.mostrarDatos());
			System.out.println("********************************************************\n");

			confirmacion = solicitarRespuesta();

			if (confirmacion)
			{
				mensaje = AlumnoController.modificarEstadoAlumno(alumno);
				System.out.println("\n********************************************************");
				System.out.println(mensaje);
				System.out.println("********************************************************\n");
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*         El alumno no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void asociarApoderado()
	{
		System.out.println("\n********************************************************");
		System.out.println("*             Asociar apoderado a un alumno            *");
		System.out.println("********************************************************\n");
		String runAlumno = solicitarRun("Alumno");
		String runApoderado = solicitarRun("Apoderado");
		System.out.println("\n********************************************************");
		System.out.println(AlumnoController.asociarApoderadoAlumno(runAlumno, runApoderado));
		System.out.println("********************************************************\n");
		
	}

}
