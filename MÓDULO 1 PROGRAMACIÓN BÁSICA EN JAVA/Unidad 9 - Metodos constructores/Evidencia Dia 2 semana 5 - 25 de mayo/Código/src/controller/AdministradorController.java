package controller;

import BD.ConsultaUsuario;
import BD.ConsultaAlumno;
import View.Administrador.CrearAlumno;
import View.Administrador.IndexAdministrador;
import View.Login.ViewLogin;
import model.Alumno;
import model.Usuario;

public class AdministradorController
{
	public static void crearAlumno()
	{
		System.out.println("\n------------------------------------------- ");
		System.out.println("	Opción para Crear alumno: ");
		System.out.println("------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run, estado;

		String mensaje = "";
		

		
		do {
			run = CrearAlumno.solicitarRun();
			alumno = ConsultaAlumno.consultarAlumno_run(run);
			if(alumno==null) {
				nombre = CrearAlumno.solicitarNombre();
				edad = CrearAlumno.solicitarEdad();
				estado = null;
				alumno = new Alumno(nombre,run,edad,estado);
				mensaje = ConsultaAlumno.insertarAlumno(alumno);
				
				System.out.println("\n\n------------------------------------------- ");
				System.out.println(mensaje);
				alumno.mostrarDatos();
				
			}else {
				System.out.println("\n\n----------------------------------------------------- ");
				System.out.println("\n\n	El alumno ya se encuentra registrado ");
				System.out.println("\n\n----------------------------------------------------- ");
				mensaje="Alumno registrado";
			}
			
		} while (mensaje=="Alumno registrado");
		
	}
	
	public static void VerAlumno() {
		
		System.out.println("\n------------------------------------------- ");
		System.out.println("\n	Opción para Ver alumno: ");
		System.out.println("\n------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run, estado;
		String mensaje="";
		
		do {
			run = CrearAlumno.solicitarRun();
			alumno = ConsultaAlumno.consultarAlumno_run(run);
			if(alumno!=null) {
				alumno.mostrarDatos();
				System.out.println("\n\n ");
				
				
			}else {
				System.out.println("\n\n------------------------------------------- ");
				System.out.println("\n\n	El alumno no se encuentra registrado ");
				System.out.println("\n\n------------------------------------------- ");
				mensaje="Alumno no registrado";
			}
			
		} while (mensaje=="Alumno no registrado");
	}
	
	public static void CambioEstadoAlumno() {
		
		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opción para Habilitar o Deshabilitar un alumno: ");
		System.out.println("--------------------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run, estado;

		String mensaje = "";
		Boolean confirmacion=false;
		
		do {
			run = CrearAlumno.solicitarRun();
			alumno = ConsultaAlumno.consultarAlumno_run(run);
			if(alumno!=null) {
				alumno.mostrarDatos();
				System.out.println("\n");
				
				confirmacion=CrearAlumno.solicitarRespuesta(); 
				
				if(confirmacion) {
					
					mensaje = ConsultaAlumno.UpdateAlumno_estado(alumno);
					System.out.println("\n-------------------------------------------------------- ");
					System.out.println(mensaje);
					System.out.println("\n-------------------------------------------------------- ");
				}
				
				
			}else {
				System.out.println("\n\n------------------------------------------- ");
				System.out.println("\n\n	El alumno no se encuentra registrado ");
				System.out.println("\n\n------------------------------------------- ");
				mensaje="Alumno no registrado";
			}
			
		} while (mensaje=="Alumno no registrado");
		
		
		
	}
	
	public static void ModificarAlumno() {
		
		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opción para modificar un alumno: ");
		System.out.println("--------------------------------------------------------- ");
		Alumno alumno = null;
		int edad;
		String nombre, run, estado;
		Boolean confirmacion=false;
		String mensaje = "";
		
		
		do {
			run = CrearAlumno.solicitarRun();
			alumno = ConsultaAlumno.consultarAlumno_run(run);
			if(alumno!=null) {
				alumno.mostrarDatos();
				
				confirmacion=CrearAlumno.solicitarRespuesta(); 
				
				if(confirmacion) {
					
					nombre = CrearAlumno.solicitarNombre();
					edad = CrearAlumno.solicitarEdad();
					
					mensaje = ConsultaAlumno.UpdateAlumno(run,nombre,edad);
					System.out.println("\n-------------------------------------------------------- ");
					System.out.println(mensaje);
					System.out.println("\n-------------------------------------------------------- ");
				}
				
				
			}else {
				System.out.println("\n\n------------------------------------------- ");
				System.out.println("\n\n	El alumno no se encuentra registrado ");
				System.out.println("\n\n------------------------------------------- ");
				mensaje="Alumno no registrado";
			}
			
		} while (mensaje=="Alumno no registrado");
	}
	
	
	public static void seleccionarOpcion(Usuario usuario)
	{
		System.out.println("\n\nDatos del administrador: ");
		usuario.mostrarDatos();
		System.out.println("\n\n");
		
		int opcion;
		do
		{
			opcion = IndexAdministrador.accionEjecucion();
			
			switch(opcion)
			{
				case 1:
					System.out.println("Ir a crear curso");
					break;
				case 2:
					System.out.println("Ir a ver curso");
					break;
				case 3:
					System.out.println("Ir a  modificar curso");
					break;
				case 4:
					System.out.println("Ir a habilitar o deshabilitar curso");
					break;
				case 5:
					System.out.println("Ir a crear asignatura");
					break;
				case 6:
					System.out.println("Ir a ver asignatura");
					break;
				case 7:
					System.out.println("Ir a modificar asignatura");
					break;
				case 8:
					System.out.println("Ir a habilitar o deshabilitar asignatura");
					break;
				case 9:
					System.out.println("Ir a crear unidad");
					break;
				case 10:
					System.out.println("Ir a ver unidad");
					break;
				case 11:
					System.out.println("Ir a modificar unidad");
					break;
				case 12:
					System.out.println("Ir a habilitar o deshabilitar unidad");
					break;
				case 13:
					crearAlumno();
					break;
				case 14:
					VerAlumno();
					break;
				case 15:
					ModificarAlumno();
					break;
				case 16:
					CambioEstadoAlumno();
					break;
				default:
					System.out.println("Sesion cerrada...\n\n");
					break;
			}
		}
		while(opcion!=17);
	}

}