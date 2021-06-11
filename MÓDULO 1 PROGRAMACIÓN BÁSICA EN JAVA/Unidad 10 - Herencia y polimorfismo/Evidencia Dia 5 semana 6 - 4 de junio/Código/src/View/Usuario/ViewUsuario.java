package View.Usuario;

import Utilidades.Utilidades;
import Controller.CursoController;
import Controller.AlumnoController;
import Controller.ApoderadoController;
import Controller.AsignaturaController;
import Controller.ProfesorController;
import Controller.UnidadController;

public class ViewUsuario
{
	/*Opciones administrador*/
	public static void menuAdministrador()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                   Menu Administrador                 *");
		System.out.println("********************************************************\n");
		
		System.out.println("1. Crear Curso");
		System.out.println("2. Ver cursos registrados con su estado");
		System.out.println("3. Ver curso");
		System.out.println("4. Habilitar o deshabiliatar curso");
		
		System.out.println("5. Crear asignatura");
		System.out.println("6. Ver asignaturas registradas");
		System.out.println("7. Modificar asignatura");
		
		System.out.println("8. Asociar asignatura a curso");
		System.out.println("9. Ver asignaturas asociadas a un curso");
		System.out.println("10. Habilitar o deshabiliatar asignatura de un curso");
		System.out.println("11. Quitar asignatura de un curso");
		
		System.out.println("12. Crear unidad");
		System.out.println("13. Ver unidades de una asignatura de un curso");
		System.out.println("14. Modificar unidad");
		System.out.println("15. Habilitar o deshabiliatar unidad");
		
		System.out.println("16. Crear Alumno");
		System.out.println("17. Ver Alumno");
		System.out.println("18. Modificar Alumno");
		System.out.println("19. Habilitar o deshabiliatar Alumno");

		System.out.println("20. Crear Profesor");
		System.out.println("21. Ver Profesor");
		System.out.println("22. Modificar Profesor");
		System.out.println("23. Habilitar o deshabiliatar Profesor");
		
		System.out.println("24. Crear Apoderado");
		System.out.println("25. Ver Apoderado");
		System.out.println("26. Modificar Apoderado");
		System.out.println("27. Habilitar o deshabiliatar Apoderado");
		
		System.out.println("28. Asociar apoderado a alumno");

		System.out.println("29. Cerrar sesion");
		System.out.println("********************************************************\n");
		
		System.out.print("Ingrese su opcion: ");
	}
	
	public static int accionEjecucionAdministrador()
	{
		boolean validar = false;
		String opcion;
		do
		{
			menuAdministrador();
			opcion = Utilidades.extracted().nextLine();
			validar = Utilidades.esNumero(opcion);
			
			if(!validar )
			{
				System.out.println("Ha ingresado un parametro incorrecto. Por favor, ingrese una opcion valida..\n\n");
				opcion="-1";
			}
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>28)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>29);
		
		return Integer.parseInt(opcion);
	}
	
	public static void seleccionarOpcionAdministrador()
	{	
		int opcion;
		do
		{
			opcion = accionEjecucionAdministrador();
			
			switch(opcion)
			{
				case 1:
					CursoController.crear();
					break;
				case 2:
					CursoController.verCursosRegistrados();
					break;
				case 3:
					CursoController.ver();
					break;
				case 4:
					CursoController.modificar();
					break;
				case 5:
					AsignaturaController.crear();
					break;
				case 6:
					AsignaturaController.verAsignaturasRegistradas();
					break;
				case 7:
					AsignaturaController.modificar();
					break;
				case 8:
					CursoController.asociarAsignatura();
					break;
				case 9:
					CursoController.verListadoAsignaturasCurso();
					break;
				case 10:
					CursoController.cambiarEstadoAsignatura();
					break;
				case 11:
					System.out.println("Quitar asignatura de curso");
					break;
				case 12:
					UnidadController.crear();
					break;
				case 13:
					UnidadController.verListadoUnidades();
					break;
				case 14:
					UnidadController.modificar();
					break;
				case 15:
					UnidadController.cambiarEstadoUnidad();
					break;
				case 16:
					AlumnoController.crear();
					break;
				case 17:
					AlumnoController.ver();
					break;
				case 18:
					AlumnoController.modificar();
					break;
				case 19:
					AlumnoController.cambiarEstado();
					break;
				case 20:
					ProfesorController.crear();
					break;
				case 21:
					ProfesorController.ver();
					break;
				case 22:
					ProfesorController.modificar();
					break;
				case 23:
					ProfesorController.cambiarEstado();
					break;
				case 24:
					ApoderadoController.crear();
					break;
				case 25:
					ApoderadoController.ver();
					break;
				case 26:
					ApoderadoController.modificar();
					break;
				case 27:
					ApoderadoController.cambiarEstado();
					break;
				case 28:
					AlumnoController.asociarApoderado();
					break;
				default:
					System.out.println("Sesion cerrada...\n\n");
					break;
			}
		}
		while(opcion!=29);
	}
	/*Fin opciones administrador*/
	
	/*Opciones profesor*/
	/*Fin opciones profesor*/
	
	/*Opciones apoderado*/
	/*Fin opciones apoderado*/

}