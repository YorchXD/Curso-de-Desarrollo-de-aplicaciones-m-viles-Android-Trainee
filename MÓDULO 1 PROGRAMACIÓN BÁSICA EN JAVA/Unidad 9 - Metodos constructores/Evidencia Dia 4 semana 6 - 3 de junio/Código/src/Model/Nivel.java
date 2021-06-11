package Model;

import java.util.HashMap;
import java.util.Map;

public enum Nivel
{
	PRIMERO_BASICO(1),
	SEGUNDO_BASICO(2),
	TERCERO_BASICO(3),
	CUARTO_BASICO(4),
	QUINTO_BASICO(5),
	SEXTO_BASICO(6),
	SEPTIMO_BASICO(7),
	OCTAVO_BASICO(8),
	PRIMERO_MEDIO(9),
	SEGUNDO_MEDIO(10),
	TERCERO_MEDIO(11),
	CUARTO_MEDIO(12);
	
	private int id;
    private static final Map<Integer, Nivel> MAP = new HashMap<>();
    
    private Nivel(int id) 
    { 
    	this.id = id; 
    }
    
    public int getId() 
    { 
    	return id; 
    }
    
    public static Nivel getNivel(int id)
    {
        return MAP.get(id);
    }
    
    static
    {
        for(Nivel n : values())
        {
            MAP.put(n.getId(), n);
        }
    }
}
