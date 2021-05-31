package model;

import java.util.ArrayList;

public class Curso
{
	private String tipoCurso;
	private ArrayList<Asignatura> asignaturas;
	private String tipoDivisionAnual;
	private String estado;
	
	public Curso(String tipoCurso, String tipoDivisionAnual)
	{
		this.tipoCurso = tipoCurso;
		this.asignaturas = new ArrayList<Asignatura>();
		this.tipoDivisionAnual = tipoDivisionAnual;
		this.estado = "Habilitado";
	}
	
	public Curso(String tipoCurso, String tipoDivisionAnual, String estado)
	{
		this.tipoCurso = tipoCurso;
		this.asignaturas = new ArrayList<Asignatura>();
		this.tipoDivisionAnual = tipoDivisionAnual;
		this.estado = estado;
	}

	public Asignatura getAsignatura(int index)
	{
		return asignaturas.get(index);
	}

	public boolean addAsignatura(Asignatura e)
	{
		return asignaturas.add(e);
	}

	public Asignatura removeAsignatura(int index)
	{
		return asignaturas.remove(index);
	}

	public String getTipoCurso()
	{
		return tipoCurso;
	}

	public void setTipoCurso(String tipoCurso)
	{
		this.tipoCurso = tipoCurso;
	}

	public ArrayList<Asignatura> getAsignaturas()
	{
		return asignaturas;
	}

	public String getTipoDivisionAnual()
	{
		return tipoDivisionAnual;
	}

	public void setTipoDivisionAnual(String tipoDivisionAnual)
	{
		this.tipoDivisionAnual = tipoDivisionAnual;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}
}
