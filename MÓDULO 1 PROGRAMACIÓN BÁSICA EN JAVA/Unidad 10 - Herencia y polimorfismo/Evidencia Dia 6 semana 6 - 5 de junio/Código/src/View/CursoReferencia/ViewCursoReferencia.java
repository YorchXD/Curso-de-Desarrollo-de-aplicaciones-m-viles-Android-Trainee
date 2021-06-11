package View.CursoReferencia;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import Controller.AlumnoController;
import Controller.CursoReferenciaController;
import Controller.ProfesorController;
import Model.Alumno;
import Model.Curso;
import Model.Curso_Referencia;
import Model.Estado;
import Model.Profesor;
import Utilidades.Utilidades;

public class ViewCursoReferencia {
	


	
	public static void crear()
	{
		int id_curso =0;
		String letra, refProfesor;
		int anio = solicitarAnio();
		id_curso = solicitarSeleccionCurso(CursoReferenciaController.ObtenerCursosRegistrados());
		letra = SeleccionLetra();
		refProfesor = solicitarRun();


		
		
		if(ProfesorController.buscarProfesor(refProfesor)!=null) {		
			if(CursoReferenciaController.buscarCursoReferencia(letra,refProfesor,id_curso,anio)==0)
			{
				if(CursoReferenciaController.registrarCurso(letra,refProfesor,id_curso,anio))
				{
					System.out.println("\nCurso Promoción registrado correctamente\n");
				}else {
					System.out.println("\nNo se han guardado cambios, intentelo nuevamente.\n");
				}
				
			}else
			{
				System.out.println("\nEl curso promoción ya existe\n");
			}
		}else {
			System.out.println("\nEl profesor no se encuentra registrado.\n");
		}
	
		
	}
	
	public static void ver() 
	{
		int id_curso =0;
		String letra, refProfesor;
		int anio = solicitarAnio();
		Map<Integer, Curso_Referencia> cursos_referencia= new HashMap<>();
		
		cursos_referencia = CursoReferenciaController.verCursoReferencia(anio);
		
		if(cursos_referencia.size()>0)
		{
			listarCursosReferencia(cursos_referencia);
			
		}else
		{
			System.out.println("\nNo hay cursos promoción registrados\n");
		}
		
	}

	
	
	public static String SeleccionLetra() {
		
		Map<Integer, String> letra = new HashMap<>();
		letra.put(1, "A");
		letra.put(2, "B");
		letra.put(3, "C");
		letra.put(4, "D");

		
		System.out.println("\n*************Seleccion de letra Curso **************************");

		letra.forEach((k,v) -> System.out.println("- " + k+": " + v));
		
		System.out.println("******************************************************************");
		
		String opcion;
		boolean validar = false;
		String letra_aux = "";
		do {
			
			System.out.println("\nIngrese su opción: ");
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
			}
			else if (Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>4)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}else {
				
				letra_aux = letra.get(Integer.parseInt(opcion));
			}
			
		} while (!validar);
		
		
		return letra_aux;
		
	}
	
	public static int solicitarAnio() {
		int anio=0;
		String anio_aux="";
		boolean validar;
		
		do {
			System.out.print("Ingrese el año: ");
			anio_aux = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(anio_aux);

			
			if (!validar)
			{
				System.out.println("Debe ingresar un año valido. Favor de ingresar nuevamente.\n");
				validar=false;
				
			}else if (Integer.parseInt(anio_aux)>Calendar.getInstance().get(Calendar.YEAR)){
				
				System.out.println("No puede ingresar un año mayor al actual:  " + Calendar.getInstance().get(Calendar.YEAR) );
				validar=false;
				
			}else {
				anio = Integer.parseInt(anio_aux);
			}
			
		} while (!validar);
		
		
		return anio;
	}
	
	
	public static String solicitarRun()
	{
		String run;
		boolean validar;
		do
		{
			System.out.print("Ingrese el run, formato (XX.XXX.XXX-X): ");
			run = Utilidades.extracted().nextLine();
			validar = Utilidades.validarRun(run);
			if (!validar)
			{
				System.out.println("Debe ingresar un run valido. Favor de ingresar nuevamente.\n");
			}

		}
		while (!validar);

		run = run.replace(".", "");
		run = Utilidades.formatearRun(run);

		return run;
	}

	
	public static  Map<Integer, Profesor> obenerProfesores() {
		
		return CursoReferenciaController.ObtenerProfesoresRegistrados();
	}
	
	public static void listarProfesores(Map<Integer, Profesor> profesores)
	{

		for (Map.Entry<Integer,Profesor> profesor : profesores.entrySet())
		{
			System.out.println(profesor.getKey() + ". " + profesor.getValue().getNombre() + ", " + profesor.getValue().getRun() + ", " + profesor.getValue().getEspecialidad());
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
	
	public static void listarCursosReferencia(Map<Integer, Curso_Referencia> cursos_referencia)
	{
		for (Map.Entry<Integer,Curso_Referencia> cursos : cursos_referencia.entrySet())
		{
			System.out.println("Datos cursos promoción");
			System.out.println("- id : " + cursos.getValue().getId());
			System.out.println("\n*Datos Profesor Encargado");
			System.out.println("- Nombre: " + cursos.getValue().getProfesorEncargado().getNombre());
			System.out.println("- Run:  " + cursos.getValue().getProfesorEncargado().getRun());
			System.out.println("- Especialidad:  " + cursos.getValue().getProfesorEncargado().getEspecialidad());
			System.out.println("\n* Curso");
			System.out.println("- Nivel: " + cursos.getValue().getCurso().getNivel());
			System.out.println("- Tipo División Anual: " + cursos.getValue().getCurso().getTipoDivisionAnual().toString());
			System.out.println("- Estado:  " + cursos.getValue().getCurso().getEstado().toString());
			System.out.println("- Letra: " + cursos.getValue().getLetra());
			System.out.println("- Año : " + cursos.getValue().getAnio());
			
		}
	}
	
	public static void listarCursosReferenciaReducido(Map<Integer, Curso_Referencia> cursos_referencia)
	{
		for (Map.Entry<Integer,Curso_Referencia> curso_referencia : cursos_referencia.entrySet())
		{

			System.out.println(curso_referencia.getValue().getId() + ". " + curso_referencia.getValue().getCurso().getNivel() + ", "  + curso_referencia.getValue().getLetra() + ", " + curso_referencia.getValue().getAnio());
		}
		
	}
	
	
	public static int solicitarSeleccionCurso(Map<Integer, Curso> cursos)
	{
		
		if(!cursos.isEmpty())
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
				
				if(!validar )
				{
					System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
					opcion="-1";
				}
				else if(Integer.parseInt(opcion)<1)
				{
					System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");

				}

			}
			while(Integer.parseInt(opcion)<1  && cursos.get(Integer.parseInt(opcion)).getEstado()==Estado.DESHABILITADO);
			
			return Integer.parseInt(opcion);
		}
		else
		{
			System.out.println("\nNo existen cursos registrados.\n");
		}
		return -1;
	}
	
public static void asociarAlumno(Map<Integer, Curso_Referencia> cursos_ref) {
		
		System.out.println("\n------------------------------------------- ");
		System.out.println("\n	Opcion para asociar alumno a un curso: ");
		System.out.println("\n------------------------------------------- ");
		Alumno alumno = null;
		String run;
		listarCursosReferenciaReducido(cursos_ref);
		System.out.println("\n Seleccione el curso: "); 
		String opcion = Utilidades.extracted().nextLine();
		boolean validar = Utilidades.esNumero(opcion);
		
		if(!validar )
		{
			System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
			opcion="-1";
		}
		else if(Integer.parseInt(opcion)<1)
		{
			System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");

		}
		
		int id_cursoReferencia = Integer.parseInt(opcion);
		
		run = solicitarRun();
		alumno = AlumnoController.buscarAlumno(run);
		if (alumno != null)
		{
			
			if(CursoReferenciaController.registrarCursoReferenciaAlumno(id_cursoReferencia, run)) {
				
				System.out.println("\n\n Alumno registrado correctamente ");
			}else {
				System.out.println("\n\n El alumno no se ha podido registrar, intentelo nuevamente ");
			}
		}
		else
		{
			System.out.println("\n\n------------------------------------------- ");
			System.out.println("\n\n	El alumno no se encuentra registrado ");
			System.out.println("\n\n------------------------------------------- ");
		}
		
		
		
	}

public static void verAlumnos() {
	
	
	int anio = solicitarAnio();
	
	Map<Integer, Curso_Referencia> cursos_referencia= new HashMap<>();
	
	cursos_referencia = CursoReferenciaController.verCursoReferencia(anio);
	
	if(cursos_referencia.size()>0)
	{
		listarCursosReferenciaReducido(cursos_referencia);
		System.out.println("\n Seleccione el curso: "); 
		String opcion = Utilidades.extracted().nextLine();
		boolean validar = Utilidades.esNumero(opcion);
		
		if(!validar )
		{
			System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
			opcion="-1";
		}
		else if(Integer.parseInt(opcion)<1)
		{
			System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");

		}
		
		int id_cursoReferencia = Integer.parseInt(opcion);
		Map <Integer, Alumno> alumnos = CursoReferenciaController.verAlumnoAsociados(id_cursoReferencia);
		System.out.println("\n********************************************************");
		System.out.println("*              Alumnos registrados al curso            *");
		System.out.println("********************************************************");
		
		listarAlumnos(alumnos);
		
		System.out.println("********************************************************\n");
		
		
	}else
	{
		System.out.println("\nNo hay cursos promoción registrados\n");
	}
	
}


public static void listarAlumnos(Map<Integer, Alumno> Alumnos)
{
	for (Map.Entry<Integer,Alumno> alumno : Alumnos.entrySet())
	{

		System.out.println(alumno.getKey() + ".- " + alumno.getValue().getNombre() + ", "  + alumno.getValue().getRun());
	}
	
}	


}
