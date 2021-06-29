package Model;

import java.util.HashMap;
import java.util.Map;

public enum Tipo_Division_Anual
{
	SEMESTRAL(1), TRIMESTRAL(2), ANUAL(3);
	
	private int id;
    private static final Map<Integer, Tipo_Division_Anual> MAP = new HashMap<>();
    
    private Tipo_Division_Anual(int id) 
    { 
    	this.id = id; 
    }
    
    public int getId() 
    { 
    	return id; 
    }
    
    public static Tipo_Division_Anual getTipoDivAnual(int id)
    {
        return MAP.get(id);
    }
    
    static
    {
        for(Tipo_Division_Anual n : values())
        {
            MAP.put(n.getId(), n);
        }
    }
}
