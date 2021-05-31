package Model;

import java.util.ArrayList;

public class Asignatura
{
	private int id;
	private String nombre;
	private Estado estado;
	private ArrayList<Unidad> unidades;
	
	public Asignatura(int id, String nombre, Estado estado)
	{
		this.id = id;
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

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	public ArrayList<Unidad> getUnidades()
	{
		return unidades;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
}
