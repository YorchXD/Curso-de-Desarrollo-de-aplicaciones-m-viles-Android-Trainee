package Model;

import java.util.HashMap;
import java.util.Map;

public enum Tipo_Asistencia
{
	ASISTE(1), INASISTENTE(2);
	
	private int id;
    private static final Map<Integer, Tipo_Asistencia> MAP = new HashMap<>();
    
    private Tipo_Asistencia(int id) 
    { 
    	this.id = id; 
    }
    
    public int getId() 
    { 
    	return id; 
    }
    
    public static Tipo_Asistencia getTipoDivAnual(int id)
    {
        return MAP.get(id);
    }
    
    static
    {
        for(Tipo_Asistencia n : values())
        {
            MAP.put(n.getId(), n);
        }
    }
}
