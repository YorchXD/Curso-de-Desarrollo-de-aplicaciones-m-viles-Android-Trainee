package View.CursoReferencia;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Controller.AlumnoController;
import Controller.CursoReferenciaController;
import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import Model.Estado;
import Model.LetraCurso;
import Model.Profesor;
import Utilidades.Utilidades;

public class ViewCursoReferencia
{

	public static void crear(Map<Integer, LetraCurso> letras, Map<Integer, Curso> cursos, Map<String, Profesor> profesores)
	{
		int anio = solicitarAnio();
		int id_curso = solicitarSeleccionCurso(cursos);
		LetraCurso letra = SeleccionLetra(letras);
		Profesor profesorEncargado = SeleccionarProfesor(profesores);
		System.out.println(CursoReferenciaController.registrarCurso(letra, profesorEncargado, id_curso, anio));
		
	}

	public static void ver()
	{
		int anio = solicitarAnio();
		Map<Integer, Curso_Referencia> cursos_referencia = new HashMap<>();
		cursos_referencia = CursoReferenciaController.buscarCursoReferencia(anio);

		if (cursos_referencia.size() > 0)
		{
			listarCursosReferencia(cursos_referencia, anio);
		}
		else
		{
			System.out.println("\nNo hay cursos promocion registrados\n");
		}
	}

	public static LetraCurso SeleccionLetra(Map<Integer, LetraCurso> letras)
	{
		System.out.println("\n********************************************************");
		System.out.println("*               Seleccionar letra de curso             *");
		System.out.println("********************************************************\n");
		letras.forEach((key, value) -> System.out.println(key + ". " + value.getLetra()));
		System.out.println("********************************************************\n");

		String opcion;
		boolean validar = false, validar2=false;
		LetraCurso letraAux=null;
		do
		{
			System.out.println("\nIngrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);

			if (!validar)
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
			}
			else if (letras.get(Integer.parseInt(opcion))==null)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
			else
			{
				letraAux = letras.get(Integer.parseInt(opcion));
				validar2=true;
			}
		}
		while (!validar2);

		return letraAux;
	}
	
	public static Profesor SeleccionarProfesor(Map<String, Profesor> profesores)
	{
		System.out.println("\n********************************************************");
		System.out.println("*                  Seleccionar profesor                *");
		System.out.println("********************************************************\n");
		profesores.forEach((key, value) -> System.out.println("RUN: " + key + ", Nombre: " + value.getNombre()));
		System.out.println("********************************************************\n");

		boolean validar = false;
		Profesor profesor=null;
		do
		{
			String run = Utilidades.solicitarRun(Utilidades.PROFESOR);

			if (profesores.get(run)==null)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
			else
			{
				profesor = profesores.get(run);
				validar=true;
			}
		}
		while (!validar);

		return profesor;
	}

	public static int solicitarAnio()
	{
		int anio = 0;
		String anio_aux = "";
		boolean validar;

		do
		{
			System.out.print("Ingrese el anio: ");
			anio_aux = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(anio_aux);

			if (!validar)
			{
				System.out.println("Debe ingresar un anio valido. Favor de ingresar nuevamente.\n");
				validar = false;

			}
			else if (Integer.parseInt(anio_aux) > Calendar.getInstance().get(Calendar.YEAR))
			{

				System.out.println(
						"No puede ingresar un anio mayor al actual:  " + Calendar.getInstance().get(Calendar.YEAR));
				validar = false;

			}
			else
			{
				anio = Integer.parseInt(anio_aux);
			}

		}
		while (!validar);

		return anio;
	}

	public static void listarProfesores(Map<Integer, Profesor> profesores)
	{

		for (Map.Entry<Integer, Profesor> profesor : profesores.entrySet())
		{
			System.out.println(profesor.getKey() + ". " + profesor.getValue().getNombre() + ", "
					+ profesor.getValue().getRun());
		}
	}

	public static void listarCursos(Map<Integer, Curso> cursos)
	{
		for (Map.Entry<Integer, Curso> curso : cursos.entrySet())
		{
			String[] cursoAux = curso.getValue().getNivel().toString().split("_");
			System.out.println(curso.getKey() + ". " + cursoAux[0] + " " + cursoAux[1] + ", "
					+ curso.getValue().getTipoDivisionAnual() + ", " + curso.getValue().getEstado());
		}
	}

	public static void listarCursosReferencia(Map<Integer, Curso_Referencia> cursos_referencia, int aniopromocion)
	{
		System.out.println("\n********************************************************");
		System.out.println("*              Listado de los cursos "+ aniopromocion +"              *");
		System.out.println("********************************************************\n");
		for (Map.Entry<Integer, Curso_Referencia> cursos : cursos_referencia.entrySet())
		{
			System.out.println("\n********************************************************");
			System.out.println("* Curso");
			System.out.println("- id : " + cursos.getValue().getId());
			System.out.println("- Nivel: " + cursos.getValue().getCurso().getNivel());
			System.out.println("- Tipo Division Anual: " + cursos.getValue().getCurso().getTipoDivisionAnual().toString());
			System.out.println("- Estado:  " + cursos.getValue().getCurso().getEstado().toString());
			System.out.println("- Letra: " + cursos.getValue().getLetra().getLetra());
			System.out.println("- Anio : " + cursos.getValue().getAnio());
			System.out.println("\n*Datos Profesor Encargado");
			System.out.println("- Nombre: " + cursos.getValue().getProfesorEncargado().getNombre());
			System.out.println("- Run:  " + cursos.getValue().getProfesorEncargado().getRun());
			System.out.println("********************************************************\n");
		}
	}

	public static void listarCursosReferenciaReducido(Map<Integer, Curso_Referencia> cursos_referencia)
	{
		for (Map.Entry<Integer, Curso_Referencia> curso_referencia : cursos_referencia.entrySet())
		{
			System.out.println(curso_referencia.getValue().getId() + ". "
					+ curso_referencia.getValue().getCurso().getNivel() + ", " + curso_referencia.getValue().getLetra()
					+ ", " + curso_referencia.getValue().getAnio());
		}

	}

	public static int solicitarSeleccionCurso(Map<Integer, Curso> cursos)
	{

		if (!cursos.isEmpty())
		{
			String opcion;
			boolean validar = false;
			do
			{
				System.out.println("\n********************************************************");
				System.out.println("*              Menu de cursos registrados              *");
				System.out.println("********************************************************");
				System.out.println("NOTA: Solo se pueden seleccionar cursos habilitados\n");
				listarCursos(cursos);
				System.out.println("********************************************************\n");
				System.out.print("\nIngrese su opcion: ");

				opcion = Utilidades.extracted().nextLine();
				validar = Utilidades.esNumero(opcion);

				if (!validar)
				{
					System.out.println(
							"Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
					opcion = "-1";
				}
				else if (Integer.parseInt(opcion) < 1)
				{
					System.out.println(
							"La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");

				}

			}
			while (Integer.parseInt(opcion) < 1
					&& cursos.get(Integer.parseInt(opcion)).getEstado() == Estado.DESHABILITADO);

			return Integer.parseInt(opcion);
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
		return -1;
	}
	
	public static Curso_Referencia solicitarCursoPromocion(Map<Integer, Curso_Referencia> cursos_ref)
	{
		boolean validar2 = true;
		String opcion;
		do
		{
			System.out.println("\n********************************************************");
			System.out.println("*              Seleccionar curso promocion             *");
			System.out.println("********************************************************\n");
			listarCursosReferenciaReducido(cursos_ref);
			System.out.println("********************************************************\n");
			System.out.print("Ingrese su opcion: ");
			opcion = Utilidades.extracted().nextLine();
			boolean validar = Utilidades.esNumero(opcion);

			if (!validar)
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
			}
			else if (cursos_ref.get(Integer.parseInt(opcion))==null)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
			else
			{
				validar2=false;
			}
		}
		while(validar2);
		return cursos_ref.get(Integer.parseInt(opcion));
	}

	public static void asociarAlumno()
	{
		int anio = solicitarAnio();
		Map<Integer, Curso_Referencia> cursos_ref = CursoReferenciaController.buscarCursoReferencia(anio);
		Curso_Referencia cursoReferencia = solicitarCursoPromocion(cursos_ref);
		String run = Utilidades.solicitarRun(Utilidades.ALUMNO);
		Alumno alumno = AlumnoController.buscarAlumno(run);
		if (alumno != null)
		{
			System.out.println(CursoReferenciaController.registrarCursoReferenciaAlumno(cursoReferencia, run));
		}
		else
		{
			System.out.println("\nEl alumno no se encuentra registrado\n");
		}
	}

	public static void verAlumnos()
	{
		int anio = solicitarAnio();
		Map<Integer, Curso_Referencia> cursos_referencia = CursoReferenciaController.buscarCursoReferencia(anio);

		if (cursos_referencia.size() > 0)
		{
			Curso_Referencia cursoReferencia = solicitarCursoPromocion(cursos_referencia);
			Map<Integer, Alumno> alumnos = CursoReferenciaController.verAlumnoAsociados(cursoReferencia);
			System.out.println("\n********************************************************");
			System.out.println("*              Alumnos registrados al curso            *");
			System.out.println("********************************************************");
			listarAlumnos(alumnos);
			System.out.println("********************************************************\n");
		}
		else
		{
			System.out.println("\nNo hay cursos promocion registrados\n");
		}
	}

	public static void listarAlumnos(Map<Integer, Alumno> Alumnos)
	{
		for (Map.Entry<Integer, Alumno> alumno : Alumnos.entrySet())
		{
			System.out.println(alumno.getKey() + ".- " + alumno.getValue().getNombre() + ", " + alumno.getValue().getRun());
		}

	}

}
