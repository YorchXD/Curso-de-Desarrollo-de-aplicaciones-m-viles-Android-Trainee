package Test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import Model.Apoderado;
import Model.Estado;
import Model.TipoUsuario;

class ApoderadoTest
{

	@Test
	public void testRegistrarDatos()
	{
		String nombre = "Gregory Sepúlveda";
		String email = "gregory@apoderado.cl";
		String clave = "12345";
		Estado estado = Estado.HABILITADO;
		String run = "19043138-k";
		TipoUsuario tipoUsuario = TipoUsuario.APODERADO;
		Apoderado Apoderado = new Apoderado(nombre, email, clave, estado, run, tipoUsuario);
		boolean esperado = true;
		boolean resultado = Apoderado.registrarDatos();
		assertEquals(esperado, resultado);
	}

}
