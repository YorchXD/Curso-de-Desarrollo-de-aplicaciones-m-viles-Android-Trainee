package Test;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

import Model.Estado;
import Model.Profesor;
import Model.TipoUsuario;

class ProfesorTest
{

	@Test
	public void testRegistrarDatos()
	{
		String nombre = "Sergio Sepúlveda";
		String email = "segio@profesor.cl";
		String clave = "12345";
		Estado estado = Estado.HABILITADO;
		String run = "12283757-2";
		TipoUsuario tipoUsuario = TipoUsuario.PROFESOR;
		String especialidad = "Pedagogía en lenguaje";
		Profesor profesor = new Profesor(nombre, email, clave, estado, run, tipoUsuario, especialidad);
		boolean esperado = true;
		boolean resultado = profesor.registrarDatos();
		assertEquals(esperado, resultado);
	}

}
