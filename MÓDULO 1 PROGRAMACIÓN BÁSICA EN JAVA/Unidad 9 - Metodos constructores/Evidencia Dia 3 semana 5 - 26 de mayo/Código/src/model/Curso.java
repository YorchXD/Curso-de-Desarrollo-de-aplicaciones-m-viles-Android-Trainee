package model;

import java.util.ArrayList;

public class Curso
{
	private Nivel nivel;
	private ArrayList<Asignatura> asignaturas;
	private Tipo_Division_Anual tipoDivisionAnual;
	private Estado estado;
	
	public Curso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		this.nivel = nivel;
		this.asignaturas = new ArrayList<Asignatura>();
		this.tipoDivisionAnual = tipoDivisionAnual;
		this.estado = Estado.HABILITADO;
	}
	
	public Curso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual, Estado estado)
	{
		this.nivel = nivel;
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

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public ArrayList<Asignatura> getAsignaturas()
	{
		return asignaturas;
	}

	public Tipo_Division_Anual getTipoDivisionAnual()
	{
		return tipoDivisionAnual;
	}

	public void setTipoDivisionAnual(Tipo_Division_Anual tipoDivisionAnual)
	{
		this.tipoDivisionAnual = tipoDivisionAnual;
	}

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}
}
