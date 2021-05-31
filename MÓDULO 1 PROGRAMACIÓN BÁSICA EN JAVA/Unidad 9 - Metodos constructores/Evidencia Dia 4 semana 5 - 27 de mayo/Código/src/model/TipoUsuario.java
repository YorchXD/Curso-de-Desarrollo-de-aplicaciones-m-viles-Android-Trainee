package model;

import java.util.HashMap;
import java.util.Map;

public enum TipoUsuario
{
	ADMINISTRADOR(1), PROFESOR(2), APODERADO(3);
	
	private int id;
    private static final Map<Integer, TipoUsuario> MAP = new HashMap<>();
    
    private TipoUsuario(int id) 
    { 
    	this.id = id; 
    }
    
    public int getId() 
    { 
    	return id; 
    }
    
    public static TipoUsuario getTipoUsuario(int id)
    {
        return MAP.get(id);
    }
    
    static
    {
        for(TipoUsuario n : values())
        {
            MAP.put(n.getId(), n);
        }
    }
}
