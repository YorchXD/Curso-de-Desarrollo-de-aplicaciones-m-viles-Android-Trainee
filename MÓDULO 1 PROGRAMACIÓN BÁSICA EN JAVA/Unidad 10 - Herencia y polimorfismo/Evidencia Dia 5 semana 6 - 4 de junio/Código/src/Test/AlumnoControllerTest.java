package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controller.AlumnoController;

class AlumnoControllerTest
{

	@Test
	void testAsociarApoderadoAlumno()
	{
		String resultado = AlumnoController.asociarApoderadoAlumno("17824523-6", "19043138-k");
		String esperado = "\nLa asociacion entre el apoderado y el alumno se ha realizado exitosamente.\n";
		assertEquals(esperado, resultado);
	}

}
