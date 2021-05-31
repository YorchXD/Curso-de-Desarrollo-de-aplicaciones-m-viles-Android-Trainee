package model;

public enum Estado
{
	HABILITADO, DESHABILITADO;
	
	public static Estado buscarEstado(String estado)
	{
		Estado[] estados = Estado.values();
		Estado estadoAux = null;
		for (Estado estadoAux1 : estados)
		{
			if(estado.equals(estadoAux1.toString()))
			{
				estadoAux = estadoAux1;
				break;
			}
		}
		return estadoAux;
	}
}