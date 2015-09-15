package myp;

public class StringTokenizer{
    
    private Token[] tokens;
    private int index;        

    /**
     * Constructor que recibe una cadena.
     * @param la cadena que se va a 'tokenizar'.
     */
    public StringTokenizer(String expression){	
	index = 0;
	tokens = tokenize(expression);
    }	

    /**
     * Calcula el número de veces que el método 
     * {@link #nextToken() getComponentAt} puede ser llamado
     * antes de que genere una excepción.
     *
     * @return el numero de tokens restante de la cadena.
     * 
     */    
    public int countTokens(){
	return 0;
    }
    
    /**
     * Prueba si hay más tokens disponibles de la cadena 'tokenizada'.
     *
     * @return <tt>true</tt> si y solo si hay al menos un token en la cadena
     * después de la posición actual; <tt>falso</tt> en otro caso.
     * 
     */    
    public boolean hasMoreTokens(){
	return false;
    }

    /**
     * Regresa el siguiente token de este 'tokenizador' de cadenas.
     *
     * @return el siguiente token de este 'tokenizador'.
     * 
     * @throws NoSuchElementException si no hay más tokens.
     * 
     */    
    public Token nextToken(){
	return null;
    }

    /**
     *
     * Regresa a la primera posición del tokenizador.     
     * 
     */
    public void start(){

    }
    
    /**
     * Método que convierte una cadena dada en un arreglo de tokens.
     *
     * @param expression es la cadena que queremos tokenizar
     * 
     * @return la cadena tokenizada
     * 
     */    
    private Token[] tokenize(String expression){
	return null;
    }
}
