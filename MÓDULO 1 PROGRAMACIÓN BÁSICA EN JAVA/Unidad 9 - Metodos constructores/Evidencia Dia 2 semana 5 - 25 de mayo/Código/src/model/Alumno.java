package model;

import java.util.ArrayList;

public class Alumno
{
	private String nombre;
	private String run;
	private int edad;
	private Apoderado apoderado;
	private String estado;
	private Curso_Referencia curso;
	private ArrayList<Asistencia> asistencias;
	
	public Alumno(String nombre, String run, int edad, Apoderado apoderado, String estado, Curso_Referencia curso)
	{
		this.nombre = nombre;
		this.run = run;
		this.edad = edad;
		this.apoderado = apoderado;
		this.estado = estado;
		this.curso = curso;
		this.asistencias = new ArrayList<Asistencia>();
	}
	
	public Alumno(String nombre, String run, int edad, String estado)
	{
		this.nombre = nombre;
		this.run = run;
		this.edad = edad;
		this.estado=estado;
		this.asistencias = new ArrayList<Asistencia>();
	}


	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getRun()
	{
		return run;
	}

	public void setRun(String run)
	{
		this.run = run;
	}

	public int getEdad()
	{
		return edad;
	}

	public void setEdad(int edad)
	{
		this.edad = edad;
	}

	public Apoderado getApoderado()
	{
		return apoderado;
	}

	public void setApoderado(Apoderado apoderado)
	{
		this.apoderado = apoderado;
	}

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
	{
		this.estado = estado;
	}

	public Curso_Referencia getCurso()
	{
		return curso;
	}

	public void setCurso(Curso_Referencia curso)
	{
		this.curso = curso;
	}

	public Asistencia getAsistencia(int index)
	{
		return asistencias.get(index);
	}

	public boolean addAsistencia(Asistencia e)
	{
		return asistencias.add(e);
	}

	public Asistencia removeAsistencia(int index)
	{
		return asistencias.remove(index);
	}

	public ArrayList<Asistencia> getAsistencias()
	{
		return asistencias;
	}
	
	public void mostrarDatos() {

		System.out.println("\n------------------------------------------- ");
		System.out.println("Datos del Alumno ");
		System.out.println("\n- Nombre: " + getNombre());
		System.out.println("- Run: " + getRun());
		System.out.println("- Edad: " + getEdad());
		System.out.println("- Estado: HABILITADO");
		System.out.println("\n------------------------------------------- ");
	}
}
