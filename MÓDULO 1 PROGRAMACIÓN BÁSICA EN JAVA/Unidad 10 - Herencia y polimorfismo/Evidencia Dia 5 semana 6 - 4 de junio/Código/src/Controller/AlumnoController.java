package Controller;

import BD.ConsultaAlumno;
import BD.ConsultaApoderado;
import View.Alumno.ViewAlumno;
import Model.Alumno;
import Model.Apoderado;
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
	
	public static void asociarApoderado()
	{
		ViewAlumno.asociarApoderado();
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

		if(alumno.registrarDatos())
		{
			mensaje = "\nSe ha guardado exitosamente al alumno\n";
		}
		else
		{
			mensaje = "\nNo se pudo guardado al alumno\n";
		}
		return mensaje;
	}
	
	public static String actualizarAlumno(Alumno alumno)
	{
		String mensaje = "";
		if(alumno.actualizarDatos())
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
		String mensaje = null;
		if(alumno.getEstado().equals(Estado.HABILITADO))
		{
			alumno.setEstado(Estado.DESHABILITADO);
		}
		else
		{
			alumno.setEstado(Estado.HABILITADO);
		}
		
		if(alumno.cambiarEstado())
		{
			mensaje = "\nEl alumno se ha modificado correctamente\n";
		}
		else
		{
			mensaje = "\nNo se han guardado cambios, intentelo nuevamente.\n";
		}
		
		return mensaje;
	}
	
	public static String asociarApoderadoAlumno(String runAlumno, String runApoderado)
	{
		String mensaje = null;
		Alumno alumno = buscarAlumno(runAlumno);
		if(alumno.getApoderado()==null || (alumno.getApoderado()!=null && !alumno.getApoderado().getRun().equals(runApoderado)))
		{
			Apoderado apoderado = ConsultaApoderado.buscarApoderado(runApoderado);
			if(apoderado!=null)
			{
				alumno.setApoderado(apoderado);
				if(alumno.asociarApoderado())
				{
					mensaje = "\nLa asociacion entre el apoderado y el alumno se ha realizado exitosamente.\n";
				}
				else
				{
					mensaje="\nHubo problemas para registrar al alumno con el apoderado. Por favor, intentelo nuevamente.\n";
				}
			}
			else
			{
				mensaje="\nEl apoderado no se encuentra registrado\n";
			}
		}
		else
		{
			mensaje="\nEl apoderado ya se encuentra asociado con el alumno\n";
		}
		
		return mensaje;
	}

}
