package model;

import java.util.ArrayList;

public class Curso_Referencia
{
	private char letra;
	private Profesor profesorEncargado;
	private ArrayList<Alumno> alumnos;
	private Curso curso;
	private ArrayList<Asignatura_Referencia> asignaturas;
	private int anio;
	
	public Curso_Referencia(char letra, Profesor profesorEncargado, Curso curso, int anio)
	{
		this.letra = letra;
		this.profesorEncargado = profesorEncargado;
		this.alumnos = new ArrayList<Alumno>();
		this.curso = curso;
		this.asignaturas = new ArrayList<Asignatura_Referencia>();
		this.anio = anio;
	}

	public Alumno getAlumno(int index)
	{
		return alumnos.get(index);
	}

	public boolean addAlumno(Alumno e)
	{
		return alumnos.add(e);
	}

	public Alumno removeAlumno(int index)
	{
		return alumnos.remove(index);
	}

	public Asignatura_Referencia getAsignatura_Referencia(int index)
	{
		return asignaturas.get(index);
	}

	public boolean addAsignatura(Asignatura_Referencia e)
	{
		return asignaturas.add(e);
	}

	public Asignatura_Referencia removeAsignatura(int index)
	{
		return asignaturas.remove(index);
	}

	public char getLetra()
	{
		return letra;
	}

	public void setLetra(char letra)
	{
		this.letra = letra;
	}

	public Profesor getProfesorEncargado()
	{
		return profesorEncargado;
	}

	public void setProfesorEncargado(Profesor profesorEncargado)
	{
		this.profesorEncargado = profesorEncargado;
	}

	public ArrayList<Alumno> getAlumnos()
	{
		return alumnos;
	}

	public Curso getCurso()
	{
		return curso;
	}

	public void setCurso(Curso curso)
	{
		this.curso = curso;
	}

	public ArrayList<Asignatura_Referencia> getAsignaturas()
	{
		return asignaturas;
	}

	public int getAnio()
	{
		return anio;
	}

	public void setAnio(int anio)
	{
		this.anio = anio;
	}
}
