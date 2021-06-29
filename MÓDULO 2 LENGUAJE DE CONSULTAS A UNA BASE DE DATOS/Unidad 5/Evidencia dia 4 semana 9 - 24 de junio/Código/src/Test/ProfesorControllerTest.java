package Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Controller.ProfesorController;
import Model.Profesor;
import Model.Especialidad;

class ProfesorControllerTest
{

	@Test
	void testRegistrarProfesor()
	{
		String nombre = "Sergio Sepúlveda";
		String email = "segio@profesor.cl";
		String clave = "12345";
		String run = "12283757-2";
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		especialidades.put(1, new Especialidad(1, "Pedagogía en matematicas"));
		String resultado = ProfesorController.registrarProfesor(nombre, run, email, clave, especialidades);
		String esperado = "\nSe ha guardado exitosamente al profesor\n";
		assertEquals(esperado, resultado);
	}

	@Test
	void testBuscarProfesor()
	{
		Profesor profesor = ProfesorController.buscarProfesor("12283757-2");
		String resultado = profesor.mostrarDatos();
		String esperado = "Nombre: Sergio Sepúlveda" + 
			   	"\nEmail: segio@profesor.cl" + 
			   	"\nClave: 12345" + 
			   	"\nEstado: HABILITADO" + 
			   	"\nRUN: 12283757-2" + 
			   	"\nTipoUsuario: PROFESOR" +
			   	"\nEspecialidad: Pedagogía en lenguaje";
		assertEquals(esperado, resultado);
	}

	@Test
	void testCambiarEstadoProfesor()
	{
		Profesor profesor = ProfesorController.buscarProfesor("12283757-2");
		String resultado = ProfesorController.cambiarEstadoProfesor(profesor);
		String esperado = "\nEl profesor se ha modificado correctamente\n";
		assertEquals(esperado, resultado);
		
	}

	@Test
	void testActualizarProfesor()
	{
		Profesor profesor = ProfesorController.buscarProfesor("12283757-2");
		String nombre = "Sergio Antonio Sepúlveda Leal";
		String email = "segio@profesor.cl";
		String clave = "12345";
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		especialidades.put(1, new Especialidad(1, "Pedagogía en matematicas"));
		String resultado = ProfesorController.actualizarProfesor(profesor, nombre, email, clave);
		String esperado = "\nSe ha modificado los datos del profesor\n";
		assertEquals(esperado, resultado);
	}

}
