package Test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import Model.Especialidad;
import Model.Profesor;

class ProfesorTest
{

	@Test
	public void testRegistrarDatos()
	{
		String nombre = "Sergio Sepúlveda";
		String email = "segio@profesor.cl";
		String clave = "12345";
		String run = "12283757-2";
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		especialidades.put(1, new Especialidad(1, "Pedagogía en matematicas"));
		Profesor profesor = new Profesor(nombre, email, clave, run, especialidades);
		boolean esperado = true;
		boolean resultado = profesor.registrarDatos();
		assertEquals(esperado, resultado);
	}
	
	@Test
	public void testAsociarEspecialidad()
	{
		String nombre = "Sergio Sepúlveda";
		String email = "segio@profesor.cl";
		String clave = "12345";
		String run = "12283757-2";
		Map<Integer, Especialidad> especialidades = new HashMap<>();
		especialidades.put(1, new Especialidad(1, "Pedagogía en matematicas"));
		Profesor profesor = new Profesor(nombre, email, clave, run, especialidades);
		boolean esperado = true;
		boolean resultado = profesor.asociarEspecialidadProfesor(1);
		assertEquals(esperado, resultado);
	}

}
