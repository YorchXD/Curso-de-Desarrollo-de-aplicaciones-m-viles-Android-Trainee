package Model;

import java.util.HashMap;
import java.util.Map;

public class Asignatura
{
	private int id;
	private String nombre;
	private Estado estado;
	private Map<Integer, Unidad> unidades;
	
	public Asignatura(int id, String nombre, Estado estado)
	{
		this.id = id;
		this.nombre = nombre;
		this.estado = estado;
		this.unidades = new HashMap<Integer, Unidad>();
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

	public Map<Integer, Unidad> getUnidades()
	{
		return unidades;
	}
	
	public void setUnidades(Map<Integer, Unidad> unidades)
	{
		this.unidades = unidades;
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
