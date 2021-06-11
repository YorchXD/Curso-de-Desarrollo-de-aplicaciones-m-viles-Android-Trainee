package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import BD.ConsultaCurso;
import Controller.UnidadController;
import Model.Asignatura;
import Model.Curso;
import Model.Unidad;

class UnidadTest {

	@Test
	void testRegistrarDatos() {
		
		Map<Integer, Curso> cursos = ConsultaCurso.listadoCurso();
		
		for (Map.Entry<Integer,Curso> curso : cursos.entrySet())
		{
			ConsultaCurso.buscarAsignaturasCurso(curso.getValue());
		}
		
		Curso curso = cursos.get(3);
		Asignatura asignatura = curso.getAsignaturas().get(6);
		
		Unidad unidad = new Unidad("Resta", 2, 1, asignatura);
		
		boolean esperado = true;
		boolean resultado = unidad.registrarDatos();
		assertEquals(esperado, resultado);

	}
	
	@Test
	void testMostrarDatos() 
	{
		Map<Integer, Curso> cursos = UnidadController.obtenerTodosDatosCurso();
		Map <Integer, Unidad> unidades = cursos.get(3).getAsignaturas().get(6).getUnidades();

		String resultado=null;
		
		String esperado = "Nombre: " + "Sumas" + 
		"\nNumero Unidad: " + 2 + 
		"\nTipo División Anual: " + 1 + 
		"\nEstado: " + "DESHABILITADO";
		
		for (Map.Entry<Integer,Unidad> unidad : unidades.entrySet())
		{
			if(unidad.getValue().getNombre().equals("Sumas")) {
				resultado=unidad.getValue().mostrarDatos();
				break;
			}
		}
		
		assertEquals(esperado, resultado);		
	}
	
	


}
