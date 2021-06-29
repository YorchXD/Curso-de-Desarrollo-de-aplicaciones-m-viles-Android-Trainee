package Model;

import java.util.ArrayList;

public class Apoderado extends Usuario
{
	private ArrayList<Alumno> alumnos;

	public Apoderado(String nombre, String email, String clave, String run)
	{
		super(nombre, email, clave, Estado.HABILITADO, run, TipoUsuario.APODERADO);
		this.alumnos = new ArrayList<Alumno>();
	}
	
	public Apoderado(String nombre, String email, String clave, Estado estado, String run, TipoUsuario tipoUsuario)
	{
		super(nombre, email, clave, estado, run, TipoUsuario.APODERADO);
		this.alumnos = new ArrayList<Alumno>();
	}
	
	public Apoderado(Usuario usuario)
	{
		super(usuario.getNombre(), usuario.getEmail(), usuario.getClave(), usuario.getEstado(), usuario.getRun(), usuario.getTipoUsuario());
		this.alumnos = new ArrayList<Alumno>();
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

	public ArrayList<Alumno> getAlumnos()
	{
		return alumnos;
	}	
}
