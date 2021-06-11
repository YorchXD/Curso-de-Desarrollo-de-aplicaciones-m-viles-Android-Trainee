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
	
	
	/**
	 * Tras obtener el run, este lo envia a la clase ConsultarAlumno para que busque al alumno segun el RUN
	 * @param run
	 * @return alumno con datos o null
	 */
	public static Alumno buscarAlumno(String run)
	{
		return ConsultaAlumno.consultarAlumno_run(run);
	}
	
	/**
	 * Tras tener los parametros principales del alumno, crea una instancia del alumno y le ordena a que se registre. El mensaje a devolver dependera de lo que
	 * devuelva el metodo registrarDatos que se encuentra en la instacia del alumno creada.
	 * @param nombre
	 * @param run
	 * @param edad
	 * @return "Se ha guardado exitosamente al alumno" o "No se pudo guardado al alumno"
	 */
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
	
	/**
	 * Tras obtener la instacia del alumno, este le ordena a que se actualice en la BD. El mensaje a devolver depnedera de lo que devuelve el metodo actualizarDatos
	 * que se encuentra en la instancia del alumno.
	 * @param alumno
	 * @return "Se ha modificado exitosamente el estado del alumno" o "No se pudo modificar el estado del alumno"
	 */
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
	
	/**
	 * Tras obtener la instancia de un alumno, cambia su estado. Si se encuentra HABILITADO, este cambia a DESHABILITADO y viceversa. Luego se ordena al metodo
	 * cambiarEstado que se encuentra en la instacia que registre este cambio en la BD y de acuerdo a su respuesta es el mensaje que se selecciona (true o false)
	 * @param alumno
	 * @return "El alumno se ha modificado correctamente" o "No se han guardado cambios, intentelo nuevamente"
	 */
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
	
	/**
	 * Tras obtener el RUN del alumno y del apoderado se procede a buscar al alumno y en caso de que el alumno no tenga asociado a un apoderado, se procede a buscar
	 * al usuario apoderado a traves del run. Posteriormente se asocia al apoderado en el alumno y se le da la instruccion al metodo de la instancia alumno llamado
	 * asociarApoderado, que registre la asociacion en la BD.
	 * @param runAlumno
	 * @param runApoderado
	 * @return "La asociacion entre el apoderado y el alumno se ha realizado exitosamente", "Hubo problemas para registrar al alumno con el apoderado. Por favor, intentelo nuevamente", 
	 * "El apoderado no se encuentra registrado" o "El apoderado ya se encuentra asociado con el alumno"
	 */
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
