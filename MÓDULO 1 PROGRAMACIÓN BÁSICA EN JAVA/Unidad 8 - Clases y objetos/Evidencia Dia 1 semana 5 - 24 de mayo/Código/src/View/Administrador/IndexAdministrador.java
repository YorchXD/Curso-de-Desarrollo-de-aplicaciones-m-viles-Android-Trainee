package View.Administrador;

import Utilidades.Utilidades;

public class IndexAdministrador
{
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
		
		
		
		System.out.println("13. Cerrar sesion");
		
		System.out.print("\nIngrese su opcion: ");
	}
	
	public static int accionEjecucion()
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
			else if(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>13)
			{
				System.out.println("La opcion ingresada no es valida. Favor ingrese una opcion segun las opciones que muestra el menu.\n\n");
			}
		}
		while(Integer.parseInt(opcion)<1 || Integer.parseInt(opcion)>13);
		
		return Integer.parseInt(opcion);
	}

}
