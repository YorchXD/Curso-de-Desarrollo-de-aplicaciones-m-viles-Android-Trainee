package Model;

import java.util.HashMap;
import java.util.Map;

public class Curso
{
	private Nivel nivel;
	private Map<Integer, Asignatura> asignaturas;
	private Tipo_Division_Anual tipoDivisionAnual;
	private Estado estado;
	private int id;
	
	public Curso(Nivel nivel, Tipo_Division_Anual tipoDivisionAnual)
	{
		this.nivel = nivel;
		this.asignaturas = new HashMap<>();
		this.tipoDivisionAnual = tipoDivisionAnual;
		this.estado = Estado.HABILITADO;
	}
	
	public Curso(int id, Nivel nivel, Tipo_Division_Anual tipoDivisionAnual, Estado estado)
	{
		this.id = id;
		this.nivel = nivel;
		this.asignaturas = new HashMap<>();
		this.tipoDivisionAnual = tipoDivisionAnual;
		this.estado = estado;
	}

	public Nivel getNivel()
	{
		return nivel;
	}

	public void setNivel(Nivel nivel)
	{
		this.nivel = nivel;
	}

	public Map<Integer, Asignatura> getAsignaturas()
	{
		return asignaturas;
	}
	
	public void setAsignaturas(Map<Integer, Asignatura> asignaturas)
	{
		this.asignaturas = asignaturas;
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
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public void mostrarDatos()
	{
		String [] nivel = this.nivel.toString().split("_");
		System.out.println("\n###########################Datos del curso###########################");
		System.out.println("Nivel: " + nivel[0] + " " + nivel[1]);
		System.out.println("Tipo de division anual: " + this.tipoDivisionAnual);
		System.out.println("Estado: " + this.estado);
		System.out.println("######################################################################\n");
	}
}
