package Controller;

import BD.ConsultaProfesor;
import View.Profesor.ViewProfesor;
import Model.Profesor;


public class ProfesorController {

	
	/*Metodos que activan la vista segun sea el caso*/
	public static void crear()
	{
		ViewProfesor.crear();
	}
	
	public static void ver()
	{
		ViewProfesor.ver();
	}
	
	public static void modificar()
	{
		ViewProfesor.modificar();
	}
	
	public static void cambiarEstado()
	{
		ViewProfesor.cambiarEstado();
	}
	/*Fin mostrar vistas*/
	
	public static Profesor buscarProfesor(String run)
	{
		return ConsultaProfesor.consultarProfesor_run(run);
	}
	
	public static String registrarProfesor(Profesor profesor)
	{
		return ConsultaProfesor.insertarProfesor(profesor);
	}

	public static String actualizarProfesor(String nombre, String email, String clave, String especialidad, String run)
	{
		return ConsultaProfesor.UpdateProfesor(nombre, email, clave,especialidad, run);
	}
	
	public static String modificarEstadoProfesor(Profesor profesor)
	{
		return ConsultaProfesor.UpdateProfesor_estado(profesor);
	}



}
