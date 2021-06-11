package View.Curso;

import Utilidades.Utilidades;

import java.util.Map;

import Controller.AlumnoController;
import Controller.CursoController;
import Model.Alumno;
import Model.Asignatura;
import Model.Curso;
import Model.Curso_Referencia;
import Model.Estado;
import Model.Nivel;
import Model.Tipo_Division_Anual;

public class ViewCurso
{
	public static void nivelMenu()
	{
		Nivel[] cursos = Nivel.values();
		int cont = 1;
		System.out.println("\n********************************************************");
		System.out.println("*       Seleccione el nivel del curso a ingresar       *");
		System.out.println("********************************************************\n");
		for (Nivel nivel : cursos)
		{
			String [] nivelAux = nivel.toString().split("_");
			System.out.println(cont + ". " + nivelAux[0] + " " + nivelAux[1]);
			cont++;
		}
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static boolean confirmacionCambiarEstado(Estado estado)
	{
		String opcion;
		boolean validar = false;
		do
		{
			System.out.print("El estado actual es: " + estado + ". �Desea cambiar el estado?\n1. Si\n2. No\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>2)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}

		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>2);
		
		if(Integer.parseInt(opcion)==1)
		{
			return true;
		}
		return false;
	}
	
	
	public static Nivel solicitarNivel()
	{
		String opcion;
		boolean validar;
		do
		{
			nivelMenu();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Nivel.values().length)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Nivel.values().length);
		
		return Nivel.getNivel(Integer.parseInt(opcion));
	}
	
	
	public static void menuTipoDivisionAnual()
	{
		Tipo_Division_Anual[] tipoDivisionAnuales = Tipo_Division_Anual.values();
		int cont = 1;
		System.out.println("\n\nSeleccione el tipo de curso a ingresar: ");
		for (Tipo_Division_Anual tipoDivAnual : tipoDivisionAnuales)
		{
			System.out.println(cont + ". " + tipoDivAnual);
			cont++;
		}
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static Tipo_Division_Anual solicitarTipoDivisionAnual()
	{
		String opcion;
		boolean validar;
		do
		{
			menuTipoDivisionAnual();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Tipo_Division_Anual.values().length)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>Tipo_Division_Anual.values().length);

		return Tipo_Division_Anual.values()[Integer.parseInt(opcion)-1];
	}
	
	
	public static void crear()
	{
		Nivel nivel = ViewCurso.solicitarNivel();
		Tipo_Division_Anual tipoDivisionAnual = ViewCurso.solicitarTipoDivisionAnual();
		if(CursoController.buscarCurso(nivel, tipoDivisionAnual)==null)
		{
			if(CursoController.registrarCurso(nivel, tipoDivisionAnual))
			{
				System.out.println("\nCurso registrado correctamente\n");
			}
			else
			{
				System.out.println("\nNo se han guardado cambios, intentelo nuevamente.\n");
			}
		}
		else
		{
			System.out.println("\nEl curso ya existe\n");
		}
	}
	
	public static void modificar()
	{
		Nivel nivel = ViewCurso.solicitarNivel();
		Tipo_Division_Anual tipoDivisionAnual = ViewCurso.solicitarTipoDivisionAnual();
		Curso curso = CursoController.buscarCurso(nivel, tipoDivisionAnual);
		if(curso!=null)
		{
			boolean confirmar = confirmacionCambiarEstado(curso.getEstado());
			if(confirmar)
			{
				if(CursoController.actualizarEstadoCurso(curso))
				{
					System.out.println("\nEl curso se ha modificado correctamente\n");
				}
				else
				{
					System.out.println("\nNo se han guardado cambios, intentelo nuevamente.\n");
				}
			}
		}
		else
		{
			System.out.println("\nEl curso no se encuentra registrado\n");
		}
	}
	
	public static void ver()
	{
		Nivel nivel = ViewCurso.solicitarNivel();
		Tipo_Division_Anual tipoDivisionAnual = ViewCurso.solicitarTipoDivisionAnual();
		Curso curso = CursoController.buscarCurso(nivel, tipoDivisionAnual);
		if(curso!=null)
		{
			curso.mostrarDatos();
		}
		else
		{
			System.out.println("\nEl curso no se encuentra registrado\n");
		}
	}

	public static void verListadoCurso(Map<Integer, Curso> cursos)
	{
		if(!cursos.isEmpty())
		{
			System.out.println("\n********************************************************");
			System.out.println("*                   Cursos registrados                 *");
			System.out.println("********************************************************\n");
			listarCursos(cursos);
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
	}
	
	public static void asociarAsignatura(Map<Integer, Curso> cursos, Map<Integer, String> asignaturas)
	{
		if(cursos.size()!=0 && asignaturas.size()!=0) {
			
			int idCurso = solicitarSeleccionCurso(cursos);
			
			if(idCurso!=0) 
			{
				int idAsignatura = solicitarSeleccionAsignatura(asignaturas);
				
				if(idAsignatura!=0) {
					
					System.out.println(CursoController.registrarAsociacionCurso(idCurso, idAsignatura));	
				}
				
			}
			
		}else 
		{
			System.out.println("No hay cursos o asignaturas registradas");
		}
	
		
	}
	
	public static void listarCursos(Map<Integer, Curso> cursos)
	{
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			String [] cursoAux = curso.getValue().getNivel().toString().split("_");
			System.out.println(curso.getKey() + ". " + cursoAux[0] + " " + cursoAux[1] + ", " + curso.getValue().getTipoDivisionAnual() + ", " + curso.getValue().getEstado());
		}
	}
	
	public static int solicitarSeleccionCurso(Map<Integer, Curso> cursos)
	{
		
		if(!cursos.isEmpty())
		{
			String opcion;
			boolean validar = false;
			boolean validar2 = true;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*              Menu de cursos registradas              *");
				System.out.println("********************************************************");
				System.out.println("NOTA: Solo se pueden seleccionar cursos habilitados\n");
				System.out.println("0. Volver al menu principal");
				listarCursos(cursos);
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				}
				else if(Integer.parseInt(opcion)<0)
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else if(cursos.get(Integer.parseInt(opcion))!=null && cursos.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO)
				{
					System.out.println("La opcion ingresada es un curso deshabilitado por lo cual no se puede asociar asignaturas. Favor ingrese una opcion segun las opciones que muestra el menu y cursos habilitados.\n\n");
				}else
				{
					validar2 =false;
				}
			}
			while(validar2);
			
			return Integer.parseInt(opcion);
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
		return -1;
	}
	
	public static void listarAsignaturas(Map<Integer, String> asignaturas)
	{
		for (Map.Entry<Integer,String> asignatura : asignaturas.entrySet())
		{
			System.out.println(asignatura.getKey() + ". " + asignatura.getValue());
		}
	}
	
	public static int solicitarSeleccionAsignatura(Map<Integer, String> asignaturas)
	{
		
		if(!asignaturas.isEmpty())
		{
			String opcion;
			boolean validar = false;
			boolean validar2 = true;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*           Menu de asignaturas registradas            *");
				System.out.println("********************************************************\n");
				System.out.println("0. Volver al menu principal");
				listarAsignaturas(asignaturas);
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
					opcion="-1";

				}
				else if((Integer.parseInt(opcion)<0 || (Integer.parseInt(opcion)>0 && asignaturas.get(Integer.parseInt(opcion))==null)))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");

				}else 
				{
					validar2=false;
				}

			}
			while(validar2);
			
			return Integer.parseInt(opcion);

		}
		else
		{
			System.out.println("\nNo existen asignaturas registradas.\n");
		}
		return -1;
	}
	
	public static void verListadoAsignaturasCurso(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		Map<Integer, Asignatura> asignaturas = cursos.get(idCurso).getAsignaturas();
		if(!asignaturas.isEmpty())
		{
			
			System.out.println("\n********************************************************");
			System.out.println("*                Asignaturas registradas               *");
			System.out.println("********************************************************");
			String [] nombreCurso = cursos.get(idCurso).getNivel().toString().split("_");
			System.out.println("Las asignaturas asociadas al curso " +  nombreCurso[0] + " " + nombreCurso[1] + " son: \n");
			listarAsignaturasAsociadas(asignaturas);
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\nNo existen asignaturas asociadas al curso.\n");
		}
	}
	
	public static void listarAsignaturasAsociadas(Map<Integer, Asignatura> asignaturas)
	{
		for (Map.Entry<Integer,Asignatura> asignatura : asignaturas.entrySet())
		{
			System.out.println(asignatura.getKey() + ". "  + asignatura.getValue().getNombre() + ", " + asignatura.getValue().getEstado());
		}
	}
	
	
	public static int solicitarSeleccionAsignaturaAsociada(Map<Integer, Asignatura> asignaturas)
	{
		
		if(!asignaturas.isEmpty())
		{
			String opcion;
			boolean validar = false;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*             Menu de asignaturas asociadas            *");
				System.out.println("********************************************************\n");
				System.out.println("0. Volver al menu principal");
				listarAsignaturasAsociadas(asignaturas);
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
					opcion="-1";
				}
				else if(Integer.parseInt(opcion)<0 && asignaturas.get(Integer.parseInt(opcion))==null)
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				
			}
			while(Integer.parseInt(opcion)<0 && asignaturas.get(Integer.parseInt(opcion))==null);
			
			return Integer.parseInt(opcion);

		}
		else
		{
			System.out.println("\nNo existen asignaturas registradas.\n");
		}
		return -1;
	}
	
	
	public static void cambiarEstadoAsignatura(Map<Integer, Curso> cursos)
	{
		if(!cursos.isEmpty())
		{
			int idCurso = solicitarSeleccionCurso(cursos);
			if(idCurso!=-1 && idCurso!=cursos.size()+1)
			{
				int idAsignatura = solicitarSeleccionAsignaturaAsociada(cursos.get(idCurso).getAsignaturas());
				if(idAsignatura!=-1 && idAsignatura!=0)
				{
					Asignatura asignatura = cursos.get(idCurso).getAsignaturas().get(idAsignatura);
					if(confirmacionCambiarEstado(asignatura.getEstado()))
					{
						System.out.println(CursoController.cambiarEstadoAsignatura(asignatura));
					}
				}
			}
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
	}

	

	
}
