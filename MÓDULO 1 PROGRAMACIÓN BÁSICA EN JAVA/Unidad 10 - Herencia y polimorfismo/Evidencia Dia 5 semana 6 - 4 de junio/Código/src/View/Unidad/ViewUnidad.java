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
		
		if(!cursos.isEmpty())
		{
			String opcion;
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
					opcion="-1";
				}
				else if(Integer.parseInt(opcion)<0 || Integer.parseInt(opcion)>cursos.size())
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else if(cursos.get(Integer.parseInt(opcion))!=null && cursos.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO)
				{
					System.out.println("La opcion ingresada es un curso deshabilitado por lo cual no se puede asociar asignaturas. Favor ingrese una opcion segun las opciones que muestra el menu y cursos habilitados.\n\n");
				}
				else
				{
					validar2=false;
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
	
	public static void listarAsignaturas(Map<Integer, Asignatura> asignaturas)
	{
		for (Map.Entry<Integer,Asignatura> asignatura : asignaturas.entrySet())
		{
			System.out.println(asignatura.getKey() + ". " + asignatura.getValue().getNombre());
		}
	}
	
	public static int solicitarSeleccionAsignatura(Map<Integer, Asignatura> asignaturas)
	{
		
		if(!asignaturas.isEmpty())
		{
			String opcion;
			boolean validar = false, validar2 = true;
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
				else if((Integer.parseInt(opcion)>0 && asignaturas.get(Integer.parseInt(opcion))==null) ||(asignaturas.get(Integer.parseInt(opcion))!=null && asignaturas.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
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
	
	public static String solicitarNombre()
	{
		String nombre;
		System.out.print("Ingrese el nombre de la unidad: ");
		nombre = Utilidades.extracted().nextLine();
		return nombre;
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
				System.out.println(i + ". " + i + "° semestre");
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
				System.out.println(i + ". " + i + "° trimestre");
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
					opcion="-1";
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
			System.out.println("NOTA: El numero de la unidad debe ser igual o superior a 1.");
			System.out.print("Ingrese el nombre de la unidad: ");
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
		if(idCurso!=-1 && idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=-1 && idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				String nombre = solicitarNombre();
				int divAnual = solicitarOpcion(cursos.get(idCurso).getTipoDivisionAnual());
				int numeroUnidad = solicitarNumeroUnidad();
				System.out.println(UnidadController.registrarUnidad(asignatura, nombre, divAnual, numeroUnidad));
			}
		}
	}
	
	public static void verListadoUnidades(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=-1 && idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=-1 && idAsignatura!=0)
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
			unidad.getValue().mostrarDatos(null);
			System.out.println(unidad.getKey() + ". Unidad " + unidad.getValue().getNumero_unidad() + ": " + unidad.getValue().getNombre() + ", " + unidad.getValue().getEstado());
		}
	}

	public static void modificarUnidad(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=-1 && idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=-1 && idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				int idUnidad = solicitarSeleccionUnidad(asignatura.getUnidades());
				if(idUnidad!=-1 && idUnidad!=0)
				{
					Unidad unidad = asignatura.getUnidades().get(idUnidad);
					unidad.mostrarDatos(curso.getTipoDivisionAnual());
					String nombre = solicitarNombre();
					int divAnual = solicitarOpcion(cursos.get(idCurso).getTipoDivisionAnual());
					int numeroUnidad = solicitarNumeroUnidad();
					System.out.println(UnidadController.actualizarUnidad(unidad, nombre, divAnual, numeroUnidad));
				}
			}
		}
	}

	private static int solicitarSeleccionUnidad(Map<Integer, Unidad> unidades)
	{
		if(!unidades.isEmpty())
		{
			String opcion;
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
					opcion="-1";
				}
				else if((Integer.parseInt(opcion)>0 && unidades.get(Integer.parseInt(opcion))==null) ||(unidades.get(Integer.parseInt(opcion))!=null && unidades.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
				}
			}
			while(validar2);
			
			return Integer.parseInt(opcion);
		}
		else
		{
			System.out.println("\nNo existen unidaddes registradas.\n");
		}
		return -1;
	}

	public static void cambiarEstadoUnidad(Map<Integer, Curso> cursos)
	{
		int idCurso = solicitarSeleccionCurso(cursos);
		if(idCurso!=-1 && idCurso!=0)
		{
			Curso curso = cursos.get(idCurso);
			int idAsignatura = solicitarSeleccionAsignatura(curso.getAsignaturas());
			if(idAsignatura!=-1 && idAsignatura!=0)
			{
				Asignatura asignatura = curso.getAsignaturas().get(idAsignatura);
				int idUnidad = solicitarSeleccionUnidad2(asignatura.getUnidades());
				if(idUnidad!=-1 && idUnidad!=0)
				{
					Unidad unidad = asignatura.getUnidades().get(idUnidad);
					unidad.mostrarDatos(curso.getTipoDivisionAnual());
					if(confirmacionCambiarEstado(unidad.getEstado()))
					{
						System.out.println(UnidadController.actualizarEstadoUnidad(unidad));
					}
					
				}
			}
		}
	}
	
	public static boolean confirmacionCambiarEstado(Estado estado)
	{
		String opcion;
		boolean validar = false;
		do
		{
			System.out.print("El estado actual es: " + estado + ". ¿Desea cambiar el estado?\n1. Si\n2. No\nIngrese su opcion: ");
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
	
	public static int solicitarSeleccionUnidad2(Map<Integer, Unidad> unidades)
	{
		if(!unidades.isEmpty())
		{
			String opcion;
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
					opcion="-1";
				}
				else if((Integer.parseInt(opcion)>0 && unidades.get(Integer.parseInt(opcion))==null))
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
				}
				else
				{
					validar2 = false;
				}
			}
			while(validar2);
			
			return Integer.parseInt(opcion);
		}
		else
		{
			System.out.println("\nNo existen unidaddes registradas.\n");
		}
		return -1;
	}
}
