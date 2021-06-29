package Model;

import java.util.ArrayList;

import BD.ConsultaAlumno;
import Utilidades.AccionesPrincipales;

public class Alumno implements AccionesPrincipales
{
	private String nombre;
	private String run;
	private int edad;
	private Apoderado apoderado;
	private Estado estado;
	private Curso_Referencia curso;
	private ArrayList<Asistencia> asistencias;
	
	public Alumno()
	{
	}
	
	public Alumno(String nombre, String run, int edad, Apoderado apoderado, Estado estado, Curso_Referencia curso)
	{
		this.nombre = nombre;
		this.run = run;
		this.edad = edad;
		this.apoderado = apoderado;
		this.estado = estado;
		this.curso = curso;
		this.asistencias = new ArrayList<Asistencia>();
	}
	
	public Alumno(String nombre, String run, int edad, Estado estado)
	{
		this.nombre = nombre;
		this.run = run;
		this.edad = edad;
		this.estado=estado;
		this.apoderado = null;
		this.curso = null;
		this.asistencias = new ArrayList<Asistencia>();
	}
	
	public Alumno(String nombre, String run)
	{
		this.nombre = nombre;
		this.run = run;
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

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
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

	public String mostrarDatos()
	{
		return	"Nombre: " + this.nombre + 
				"\nRUN: " + this.run +
			   	"\nEdad: " + this.edad +
			   	"\nEstado: " + this.estado;
	}

	public boolean registrarDatos()
	{
		return ConsultaAlumno.registrarAlumno(this.nombre, this.run, this.edad);
	}

	public boolean actualizarDatos()
	{
		return ConsultaAlumno.actualizarDatos(this.nombre, this.run, this.edad);
	}

	public boolean cambiarEstado()
	{
		return ConsultaAlumno.cambiarEstado(this.run, this.estado);
	}

	public boolean asociarApoderado()
	{
		return ConsultaAlumno.asociarAlumnoApoderado(this.run, this.apoderado.getRun());
	}
}
