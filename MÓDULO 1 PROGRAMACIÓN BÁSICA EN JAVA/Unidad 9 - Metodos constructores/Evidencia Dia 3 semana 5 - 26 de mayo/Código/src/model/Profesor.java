package model;

public class Profesor extends Usuario
{
	private String especialidad;

	public Profesor(String nombre, String email, String clave, Estado estado, String run, TipoUsuario tipoUsuario, String especialidad)
	{
		super(nombre, email, clave, estado, run, tipoUsuario);
		this.especialidad = especialidad;
	}
	
	public Profesor(Usuario usuario)
	{
		super(usuario.getNombre(), usuario.getEmail(), usuario.getClave(), usuario.getEstado(), usuario.getRun(), usuario.getTipoUsuario());
		this.especialidad = null;
	}

	public String getEspecialidad()
	{
		return especialidad;
	}

	public void setEspecialidad(String especialidad)
	{
		this.especialidad = especialidad;
	}
	
	public void mostrarDatos()
	{
		System.out.println("\n#########################Datos del profesor#########################");
		super.mostrarDatos();
		System.out.println("especialidad: " + this.especialidad);
		System.out.println("\n####################################################################\n");
	}
}
