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
	

	/*@Override
	public void mostrarDatos()
	{
		System.out.println("\n********************************************************");
		System.out.println("*                  Datos del apoderado                 *");
		System.out.println("********************************************************\n");
		System.out.println("Nombre: " + super.getNombre());
		System.out.println("Email: " + super.getEmail());
		System.out.println("Clave: " + super.getClave());
		System.out.println("Estado: " + super.getEstado());
		System.out.println("RUN: " + super.getRun());
		System.out.println("tipoUsuario: " + super.getTipoUsuario());
		System.out.println("********************************************************\n");
	}*/
	
}
