package model;

public class Usuario
{
	private String nombre;
	private String email;
	private String clave;
	private String estado;
	private String run;
	private String tipoUsuario;
	
	public Usuario()
	{
		
	}

	public Usuario(String nombre, String email, String clave, String estado, String run, String tipoUsuario)
	{
		this.nombre = nombre;
		this.email = email;
		this.clave = clave;
		this.estado = estado;
		this.run = run;
		this.tipoUsuario = tipoUsuario;
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

	public String getEstado()
	{
		return estado;
	}

	public void setEstado(String estado)
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

	public String getTipoUsuario()
	{
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario)
	{
		this.tipoUsuario = tipoUsuario;
	}
	
	public void mostrarDatos()
	{
		System.out.println("Nombre: " + this.nombre);
		System.out.println("Email: " + this.email);
		System.out.println("Clave: " + this.clave);
		System.out.println("Estado: " + this.estado);
		System.out.println("RUN: " + this.run);
		System.out.println("tipoUsuario: " + this.tipoUsuario);
	}

}
