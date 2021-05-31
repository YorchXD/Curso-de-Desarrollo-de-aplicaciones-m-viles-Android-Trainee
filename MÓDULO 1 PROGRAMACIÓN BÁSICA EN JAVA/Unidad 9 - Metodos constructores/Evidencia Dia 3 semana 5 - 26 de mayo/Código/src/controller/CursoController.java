package controller;

import BD.ConsultaAdministrador;
import View.Curso.ViewCurso;
import model.Curso;
import model.Nivel;
import model.Tipo_Division_Anual;

public class CursoController
{
	public static Curso buscarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		return ConsultaAdministrador.buscarCurso(nivel, tipoDivisionAnual);
	}
	
	public static boolean registrarCurso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		return ConsultaAdministrador.registrarCurso(nivel, tipoDivisionAnual);
	}
	
	public static void crear()
	{
		ViewCurso.crear();
	}
}
