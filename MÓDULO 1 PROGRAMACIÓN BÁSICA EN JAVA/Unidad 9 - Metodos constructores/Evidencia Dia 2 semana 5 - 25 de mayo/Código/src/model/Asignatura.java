package model;

import java.util.ArrayList;

public class Asignatura
{
	private String nombre;
	private String estado;
	private ArrayList<Unidad> unidades;
	
	public Asignatura(String nombre, String estado)
	{
		this.nombre = nombre;
		this.estado = estado;
		this.unidades = new ArrayList<Unidad>();
	}

	public Unidad get(int index)
	{
		return unidades.get(index);
	}

	public boolean add(Unidad e)
	{
		return unidades.add(e);
	}

	public Unidad remove(int index)
	{
		return unidades.remove(index);
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public ArrayList<Unidad> getUnidades()
	{
		return unidades;
	}
	
}
