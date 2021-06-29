package View.Unidad;

import java.util.Map;

import Controller.UnidadController;
import Model.Asignatura;
import Model.Curso;
import Model.Estado;
import Model.Tipo_Division_Anual;
import Model.Unidad;
import Utilidades.Utilidades;

public class ViewUnidad
{
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
		String opcion="0";
		if(!cursos.isEmpty())
		{
			boolean validar = false, validar2 = true;
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
				else if(Integer.parseInt(opcion)<0 
						|| (Integer.parseInt(opcion)>0 && cursos.get(Integer.parseInt(opcion))==null))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else if(cursos.get(Integer.parseInt(opcion))!=null && cursos.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO)
				{
					System.out.println("La opcion ingresada es un curso deshabilitado por lo cual no se puede asociar unidades. Favor ingrese una opcion segun las opciones que muestra el menu y cursos habilitados.\n\n");
				}
				else
				{
					validar2=false;
				}
			}
			while(validar2);			
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
		return Integer.parseInt(opcion);
	}
	
	public static void listarAsignaturas(Map<Integer, Asignatura> asignaturas)
	{
		for (Map.Entry<Integer,Asignatura> asignatura : asignaturas.entrySet())
		{
			System.out.println(asignatura.getKey() + ". " + asignatura.getValue().getNombre() + ", " + asignatura.getValue().getEstado());
		}
	}
	
	public static int solicitarSeleccionAsignatura(Map<Integer, Asignatura> asignaturas)
	{
		String opcion = "0";
		if(!asignaturas.isEmpty())
		{
			boolean validar = false, validar2 = true;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*           Menu de asignaturas registradas            *");
				System.out.println("********************************************************\n");
				System.out.println("NOTA: Solo se pueden seleccionar asignaturas habilitados\n");
				System.out.println("0. Volver al menu principal");
				listarAsignaturas(asignaturas);
				
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				}
				else if(Integer.parseInt(opcion)<0 
						|| (Integer.parseInt(opcion)>0 && asignaturas.get(Integer.parseInt(opcion))==null) 
						||(asignaturas.get(Integer.parseInt(opcion))!=null && asignaturas.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
				}
			}
			while(validar2);
		}
		else
		{
			System.out.println("\nNo existen asignaturas registradas.\n");
		}
		return Integer.parseInt(opcion);
	}
	
	
	public static void solicitarDivAnual(Tipo_Division_Anual tipoDivAnual)
	{
		if(tipoDivAnual==Tipo_Division_Anual.SEMESTRAL)
		{
			System.out.println("\n********************************************************");
			System.out.println("*              Menu de semestres                       *");
			System.out.println("********************************************************");
			System.out.println("Seleccione el semestre a que pertenece la unidad\n");
			for(int i = 1; i<=2; i++)
			{
				System.out.println(i + ". " + i + "� semestre");
			}
		}
		else if(tipoDivAnual == Tipo_Division_Anual.TRIMESTRAL)
		{
			System.out.println("\n********************************************************");
			System.out.println("*              Menu de semestres                       *");
			System.out.println("********************************************************");
			System.out.println("Seleccione el trimestre a que pertenece la unidad\n");
			for(int i = 1; i<=3; i++)
			{
				System.out.println(i + ". " + i + "� trimestre");
			}
			
		}
		System.out.println("********************************************************\n");
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static int solicitarOpcion(Tipo_Division_Anual tipoDivAnual)
	{
		String opcion = "1";
		int cantTipoDiv;
		boolean validar, validar2 = true;
		
		if(tipoDivAnual==Tipo_Division_Anual.SEMESTRAL)
		{
			cantTipoDiv=2;
		}
		else if(tipoDivAnual == Tipo_Division_Anual.TRIMESTRAL)
		{
			cantTipoDiv=3;
		}
		else
		{
			cantTipoDiv=1;
		}
		
		do
		{
			if(tipoDivAnual != Tipo_Division_Anual.ANUAL)
			{
				solicitarDivAnual(tipoDivAnual);
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				}
				else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>cantTipoDiv)
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
				}
			}
		}
		while(validar2);
		
		return Integer.parseInt(opcion);
	}
	
	
	public static int solicitarNumeroUnidad()
	{
		String numeroUnidad;
		do
		{
			System.out.println("\n\nNOTA: El numero de la unidad debe ser igual o superior a 1.");
			System.out.print("Ingrese el numero de la unidad: ");
			numeroUnidad = Utilidades.extracted().nextLine();
			if(Integer.parseInt(numeroUnidad)<1)
			{
				System.out.println("El numero de la unidad no debe ser inferior a 1. Favor ingrese un numero valido.");
			}
		}
		while(Integer.parseInt(numeroUnidad)<1);
		
		return Integer.parseInt(numeroUnidad);
	}
	
	public static void crear(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=0)
			{
				String nombre = Utilidades.solicitarNombre(Utilidades.UNIDAD);
				int divAnual = solicitarOpcion(curso.getTipoDivisionAnual());
				int numeroUnidad = solicitarNumeroUnidad();
				System.out.println(UnidadController.registrarUnidad(idAsignatura, idCurso, nombre, divAnual, numeroUnidad));
			}
		}
	}
	
	public static void verListadoUnidades(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				mostrarlistarUnidades(asignatura.getUnidades());
			}
		}
	}
	
	public static void mostrarlistarUnidades(Map<Integer, Unidad> unidades)
	{
		System.out.println("\n********************************************************");
		System.out.println("*                 Unidades registradas                 *");
		System.out.println("********************************************************");
		if(unidades.size()>0)
		{
			listarUnidades(unidades);
		}
		else
		{
			System.out.println("No existen unidades registradas para esta asignatura.");
		}
		System.out.println("********************************************************\n");
	}
	
	public static void listarUnidades(Map<Integer, Unidad> unidades)
	{
		for (Map.Entry<Integer,Unidad> unidad : unidades.entrySet())
		{
			unidad.getValue().mostrarDatos();
			System.out.println(unidad.getKey() + ". Unidad " + unidad.getValue().getNumero_unidad() + ": " + unidad.getValue().getNombre() + ", " + unidad.getValue().getEstado());
		}
	}

	public static void modificarUnidad(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				int idUnidad = solicitarSeleccionUnidad(asignatura.getUnidades());
				if(idUnidad!=0)
				{
					Unidad unidad = asignatura.getUnidades().get(idUnidad);
					mostrarDatosModUnidad(unidad,curso);
					String nombre = Utilidades.solicitarNombre(Utilidades.UNIDAD);
					int divAnual = solicitarOpcion(curso.getTipoDivisionAnual());
					int numeroUnidad = solicitarNumeroUnidad();
					System.out.println(UnidadController.actualizarUnidad(unidad, nombre, divAnual, numeroUnidad));
				}
			}
		}
	}

	public static int solicitarSeleccionUnidad(Map<Integer, Unidad> unidades)
	{
		String opcion="0";
		if(!unidades.isEmpty())
		{
			boolean validar = false, validar2 = true;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*             Menu de unidades registradas             *");
				System.out.println("********************************************************\n");
				System.out.println("0. Volver al menu principal");
				listarUnidades(unidades);
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");
				
				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				}
				else if(Integer.parseInt(opcion)>0 && unidades.get(Integer.parseInt(opcion))==null)
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
				}
			}
			while(validar2);
		}
		else
		{
			System.out.println("\nNo existen unidaddes registradas.\n");
		}
		
		return Integer.parseInt(opcion);
	}

	public static void cambiarEstadoUnidad(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				int idUnidad = solicitarSeleccionUnidad(asignatura.getUnidades());
				if(idUnidad!=0)
				{
					Unidad unidad = asignatura.getUnidades().get(idUnidad);
					mostrarDatosModUnidad(unidad,curso);
					if(Utilidades.confirmacionCambiarEstado(unidad.getEstado(), Utilidades.UNIDAD))
					{
						System.out.println(UnidadController.actualizarEstadoUnidad(unidad));
					}
				}
			}
		}
	}
	
	public static void mostrarDatosModUnidad(Unidad unidad, Curso curso) {
		System.out.println("\n********************************************************");
		System.out.println("*            Datos de la unidad a modificar            *");
		System.out.println("********************************************************");
		System.out.println(unidad.mostrarDatos());
		if(curso.getTipoDivisionAnual() == Tipo_Division_Anual.TRIMESTRAL)
		{
			System.out.println("Trimestre en que se imparte la unidad: " + unidad.getDivision_anual() + "� trimestre");
		}
		else if(curso.getTipoDivisionAnual() == Tipo_Division_Anual.SEMESTRAL)
		{
			System.out.println("Semestre en que se imparte la unidad: " + unidad.getDivision_anual()  + "� semestre");
		}
		else
		{
			System.out.println("La unidad se imparte dentro del anio dado que la asignatura es anual");
		}
		System.out.println("********************************************************\n");
		
	}
}
