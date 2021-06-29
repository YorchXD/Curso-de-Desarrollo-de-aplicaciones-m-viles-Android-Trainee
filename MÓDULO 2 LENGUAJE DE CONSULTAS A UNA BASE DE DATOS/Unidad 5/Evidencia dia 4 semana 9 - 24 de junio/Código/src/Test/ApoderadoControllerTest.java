package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Controller.ApoderadoController;
import Model.Apoderado;

class ApoderadoControllerTest
{

	@Test
	void testRegistrarApoderado()
	{
		String nombre = "Patricia Manríquez Lisboa";
		String run = "12296649-6";
		String email = "patricia@apoderado.cl";
		String clave = "12345";
		String resultado = ApoderadoController.registrarApoderado(nombre, email, run, clave);
		String esperado = "\nSe ha guardado exitosamente al apoderado\n";
		assertEquals(esperado, resultado);
	}

	@Test
	void testBuscarApoderado()
	{
		Apoderado apoderado = ApoderadoController.buscarApoderado("12296649-6");
		String resultado = apoderado.mostrarDatos();
		String esperado = 	"Nombre: Patricia Manríquez Lisboa" +
						   	"\nEmail: patricia@apoderado.cl" +
						   	"\nClave: 12345" + 
						   	"\nEstado: HABILITADO" +
						   	"\nRUN: 12296649-6" + 
						   	"\nTipoUsuario: APODERADO";
		assertEquals(esperado, resultado);
	}

	@Test
	void testCambiarEstadoApoderado()
	{
		Apoderado apoderado = ApoderadoController.buscarApoderado("12296649-6");
		String resultado = ApoderadoController.cambiarEstadoApoderado(apoderado);
		String esperado = "\nEl apoderado se ha modificado correctamente\n";
		assertEquals(esperado, resultado);
	}

	@Test
	void testActualizarApoderado()
	{
		Apoderado apoderado = ApoderadoController.buscarApoderado("12296649-6");
		String nombre = "Patricia Manríquez Lisboa";
		String email = "patricia@apoderado.cl";
		String clave = "12345";
		String resultado = ApoderadoController.actualizarApoderado(apoderado, nombre, email, clave);
		String esperado = "\nSe ha modificado los datos del apoderado\n";
		assertEquals(esperado, resultado);
	}

}
