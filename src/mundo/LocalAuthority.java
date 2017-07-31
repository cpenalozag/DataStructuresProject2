package mundo;

public class LocalAuthority
{
	/**
	 * Codigo de la autoridad local
	 */
	private String codigo;
	
	/**
	 * Nombre del local authority
	 */
	private String nombre;
	
	/**
	 * Prioridad de la autoridad local
	 */
	private Double prioridad;
	
	/**
	 * Inicializa un nuevo local Authority y la lista de wards
	 * @param pNombre Nombre del local authority
	 */
	public LocalAuthority(String pNombre, String pCodigo, Double pPrioridad){
		nombre=pNombre;
		codigo = pCodigo;
		prioridad = pPrioridad;
	}
	
	/**
	 * Retorna el nombre de la autoridad local
	 */
	public String darNombre() {
		return nombre;
	}
	
	/**
	 * Retorna el Codigo de la autoridad local.
	 */
	public String darCodigo(){
		return codigo;
	}
	
	/**
	 * Retorna la prioridad de la autoridad local.
	 */
	public Double darPrioridad(){
		return prioridad;
	}
	
	public String toString()
	{
		return "Local Authority: " + darNombre() + "\n Codigo: " + darCodigo() + "\n Prioridad: " + darPrioridad() + "\n";
	}
}
