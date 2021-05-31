package View.Profesor;



import BD.ConsultaProfesor;
import Utilidades.Utilidades;
import Controller.ProfesorController;
import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

public class ViewProfesor {

	public static void crear() {
		
		System.out.println("\n------------------------------------------- ");
		System.out.println("	Opcion para Crear Profesor: ");
		System.out.println("------------------------------------------- ");
		Profesor profesor = null;
		String nombre, run, especialidad, email, clave;
		Estado estado;
		TipoUsuario tipoUsuario;
		String mensaje = "";

		do 
		{
			run = solicitarRun();
			profesor = ProfesorController.buscarProfesor(run);
			if(profesor==null) 
			{
				nombre = solicitarNombre();
				email = solicitarEmail();
				clave=solicitarClave();
				especialidad = solicitarEspecialidad();
				estado = Estado.HABILITADO;
				tipoUsuario = TipoUsuario.PROFESOR;
				
				profesor = new Profesor(nombre, email, clave, estado, run, tipoUsuario, especialidad);
				
				mensaje = ConsultaProfesor.insertarProfesor(profesor);
				
				System.out.println("\n\n------------------------------------------- ");
				System.out.println(mensaje);
				System.out.println("\n\n------------------------------------------- ");
			}
			else 
			{
				System.out.println("\n\n----------------------------------------------------- ");
				System.out.println("\n\n	El profesor ya se encuentra registrado ");
				System.out.println("\n\n----------------------------------------------------- ");
				mensaje="Profesor registrado";
			}
		} 
		while (mensaje=="Profesor registrado");
		
	}

	public static void ver() {

		System.out.println("\n------------------------------------------- ");
		System.out.println("\n	Opcion para Ver Profesor: ");
		System.out.println("\n------------------------------------------- ");
		Profesor profesor = null;
		String run;
		
		run = solicitarRun();
		profesor = ProfesorController.buscarProfesor(run);
		if(profesor!=null) 
		{
			profesor.mostrarDatos();
			System.out.println("\n\n ");
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El Profesor no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
	
		
	}

	public static void modificar() {

		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opcion para modificar un Profesor: ");
		System.out.println("--------------------------------------------------------- ");
		Profesor profesor = null;
		String nombre, run, especialidad, email, clave, mensaje;
		Estado estado;
		TipoUsuario tipoUsuario;
		Boolean confirmacion=false;
		
		run = solicitarRun();
		profesor = ProfesorController.buscarProfesor(run);
		
		if(profesor!=null) 
		{
			
			
				nombre = solicitarNombre();
				email = solicitarEmail();
				clave=solicitarClave();
				especialidad = solicitarEspecialidad();
				confirmacion= solicitarRespuesta(); 

			if(confirmacion) 
			{
				
				mensaje = ProfesorController.actualizarProfesor(nombre, email,clave, especialidad, run);
				System.out.println("\n-------------------------------------------------------- ");
				System.out.println(mensaje);
				System.out.println("\n-------------------------------------------------------- ");
			}
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El profesor no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
		
		
		
		
		
	}

	public static void cambiarEstado() {

		System.out.println("\n-------------------------------------------------------");
		System.out.println("	Opcion para Habilitar o Deshabilitar un Profesor: ");
		System.out.println("--------------------------------------------------------- ");
		
		Profesor profesor = null;
		String run, mensaje = "";
		Boolean confirmacion=false;
		
		run = solicitarRun();
		profesor =  ProfesorController.buscarProfesor(run);
		
		if(profesor!=null) 
		{
			profesor.mostrarDatos();
			System.out.println("\n");
			
			confirmacion = solicitarRespuesta(); 
			
			if(confirmacion)
			{
				mensaje = ConsultaProfesor.UpdateProfesor_estado(profesor);
				System.out.println("\n-------------------------------------------------------- ");
				System.out.println(mensaje);
				System.out.println("\n-------------------------------------------------------- ");
			}
		}
		else 
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El profesor no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
			mensaje="profesor no registrado";
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
	
	public static String solicitarEmail()
	{
		String email;
		boolean validar;
		do
		{
			System.out.print("Ingrese su email: ");
			email = Utilidades.extracted().nextLine();
			validar = Utilidades.validarEmail(email);
			if(!validar)
			{
				System.out.println("Debe ingresar un email valido. Favor de ingresarlo nuevamente.\n");
			}
			
		}while(!validar);
		
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
		
		if (mensaje.equals("SI")) respuesta = true;
		
		return respuesta;
	}
}
