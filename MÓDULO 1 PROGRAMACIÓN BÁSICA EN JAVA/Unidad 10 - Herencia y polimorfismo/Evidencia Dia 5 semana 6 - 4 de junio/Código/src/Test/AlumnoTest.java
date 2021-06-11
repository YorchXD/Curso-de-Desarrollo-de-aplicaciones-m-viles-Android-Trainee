package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Alumno;

class AlumnoTest
{

	@Test
	void testRegistrarDatos()
	{
		Alumno alumno = new Alumno();
		alumno.setNombre("Juana Perez");
		alumno.setRun("22222222-2");
		alumno.setEdad(11);
		
		boolean esperado = true;
		boolean resultado = alumno.registrarDatos();
		assertEquals(esperado, resultado);
	}

}
