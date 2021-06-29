package View.Alumno;

import Utilidades.Utilidades;
import Controller.AlumnoController;
import Model.Alumno;

public class ViewAlumno
{
	public static int solicitarEdad()
	{
		boolean validar = true;
		String edad = "0";
		do
		{
			System.out.print("Ingrese la edad del alumno: ");
			edad = Utilidades.extracted().nextLine();
			if(Utilidades.esNumero(edad) && Integer.parseInt(edad)>0 && Integer.parseInt(edad)<100 )
			{
				validar = false;
			}
			else
			{
				System.out.println("Se deben ingresar solo numeros, mayores que cero y menores que 100. Favor de volver a ingresar una edad valida.");
			}
		}
		while(validar);
		return Integer.parseInt(edad);
	}

	public static void crear()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                      Crear alumno                    *");
		System.out.println("********************************************************\n");
		
		String run = Utilidades.solicitarRun(Utilidades.ALUMNO);
		String nombre  = Utilidades.solicitarNombre(Utilidades.ALUMNO);
		int edad = solicitarEdad();

		System.out.println("\n********************************************************");
		System.out.println(AlumnoController.registrarAlumno(nombre, run, edad));
		System.out.println("********************************************************\n");
	}

	public static void ver()
	{
		Alumno alumno = null;
		String run;

		run = Utilidades.solicitarRun(Utilidades.ALUMNO);
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

		run = Utilidades.solicitarRun(Utilidades.ALUMNO);
		alumno = AlumnoController.buscarAlumno(run);
		if (alumno != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del alumno                   *");
			System.out.println("********************************************************\n");
			System.out.println(alumno.mostrarDatos());
			System.out.println("********************************************************\n");
			confirmacion = Utilidades.confirmacionModificarDatos(Utilidades.ALUMNO);

			if (confirmacion)
			{
				String nombre = Utilidades.solicitarNombre(Utilidades.ALUMNO);
				int edad = solicitarEdad();
				System.out.println("\n********************************************************");
				System.out.println(AlumnoController.actualizarAlumno(alumno, nombre, edad));
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

		run = Utilidades.solicitarRun(Utilidades.ALUMNO);
		alumno = AlumnoController.buscarAlumno(run);

		if (alumno != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del alumno                   *");
			System.out.println("********************************************************\n");
			System.out.println(alumno.mostrarDatos());
			System.out.println("********************************************************\n");

			confirmacion = Utilidades.confirmacionCambiarEstado(alumno.getEstado(), Utilidades.ALUMNO);

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
		String runAlumno = Utilidades.solicitarRun(Utilidades.ALUMNO);
		String runApoderado = Utilidades.solicitarRun(Utilidades.APODERADO);
		System.out.println("\n********************************************************");
		System.out.println(AlumnoController.asociarApoderadoAlumno(runAlumno, runApoderado));
		System.out.println("********************************************************\n");
	}

}
