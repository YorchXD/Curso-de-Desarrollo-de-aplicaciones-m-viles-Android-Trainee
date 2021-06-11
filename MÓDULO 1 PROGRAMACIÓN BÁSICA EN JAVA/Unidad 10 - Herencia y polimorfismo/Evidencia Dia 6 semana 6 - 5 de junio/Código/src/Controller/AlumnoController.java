package Controller;

import BD.ConsultaAlumno;
import View.Alumno.ViewAlumno;
import Model.Alumno;
import Model.Estado;

public class AlumnoController
{
	/*Metodos que activan la vista segun sea el caso*/
	public static void crear()
	{
		ViewAlumno.crear();
	}
	
	public static void ver()
	{
		ViewAlumno.ver();
	}
	
	public static void modificar()
	{
		ViewAlumno.modificar();
	}
	
	public static void cambiarEstado()
	{
		ViewAlumno.cambiarEstado();
	}
	/*Fin mostrar vistas*/
	
	public static Alumno buscarAlumno(String run)
	{
		return ConsultaAlumno.consultarAlumno_run(run);
	}
	
	public static String registrarAlumno(String nombre, String run,int edad)
	{
		String mensaje = "";
		Alumno alumno = new Alumno(nombre, run, edad, Estado.HABILITADO);
		boolean validar = ConsultaAlumno.insertarAlumno(alumno);
		if(validar==true)
		{
			mensaje = "\nSe ha guardado exitosamente al alumno\n";
		}
		else
		{
			mensaje = "\nNo se pudo guardado al alumno\n";
		}
		return mensaje;
	}
	
	public static String actualizarAlumno(String nombre, String run, int edad)
	{
		String mensaje = "";
		boolean validar = ConsultaAlumno.UpdateAlumno(nombre, run, edad);
		if(validar==true)
		{
			mensaje = "/nSe ha modificado exitosamente el estado del alumno/n";
		}
		else
		{
			mensaje = "/nNo se pudo modificar el estado del alumno/n";
		}
		return mensaje;
	}
	
	public static String modificarEstadoAlumno(Alumno alumno)
	{
		String mensaje = "";
		boolean validar = ConsultaAlumno.UpdateAlumno_estado(alumno); 
		if(validar==true)
		{
			mensaje = "/nSe ha modificado exitosamente el estado del alumno/n";
		}
		else
		{
			mensaje = "/nNo se pudo modificar el estado del alumno/n";
		}
		return mensaje;
	}

}
