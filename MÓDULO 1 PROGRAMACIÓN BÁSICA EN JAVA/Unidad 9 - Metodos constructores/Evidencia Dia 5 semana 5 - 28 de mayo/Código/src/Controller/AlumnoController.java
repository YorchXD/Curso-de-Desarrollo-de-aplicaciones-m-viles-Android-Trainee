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
		Alumno alumno = new Alumno(nombre, run, edad, Estado.HABILITADO);
		return ConsultaAlumno.insertarAlumno(alumno);
	}
	
	public static String actualizarAlumno(String nombre, String run, int edad)
	{
		return ConsultaAlumno.UpdateAlumno(nombre, run, edad);
	}
	
	public static String modificarEstadoAlumno(Alumno alumno)
	{
		return ConsultaAlumno.UpdateAlumno_estado(alumno);
	}

}
