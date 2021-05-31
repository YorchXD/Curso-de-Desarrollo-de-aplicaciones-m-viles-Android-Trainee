package View.Administrador;

import Utilidades.Utilidades;
import controller.AlumnoController;
import controller.CursoController;


public class ViewUsuario
{
	/*Opciones administrador*/
	public static void menuAdministrador()
	{
		System.out.println("####################Menu Administrador####################");
		System.out.println("1. Crear Curso");
		System.out.println("2. Ver curso");
		System.out.println("3. Modificar Curso");
		System.out.println("4. Habilitar o deshabiliatar curso");
		
		System.out.println("5. Crear asignatura");
		System.out.println("6. Ver asignatura");
		System.out.println("7. Modificar asignatura");
		System.out.println("8. Habilitar o deshabiliatar asignatura");
		
		System.out.println("9. Crear unidad");
		System.out.println("10. Ver unidad");
		System.out.println("11. Modificar unidad");
		System.out.println("12. Habilitar o deshabiliatar unidad");
		
		System.out.println("13. Crear Alumno");
		System.out.println("14. Ver Alumno");
		System.out.println("15. Modificar Alumno");
		System.out.println("16. Habilitar o deshabiliatar Alumno");

		System.out.println("17. Cerrar sesion");
		
		System.out.print("\nIngrese su opcion: ");
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
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>17)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>17);
		
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
					System.out.println("Ir a ver curso");
					break;
				case 3:
					System.out.println("Ir a  modificar curso");
					break;
				case 4:
					System.out.println("Ir a habilitar o deshabilitar curso");
					break;
				case 5:
					System.out.println("Ir a crear asignatura");
					break;
				case 6:
					System.out.println("Ir a ver asignatura");
					break;
				case 7:
					System.out.println("Ir a modificar asignatura");
					break;
				case 8:
					System.out.println("Ir a habilitar o deshabilitar asignatura");
					break;
				case 9:
					System.out.println("Ir a crear unidad");
					break;
				case 10:
					System.out.println("Ir a ver unidad");
					break;
				case 11:
					System.out.println("Ir a modificar unidad");
					break;
				case 12:
					System.out.println("Ir a habilitar o deshabilitar unidad");
					break;
				case 13:
					AlumnoController.crear();
					break;
				case 14:
					AlumnoController.ver();
					break;
				case 15:
					AlumnoController.modificar();
					break;
				case 16:
					AlumnoController.cambiarEstado();
					break;
				default:
					System.out.println("Sesion cerrada...\n\n");
					break;
			}
		}
		while(opcion!=17);
	}
	/*Fin opciones administrador*/
	
	/*Opciones profesor*/
	/*Fin opciones profesor*/
	
	/*Opciones apoderado*/
	/*Fin opciones apoderado*/

}
