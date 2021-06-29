package Model;

import BD.ConsultaUsuario;

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
	
	/**
	 * Muestra el valor que tiene el atributo especialidad
	 * @return especialidad
	 */
	public String getEspecialidad()
	{
		return especialidad;
	}
	
	/**
	 * Ayuda a modificar el valor del atributo especialidad
	 * @param especialidad
	 */
	public void setEspecialidad(String especialidad)
	{
		this.especialidad = especialidad;
	}
	
	@Override
	public String mostrarDatos()
	{
		return	"Nombre: " + super.getNombre() + 
					   	"\nEmail: " + super.getEmail() +
					   	"\nClave: " + super.getClave() +
					   	"\nEstado: " + super.getEstado() +
					   	"\nRUN: " + super.getRun() +
					   	"\nTipoUsuario: " + super.getTipoUsuario() +
					   	"\nEspecialidad: " + this.especialidad;
	}
	
	@Override
	public boolean registrarDatos()
	{
		return ConsultaUsuario.registrarUsuario(this.getNombre(), this.getRun(), this.getEmail(), this.getClave(), this.getTipoUsuario(), this.especialidad);
	}
	
	@Override
	public boolean actualizarDatos()
	{
		return ConsultaUsuario.actualizarDatos(this.getNombre(), this.getEmail(), this.getClave(), this.especialidad, this.getRun(), this.getTipoUsuario());
	}
	
	@Override
	public boolean registrarCuenta()
	{
		return ConsultaUsuario.registrarCuenta(this.getRun(), this.getEmail(), this.getClave(), this.getTipoUsuario(), this.especialidad);
	}
}
