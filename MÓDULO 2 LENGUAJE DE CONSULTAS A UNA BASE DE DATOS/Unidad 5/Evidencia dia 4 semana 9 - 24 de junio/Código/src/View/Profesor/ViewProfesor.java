package View.Profesor;

import Utilidades.Utilidades;

import java.util.HashMap;
import java.util.Map;

import Controller.ProfesorController;
import Model.Especialidad;
import Model.Profesor;

public class ViewProfesor
{
	public static Map<Integer, Especialidad> solicitarEspecialidad(Map<Integer, Especialidad> listadoEspecialidades)
	{
		String opcion;
		boolean validar = false, validar2=true;
		Map<Integer, Especialidad> especialidades=new HashMap<>();
		do
		{
			System.out.println("\n********************************************************");
			System.out.println("*              Seleccionar especialidades              *");
			System.out.println("********************************************************\n");
			System.out.println("Nota: debe seleccionar al menos una especialidad");
			System.out.println("0. Terminar");
			listadoEspecialidades.forEach((key, value) -> System.out.println(key + ". " + value.getNombre()));
			System.out.println("********************************************************\n");
			
			System.out.print("\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);

			if (!validar)
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida.\n\n");
			}
			else if(Integer.parseInt(opcion)==0 && especialidades.size()==0)
			{
				System.out.println("Recuerde que debe seleccionar al menos una especialidad para el profesor");
			}
			else if(Integer.parseInt(opcion)==0 && listadoEspecialidades.size()!=0)
			{
				validar2=false;
			}
			else if (listadoEspecialidades.get(Integer.parseInt(opcion))==null)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
			
			else
			{
				Especialidad especialidad = listadoEspecialidades.get(Integer.parseInt(opcion));
				if(especialidades.get(especialidad.getId())==null)
				{
					especialidades.put(especialidad.getId(), especialidad);
					if(!Utilidades.continuar(Utilidades.INGRESANDOESPECIALIDADESPROFESOR))
					{
						validar2=false;
					}
				}
				else
				{
					System.out.println("No se pueden repetir las especialidades. Seleccione otra especialidad u ingrese un cero para finalizar el registro de especialidades ");
				}			
			}
		}
		while (validar2);

		return especialidades;
	}
	
	public static void crear(Map<Integer, Especialidad> listadoEspecialidades)
	{
		System.out.println("\n********************************************************");
		System.out.println("*                    Crear profesor                    *");
		System.out.println("********************************************************\n");
	
		
		String run = Utilidades.solicitarRun(Utilidades.PROFESOR);
		String nombre = Utilidades.solicitarNombre(Utilidades.PROFESOR);
		String email = Utilidades.solicitarEmail(Utilidades.PROFESOR);
		String clave = Utilidades.solicitarClave(Utilidades.PROFESOR);
		Map<Integer, Especialidad> especialidad = solicitarEspecialidad(listadoEspecialidades);
		
		System.out.println("\n********************************************************");
		System.out.println(ProfesorController.registrarProfesor(nombre, run, email, clave, especialidad));
		System.out.println("********************************************************\n");
	}

	public static void ver()
	{		
		Profesor profesor = null;
		String run = Utilidades.solicitarRun(Utilidades.PROFESOR);
		
		profesor = ProfesorController.buscarProfesor(run);
		if (profesor != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del profesor                 *");
			System.out.println("********************************************************\n");
			System.out.println(profesor.mostrarDatos());
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void modificar()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                   Modificar profesor                 *");
		System.out.println("********************************************************\n");

		String run = Utilidades.solicitarRun(Utilidades.PROFESOR);
		Profesor profesor = ProfesorController.buscarProfesor(run);

		if (profesor != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del profesor                 *");
			System.out.println("********************************************************\n");
			System.out.println(profesor.mostrarDatos());
			System.out.println("********************************************************\n");
			
			String nombre = Utilidades.solicitarNombre(Utilidades.PROFESOR);
			String email = Utilidades.solicitarEmail(Utilidades.PROFESOR);
			String clave = Utilidades.solicitarClave(Utilidades.PROFESOR);			
			Boolean confirmacion = Utilidades.confirmacionModificarDatos(Utilidades.PROFESOR);

			if (confirmacion)
			{
				System.out.println(ProfesorController.actualizarProfesor(profesor, nombre, email, clave));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}

	public static void cambiarEstado()
	{
		System.out.println("\n********************************************************");
		System.out.println("*         Habilitar o Deshabilitar un Profesor         *");
		System.out.println("********************************************************\n");

		Profesor profesor = null;
		Boolean confirmacion = false;

		String run = Utilidades.solicitarRun(Utilidades.PROFESOR);
		profesor = ProfesorController.buscarProfesor(run);

		if (profesor != null)
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Datos del profesor                 *");
			System.out.println("********************************************************\n");
			System.out.println(profesor.mostrarDatos());
			System.out.println("********************************************************\n");

			confirmacion = Utilidades.confirmacionCambiarEstado(profesor.getEstado(), Utilidades.PROFESOR);

			if (confirmacion)
			{
				System.out.println(ProfesorController.cambiarEstadoProfesor(profesor));
			}
		}
		else
		{
			System.out.println("\n********************************************************");
			System.out.println("*       El profesor no se encuentra registrado         *");
			System.out.println("********************************************************\n");
		}
	}
}
