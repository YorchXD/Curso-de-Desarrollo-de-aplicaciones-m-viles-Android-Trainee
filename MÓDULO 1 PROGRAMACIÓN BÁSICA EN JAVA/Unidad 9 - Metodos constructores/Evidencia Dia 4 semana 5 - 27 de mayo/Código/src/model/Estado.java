package model;

import java.util.HashMap;
import java.util.Map;

public enum Estado
{
	HABILITADO(1), DESHABILITADO(2);
	
	private int id;
    private static final Map<Integer, Estado> MAP = new HashMap<>();
    
    private Estado(int id) 
    { 
    	this.id = id; 
    }
    
    public int getId() 
    { 
    	return id; 
    }
    
    public static Estado getEstado(int id)
    {
        return MAP.get(id);
    }
    
    static
    {
        for(Estado n : values())
        {
            MAP.put(n.getId(), n);
        }
    }
}