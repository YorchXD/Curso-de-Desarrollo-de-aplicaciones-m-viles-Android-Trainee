package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import BD.ConsultaCurso;
import Controller.UnidadController;
import Model.Asignatura;
import Model.Curso;
import Model.Estado;
import Model.Unidad;

class UnidadTest {

	@Test
	void testRegistrarDatos() {
		
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		
		Curso curso = cursos.get(1);
		Asignatura asignatura = curso.getAsignaturas().get(1);
		
		Unidad unidad = new Unidad("Resta", 2, 1, asignatura.getId(), curso.getId());
		
		boolean esperado = true;
		boolean resultado = unidad.registrarDatos();
		assertEquals(esperado, resultado);

	}
	
	@Test
	void testMostrarDatos() 
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(1).getAsignaturas().get(1).getUnidades();

		String resultado=null;
		
		String esperado = "Nombre: " + "Resta" + 
		"\nNumero Unidad: " + 2 + 
		"\nTipo Division Anual: " + 1 + 
		"\nEstado: " + "DESHABILITADO";
		
		for (Map.Entry<Integer,Unidad> unidad : unidades.entrySet())
		{
			if(unidad.getValue().getNombre().equals("Resta")) {
				resultado=unidad.getValue().mostrarDatos();
				break;
			}
		}
		
		assertEquals(esperado, resultado);		
	}
	
	@Test
	void testActualizarDatos() 
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(1).getAsignaturas().get(1).getUnidades();
		Unidad unidad_extraida  = unidades.get(2);
		
		
		unidad_extraida.setNombre("Reales");
		unidad_extraida.setNumero_unidad(1);
		unidad_extraida.setDivision_anual(1);
		unidad_extraida.setId(2);
		
		
		boolean esperado = true;
		boolean resultado = unidad_extraida.actualizarDatos();
		assertEquals(esperado, resultado);
	}
	
	@Test
	void testCambiarEstado()
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(1).getAsignaturas().get(1).getUnidades();
		Unidad unidad_extraida  = unidades.get(1);
		
		unidad_extraida.setEstado(Estado.DESHABILITADO);
		
		boolean esperado = true;
		boolean resultado = unidad_extraida.cambiarEstado();
		assertEquals(esperado, resultado);

	}
	
	
	


}
