package Model;

import BD.ConsultaUsuario;
import Utilidades.AccionesPrincipales;

public class Usuario implements AccionesPrincipales
{
	private String nombre;
	private String email;
	private String clave;
	private Estado estado;
	private String run;
	private TipoUsuario tipoUsuario;
	
	public Usuario()
	{
	}

	public Usuario(String nombre, String email, String clave, Estado estado, String run, TipoUsuario tipoUsuario)
	{
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.estado = estado;
		this.run = run;
		this.tipoUsuario = tipoUsuario;
	}
	
	public String mostrarDatos()
	{
		return	"Nombre: " + this.nombre + 
			   	"\nEmail: " + this.email +
			   	"\nClave: " + this.clave +
			   	"\nEstado: " + this.estado +
			   	"\nRUN: " + this.run +
			   	"\nTipoUsuario: " + this.tipoUsuario;
	}

	public boolean registrarDatos()
	{
		return ConsultaUsuario.registrarUsuario(this.nombre, this.run, this.email, this.clave, this.tipoUsuario, null);
	}

	public boolean cambiarEstado()
	{
		return ConsultaUsuario.cambiarEstado(this.run, this.estado, this.tipoUsuario);
	}

	public boolean actualizarDatos()
	{
		return ConsultaUsuario.actualizarDatos(this.nombre, this.email, this.clave, null, this.run, this.tipoUsuario);
	}
	
	
	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getClave()
	{
		return clave;
	}

	public void setClave(String clave)
	{
		this.clave = clave;
	}

	public Estado getEstado()
	{
		return estado;
	}

	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}

	public String getRun()
	{
		return run;
	}

	public void setRun(String run)
	{
		this.run = run;
	}

	public TipoUsuario getTipoUsuario()
	{
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}
	

	public boolean registrarCuenta()
	{
		return ConsultaUsuario.registrarCuenta(this.run, this.email, this.clave, this.tipoUsuario, null);
	}

}
