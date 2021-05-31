package controller;

import View.Administrador.IndexAdministrador;
import model.Usuario;

public class AdministradorController
{
	public static void crearAlumno()
	{
		
	}
	
	public static void seleccionarOpcion(Usuario usuario)
	{
		System.out.println("\n\nDatos del administrador: ");
		usuario.mostrarDatos();
		System.out.println("\n\n");
		
		int opcion;
		do
		{
			opcion = IndexAdministrador.accionEjecucion();
			
			switch(opcion)
			{
				case 1:
					System.out.println("Ir a crear curso");
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
				default:
					System.out.println("Sesion cerrada...\n\n");
					break;
			}
		}
		while(opcion!=13);
	}

}