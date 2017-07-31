package estructuras;

import estructuras.ListaSencillamenteEncadenada.NodoSencillo;

public class IteradorSencillo <T>
{
	/**
	 * El nodo donde se encuentra el iterado
	 */
	private NodoSencillo<T> actual;
	
	public IteradorSencillo(ListaSencillamenteEncadenada pLista){
		actual = pLista.darPrimero();
	}

	/**
	 * Indica si a˙n hay elementos por recorrer
	 * @return true en caso de que a˙n haya elemetos o false en caso contrario
	 */
	public boolean hasNext( )
	{
		return actual != null;
	}

	/**
	 * Devuelve el siguiente elemento a recorrer
	 * post: se actualizado actual al siguiente del actual
	 * @return objeto en actual
	 */
	public T next( )
	{
		T valor = (T)actual.darElemento( );
		actual = actual.darSiguiente( );
		return valor;
	}
}

