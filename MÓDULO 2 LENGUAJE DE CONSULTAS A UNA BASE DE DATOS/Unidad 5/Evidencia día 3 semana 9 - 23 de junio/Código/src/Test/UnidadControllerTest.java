package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import Controller.UnidadController;
import Model.Curso;
import Model.Unidad;

class UnidadControllerTest 
{

	@Test
	void testRegistrarUnidad() 
	{
		String resultado = UnidadController.registrarUnidad(1, 1, "Multiplicación", 1, 4);
		String esperado = "\nLa unidad se ha registrado correctamente.\n";
		assertEquals(esperado, resultado);
	}

	@Test
	void testActualizarUnidad() 
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(1).getAsignaturas().get(1).getUnidades();
		Unidad unidad_extraida  = unidades.get(4);
		String resultado = UnidadController.actualizarUnidad(unidad_extraida, "División", 1, 4);
		String esperado = "\nLa unidad se ha modificado correctamente.\n";
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testCambiarEstadoUnidad() 
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(1).getAsignaturas().get(1).getUnidades();
		Unidad unidad_extraida  = unidades.get(4);
		String resultado = UnidadController.actualizarEstadoUnidad(unidad_extraida);
		String esperado = "\nLa unidad se ha modificado correctamente\n";
		assertEquals(esperado, resultado);
	}

}
