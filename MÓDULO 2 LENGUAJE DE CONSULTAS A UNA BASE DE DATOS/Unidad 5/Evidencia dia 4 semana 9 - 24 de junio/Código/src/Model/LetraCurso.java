package Model;

public class LetraCurso
{
	private int id;
	private String letra;
	
	public LetraCurso()
	{
	}
	
	public LetraCurso(int id, String letra)
	{
		this.id = id;
		this.letra = letra;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getLetra()
	{
		return letra;
	}

	public void setLetra(String letra)
	{
		this.letra = letra;
	}
}
