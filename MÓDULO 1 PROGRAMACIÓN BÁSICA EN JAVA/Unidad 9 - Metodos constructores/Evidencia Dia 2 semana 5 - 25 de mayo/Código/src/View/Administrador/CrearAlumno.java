package View.Administrador;

import Utilidades.Utilidades;

public class CrearAlumno {
	
	
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
			
		}while(!validar);
		
		run= run.replace(".","");
		run= run.replace(" ","");
		
		
		return run;
	}
	
	public static int solicitarEdad()
	{
		int edad = 0;

		
		do {
			
			try {
				System.out.print("Ingrese la edad del alumno: ");
				edad = Utilidades.extracted().nextInt(); 
				
			} catch (Exception e) {
				System.out.print("Debe ingresar un número, ");
			}
			
		} while (edad==0);

		
		return edad;
	}
	
	public static boolean solicitarRespuesta() {
		String mensaje = "NO";
		boolean respuesta = false;
		do {
			
			System.out.print("Desea modificar al alumno (SI o NO): ");
			mensaje = Utilidades.extracted().nextLine().toUpperCase();

		} while (!mensaje.equals("SI") && !mensaje.equals("NO"));
		
		if (mensaje.equals("SI")) respuesta = true;
		
		return respuesta;
		
		
	}

}
