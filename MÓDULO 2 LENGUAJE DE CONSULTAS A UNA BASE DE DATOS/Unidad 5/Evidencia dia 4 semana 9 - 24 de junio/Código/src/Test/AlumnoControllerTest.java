package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controller.AlumnoController;
import Model.Alumno;

class AlumnoControllerTest
{
	@Test
	void testAsociarApoderadoAlumno()
	{
		String resultado = AlumnoController.asociarApoderadoAlumno("17824523-6", "12296649-6");
		String esperado = "\nLa asociacion entre el apoderado y el alumno se ha realizado exitosamente.\n";
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testRegistrarAlumno()
	{
		String resultado = AlumnoController.registrarAlumno("Yorch Sepúlveda", "17824523-6", 29);
		String esperado = "\nEl alumno se ha registrado exitosamente\n";
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testVerAlumno()
	{
		Alumno alumno = AlumnoController.buscarAlumno("17824523-6");
		String resultado = alumno.mostrarDatos();
		
		String esperado = "Nombre: Yorch Sepúlveda" + 
				"\nRUN: 17824523-6" +
			   	"\nEdad: 29"+
			   	"\nEstado: HABILITADO";
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testCambiarEstadoAlumno()
	{
		Alumno alumno = AlumnoController.buscarAlumno("17824523-6");		
		String resultado = AlumnoController.modificarEstadoAlumno(alumno);		
		String esperado = "\nEl alumno se ha modificado correctamente\n";
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testActualizarAlumno()
	{
		Alumno alumno = AlumnoController.buscarAlumno("17824523-6");
		String resultado = AlumnoController.actualizarAlumno(alumno,"Yorch Wilian Seppúlveda Manríquez", 29);
		String esperado = "\nSe ha modificado exitosamente los datos del alumno\n";
		assertEquals(esperado, resultado);
	}

}
