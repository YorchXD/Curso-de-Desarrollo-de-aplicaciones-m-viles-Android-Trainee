package Model;

import java.util.ArrayList;
import java.util.Date;

public class Evaluacion
{
	private int id;
	private double nota;
	private Date fecha;
	private Alumno alumno;
	private ArrayList<Unidad> unidades;
	private Profesor profesorEncargado;
	private String descripcion;
	
	public Evaluacion(int id, double nota, Date fecha, Alumno alumno, Profesor profesorEncargado, String descripcion)
	{
		this.id = id;
		this.nota = nota;
		this.fecha = fecha;
		this.alumno = alumno;
		this.unidades = new ArrayList<Unidad>();
		this.profesorEncargado = profesorEncargado;
		this.descripcion = descripcion;
	}

	public Unidad getUnidad(int index)
	{
		return unidades.get(index);
	}

	public boolean addUnidad(Unidad e)
	{
		return unidades.add(e);
	}

	public Unidad removeUnidad(int index)
	{
		return unidades.remove(index);
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public double getNota()
	{
		return nota;
	}

	public void setNota(double nota)
	{
		this.nota = nota;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public Alumno getAlumno()
	{
		return alumno;
	}

	public void setAlumno(Alumno alumno)
	{
		this.alumno = alumno;
	}

	public ArrayList<Unidad> getUnidades()
	{
		return unidades;
	}

	public Profesor getProfesorEncargado()
	{
		return profesorEncargado;
	}

	public void setProfesorEncargado(Profesor profesorEncargado)
	{
		this.profesorEncargado = profesorEncargado;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}
