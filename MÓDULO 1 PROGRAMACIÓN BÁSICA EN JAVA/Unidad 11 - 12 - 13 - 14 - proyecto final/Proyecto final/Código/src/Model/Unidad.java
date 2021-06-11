package Model;

import BD.ConsultaUnidad;
import Utilidades.AccionesPrincipales;

public class Unidad implements AccionesPrincipales
{
	private String nombre;
	private int numero_unidad;
	private int division_anual;
	private Asignatura asignatura;
	private Estado estado;
	private int id;
	
	public Unidad(String nombre, int numero_unidad, int division_anual, Asignatura asignatura)
	{
		this.nombre = nombre;
		this.numero_unidad = numero_unidad;
		this.division_anual = division_anual;
		this.asignatura = asignatura;
		this.estado = Estado.HABILITADO;
	}
	
	public Unidad(int id, String nombre, int numero_unidad, int division_anual, Asignatura asignatura, Estado estado)
	{
		this.id = id;
		this.nombre = nombre;
		this.numero_unidad = numero_unidad;
		this.division_anual = division_anual;
		this.asignatura = asignatura;
		this.estado = estado;
	}

	public String getNombre()
	{
		return nombre;
	}

	public void setNombre(String nombre)
	{
		this.nombre = nombre;
	}

	public int getNumero_unidad()
	{
		return numero_unidad;
	}

	public void setNumero_unidad(int numero_unidad)
	{
		this.numero_unidad = numero_unidad;
	}

	public int getDivision_anual()
	{
		return division_anual;
	}

	public void setDivision_anual(int division_anual)
	{
		this.division_anual = division_anual;
	}

	public Asignatura getAsignatura()
	{
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura)
	{
		this.asignatura = asignatura;
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
	


	
	@Override
	public String mostrarDatos() {
		return "Nombre: " + getNombre() + 
				"\nNumero Unidad: " + getNumero_unidad() + 
				"\nTipo División Anual: " + getDivision_anual()+ 
				"\nEstado: " + getEstado();
	}

	@Override
	public boolean registrarDatos() {
		
		return ConsultaUnidad.registrarUnidad(this.nombre, this.numero_unidad, this.division_anual, this.asignatura.getId());
	}
	

	@Override
	public boolean actualizarDatos() {

		return ConsultaUnidad.actualizarDatosUnidad(this.nombre, this.numero_unidad, this.division_anual, this.id);
	}

	@Override
	public boolean cambiarEstado() {
		return ConsultaUnidad.actualizarEstadoUnidad(this.id, this.estado);
	} 
}
