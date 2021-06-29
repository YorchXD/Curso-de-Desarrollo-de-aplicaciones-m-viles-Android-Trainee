package Model;

import java.util.HashMap;
import java.util.Map;

import BD.ConsultaProfesor;
import BD.ConsultaUsuario;

public class Profesor extends Usuario
{
	private Map<Integer, Especialidad> especialidades;
	
	public Profesor(String nombre, String email, String run, Map<Integer, Especialidad> especialidades)
	{
		super(nombre, email, Estado.HABILITADO, run, TipoUsuario.PROFESOR);
		this.especialidades = especialidades;
	}
	
	/*Constructor utilizado para registrar*/
	public Profesor(String nombre, String email, String clave, String run, Map<Integer, Especialidad> especialidades)
	{
		super(nombre, email, clave, Estado.HABILITADO, run, TipoUsuario.PROFESOR);
		this.especialidades = especialidades;
	}
	
	public Profesor(String nombre, String email, Estado estado, String run, Map<Integer, Especialidad> especialidades)
	{
		super(nombre, email, estado, run, TipoUsuario.PROFESOR);
		this.especialidades = especialidades;
	}

	public Profesor(String nombre, String email, String clave, Estado estado, String run, TipoUsuario tipoUsuario, Map<Integer, Especialidad> especialidades)
	{
		super(nombre, email, clave, estado, run, tipoUsuario);
		this.especialidades = especialidades;
	}
	
	public Profesor(Usuario usuario)
	{
		super(usuario.getNombre(), usuario.getEmail(), usuario.getClave(), usuario.getEstado(), usuario.getRun(), usuario.getTipoUsuario());
		this.especialidades = new HashMap<>();
	}
	
	public Map<Integer, Especialidad> getEspecialidades()
	{
		return especialidades;
	}

	public void setEspecialidades(Map<Integer, Especialidad> especialidades)
	{
		this.especialidades = especialidades;
	}
	
	
	public boolean asociarEspecialidadProfesor(int refEspecialidad)
	{
		return ConsultaProfesor.asociarEspecialidad(super.getRun(), refEspecialidad);
	}
	
	public boolean desvincularEspecialidadProfesor(int refEspecialidad)
	{
		return ConsultaProfesor.desvincularEspecialidad(super.getRun(), refEspecialidad);
	}
	
	@Override
	public boolean registrarDatos()
	{
		boolean validar = ConsultaUsuario.registrarUsuario(super.getNombre(), super.getRun(), super.getEmail(), super.getClave(), super.getTipoUsuario());
		int cont = 0;
		for (Map.Entry<Integer, Especialidad> especialidad : this.especialidades.entrySet())
		{
			if(asociarEspecialidadProfesor(especialidad.getKey()))
			{
				cont++;
			}
			else
			{
				break;
			}
		}
		
		if(validar && this.especialidades.size()==cont)
		{
			return true;
		}
		return false; 
	}

	@Override
	public String mostrarDatos()
	{
		String datos = "Nombre: " + super.getNombre() + 
			   	"\nEmail: " + super.getEmail() +
			   	"\nClave: " + super.getClave() +
			   	"\nEstado: " + super.getEstado() +
			   	"\nRUN: " + super.getRun() +
			   	"\nTipoUsuario: " + super.getTipoUsuario() +
			   	"\n\nEspecialidades:";
		for (Map.Entry<Integer, Especialidad> especialidad : this.especialidades.entrySet())
		{
			datos+="\n "+especialidad.getValue().getNombre();
		}
		return	datos;
	}
		
}
