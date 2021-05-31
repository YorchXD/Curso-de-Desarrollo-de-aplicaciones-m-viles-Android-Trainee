package View.Alumno;

import BD.ConsultaAlumno;
import Utilidades.Utilidades;
import controller.AlumnoController;
import model.Alumno;

public class ViewAlumno {
	
	
	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre del alumno: ");
		nombre = Utilidades.extracted().nextLine();

		
		return nombre;
	}
	
	public static String solicitarRun()
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run del Alumno, formato (XX.XXX.XXX-X): ");
			run = Utilidades.extracted().nextLine();
			validar = Utilidades.validarRun(run);
			if(!validar)
			{
				System.out.println("Debe ingresar un run valido. Favor de ingresar nuevamente.\n");
			}
			
		}
		while(!validar);
		
		run= run.replace(".","");
		run= Utilidades.formatearRun(run);
		
		
		return run;
	}
	
	public static int solicitarEdad()
	{
		int edad = 0;
		
		do {
			
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
		while (edad==0);

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
		
		if (mensaje.equals("SI")) respuesta = true;
		
		return respuesta;
	}
	
	public static void crear()
	{
		System.out.println("\n------------------------------------------- ");
		System.out.println("	Opcion para Crear alumno: ");
		System.out.println("------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run;
		String mensaje = "";

		do 
		{
			run = solicitarRun();
			alumno = AlumnoController.buscarAlumno(run);
			if(alumno==null) 
			{
				nombre = solicitarNombre();
				edad = solicitarEdad();
				mensaje = AlumnoController.registrarAlumno(nombre,run,edad);
				
				System.out.println("\n\n------------------------------------------- ");
				System.out.println(mensaje);
			}
			else 
			{
				System.out.println("\n\n----------------------------------------------------- ");
				System.out.println("\n\n	El alumno ya se encuentra registrado ");
				System.out.println("\n\n----------------------------------------------------- ");
				mensaje="Alumno registrado";
			}
		} 
		while (mensaje=="Alumno registrado");
	}
	
	public static void ver()
	{
		System.out.println("\n------------------------------------------- ");
		System.out.println("\n	Opcion para Ver alumno: ");
		System.out.println("\n------------------------------------------- ");
		Alumno alumno = null;
		String run;
		
		run = solicitarRun();
		alumno = AlumnoController.buscarAlumno(run);
		if(alumno!=null) 
		{
			alumno.mostrarDatos();
			System.out.println("\n\n ");
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El alumno no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
	}
	
	public static void modificar()
	{
		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opcion para modificar un alumno: ");
		System.out.println("--------------------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run, mensaje = "";
		Boolean confirmacion=false;
		
		run = solicitarRun();
		alumno = AlumnoController.buscarAlumno(run);
		if(alumno!=null) 
		{
			alumno.mostrarDatos();
			confirmacion= solicitarRespuesta(); 
			
			if(confirmacion) 
			{
				nombre = solicitarNombre();
				edad = solicitarEdad();
				
				mensaje = AlumnoController.actualizarAlumno(nombre, run,edad);
				System.out.println("\n-------------------------------------------------------- ");
				System.out.println(mensaje);
				System.out.println("\n-------------------------------------------------------- ");
			}
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El alumno no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
	}
	
	public static void cambiarEstado()
	{
		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opcion para Habilitar o Deshabilitar un alumno: ");
		System.out.println("--------------------------------------------------------- ");
		
		Alumno alumno = null;
		String run, mensaje = "";
		Boolean confirmacion=false;

		run = solicitarRun();
		alumno =  AlumnoController.buscarAlumno(run);
		
		if(alumno!=null) 
		{
			alumno.mostrarDatos();
			System.out.println("\n");
			
			confirmacion = solicitarRespuesta(); 
			
			if(confirmacion)
			{
				mensaje = ConsultaAlumno.UpdateAlumno_estado(alumno);
				System.out.println("\n-------------------------------------------------------- ");
				System.out.println(mensaje);
				System.out.println("\n-------------------------------------------------------- ");
			}
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El alumno no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
			mensaje="Alumno no registrado";
		}
	}

}
