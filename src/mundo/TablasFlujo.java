package mundo;

import estructuras.TablaHashLP;
import estructuras.TablaHashSC;

public class TablasFlujo {
	/**
	 * Código autoridad local.
	 */
	private String codigo;
	
	/**
	 * Tabla flujo todos los vehículos parte A
	 */
	private TablaHashLP<Integer, Integer> tablaFlujoTodosA;
	
	/**
	 * Tabla flujo carros parte A
	 */
	private TablaHashSC<Integer, Integer> tablaFlujoCarrosA;
	
	/**
	 * Tabla flujo carros parte A
	 */
	private TablaHashLP<Integer, Integer> tablaFlujoCarrosB;
	
	/**
	 * Tabla flujo todos los vehículos parte A
	 */
	private TablaHashSC<Integer, Integer> tablaFlujoTodosB;
	
	/**
	 * Construye una nueva coleccion de tablas de flujos para la parte A y la B 
	 * @param pCodigo Codigo de la autoridad local que contiene estas tablas
	 */
	public TablasFlujo(String pCodigo) {
		codigo = pCodigo;
		tablaFlujoCarrosA = new TablaHashSC<Integer, Integer>(29);
		tablaFlujoTodosA = new TablaHashLP<>(29);
		tablaFlujoCarrosB = new TablaHashLP<>(29);
		tablaFlujoTodosB = new TablaHashSC<>(29);
	}

	/**
	 * Devuelve el codigo de la autoridad local que contiene las tablas de flujos
	 * @return Codigo de la autoridad local que contiene las tablas de flujos
	 */
	public String darCodigo()
	{
		return codigo;
	}
	
	/**
	 * Tabla que contiene los flujos de todos los vehiculos de la parte A
	 * @return Tabla de hash con la informacion del flujo de todos los vehiculos
	 */
	public TablaHashLP<Integer, Integer> darTablaFlujoTodosA()
	{
		return tablaFlujoTodosA;
	}
	
	/**
	 * Tabla que contiene los flujos de todos carros de la parte A
	 * @return Tabla de hash con la informacion del flujo de carros.
	 */
	public TablaHashSC<Integer, Integer> darTablaFlujoCarrosA()
	{
		return tablaFlujoCarrosA;
	}
	
	/**
	 * Tabla que contiene los flujos de todos carros de la parte B
	 * @return Tabla de hash con la informacion del flujo de carros.
	 */
	public TablaHashLP<Integer, Integer> darTablaFlujoCarrosB()
	{
		return tablaFlujoCarrosB;
	}
	
	/**
	 * Tabla que contiene los flujos de todos los vehiculos de la parte B
	 * @return Tabla de hash con la informacion del flujo de todos los vehiculos
	 */
	public TablaHashSC<Integer, Integer> darTablaFlujoTodosB()
	{
		return tablaFlujoTodosB;
	}
}
