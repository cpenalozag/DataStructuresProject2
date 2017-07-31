package estructuras;

import mundo.IComparator;

/**
 * Clase que representa una lista sencillamente encadenada
 * @param <T>
 */
public class ListaSencillamenteEncadenada<T>
{

	/**
	 * Primer nodo de la lista.
	 */
	protected NodoSencillo<T> primero;
	/**
	 * Tamaño de la lista.
	 */
	protected int size;	
	/**
	 * Comparador elementos
	 */
	protected IComparator<T> comparador;

	/**
	 * Constructor nodo lista sencillamente encadenado.
	 * @param <T> Elemento del nodo
	 */
	public static class NodoSencillo <T>
	{
		/**
		 * Crea un nuevo nodo
		 * @param nElemento el elemento a almacenar en el nodo. nElemento != null
		 */
		public NodoSencillo(T nElemento)
		{

			elemento = nElemento;
			siguiente=null;
		}
		/**
		 * Elemento alamacenado en el nodo
		 */
		protected T elemento;

		/**
		 * Siguiente nodo
		 */
		protected NodoSencillo<T> siguiente;

		/**
		 * Cambia el nodo siguiente
		 * post: Se ha cambiado el nodo siguiente
		 * @param nSiguiente el nuevo elemento siguiente
		 */
		public void cambiarSiguiente(NodoSencillo<T> nSiguiente)
		{
			siguiente = nSiguiente;
		}

		/**
		 * Devuelve el elemento almacenado en el nodo
		 * @return elemento
		 */
		public T darElemento()
		{
			return elemento;
		}

		/**
		 * Cambia el elemento almacenado en el nodo
		 * @param nElemento el nuevo elemento a almacenar en el nodo
		 */
		public void cambiarElemento(T nElemento)
		{
			elemento = nElemento;
		}

		/**
		 * Devuelve el identificador del nodo
		 * Corresponde al identificador del elemento almacenado
		 * @return identificador
		 */
		public String darIdentificador()
		{
			return elemento.toString();
		}

		/**
		 * Devuelve el siguiente nodo
		 * @return siguiente
		 */
		public NodoSencillo<T> darSiguiente()
		{
			return siguiente;
		}
	}

	/**
	 * Se construye una nueva lista cuyo primer nodo  guardar� al elemento que llega por par�mentro
	 * @param nPrimero el elemento a guardar en el primer nodo
	 * 
	 * @throws NullPointerException si el elemento recibido es nulo
	 */
	public ListaSencillamenteEncadenada(T nPrimero, IComparator<T> pComparador)
	{
		if(nPrimero == null)
		{
			primero = null;
		}
		else{
			primero = new NodoSencillo<T>( nPrimero );
		}

		this.comparador = pComparador;
	}

	public ListaSencillamenteEncadenada(IComparator<T> pComparador)
	{
		primero = null;
		this.comparador = pComparador;
	}

	public ListaSencillamenteEncadenada(T nPrimero)
	{
		if(nPrimero == null)
		{
			primero = null;
		}
		else{
			primero = new NodoSencillo<T>( nPrimero );
		}
	}

	/**
	 * Retorna el primer elemento de la lista
	 */
	public NodoSencillo darPrimero(){
		return primero;
	}

	/**
	 * Retorna el tamaño de la lista
	 * @return Tamaño de la lista sencillamente encadenada.
	 */
	public int size ()
	{
		return size;
	}

	/**
	 * Indica si la lista est� vacia
	 * @return true si la lista est� vacia o false en caso contrario
	 */
	public boolean isEmpty( )
	{
		return primero == null;
	}
	public String toString()
	{
		String cadena= "";
		NodoSencillo<T> ms = primero;
		while(ms!= null)
		{
			cadena+= ms.darIdentificador()+";";
			ms = ms.siguiente;
		}
		return cadena;
	}
	/**
	 * Agrega un elemento al final de la lista
	 * Un elemento no se agrega si la lista ya tiene un elemento con el mismo id
	 * @param elem el elemento que se desea agregar.
	 * @return true en caso que se agregue el elemento o false en caso contrario. 
	 * @throws NullPointerException si el elemento es nulo
	 */

	public void insert(T elem) {
		if(elem == null)
		{
			throw new NullPointerException( );
		}
		NodoSencillo<T> nodo = primero;
		primero = insert(nodo, elem);
	}

	private NodoSencillo<T> insert(NodoSencillo<T> nodo, T elem) {

		if (comparador==null){
			if(null == nodo) {
				NodoSencillo<T> nuevo =  createNewNode(elem);
				return nuevo;
			}
			else {
				nodo.siguiente = insert(nodo.siguiente, elem);
			}
		}
		else{
			if(null == nodo) {
				NodoSencillo<T> nuevo =  createNewNode(elem);
				return nuevo;
			}
			else if(comparador.compare(nodo.elemento, elem) > 0) {
				NodoSencillo<T> nuevo =  createNewNode(elem);
				nuevo.siguiente = nodo;
				nodo = nuevo;
			} 
			else {
				nodo.siguiente = insert(nodo.siguiente, elem);
			}
		}
		return nodo;
	}

	private NodoSencillo<T> createNewNode(T item) {
		size++;
		return new NodoSencillo<T>(item);
	}


	/**
	 * Elimina el primer nodo y retorna su elemento
	 */
	public T removerPrimero( )
	{
		T resp = null;
		if(primero != null)
		{
			if (primero.darSiguiente()==null)
			{
				resp = primero.darElemento();
				primero = null;
				size--;
			}
			else{
				resp = primero.darElemento();
				primero = primero.darSiguiente();
				size--;
			}
		}
		return resp;
	}

	/**
	 * Borra todos los elementos de la lista
	 * post: el primer elemento es nulo
	 */
	public void clear( )
	{
		primero = null;
		size = 0;
	}

	/**
	 * Devuelve el elemento de la posici�n dada
	 * @param pos la posici�n  buscada
	 * @return el elemento en la posici�n dada 
	 * @throws IndexOutOfBoundsException si pos < 0 o pos >= size()
	 */
	public T get( int pos ) throws IndexOutOfBoundsException
	{
		T resp = null; 
		NodoSencillo<T> x = null;
		if ( pos < 0 || pos >= size() )
		{
			throw new IndexOutOfBoundsException( );
		}
		if (pos == size()){
			resp = primero.darElemento( );
		}
		if (pos<=size()){
			int i = 0;
			x = primero;
			while (i<pos){
				x = x.darSiguiente();
				i++;
			}
		}
		return x.darElemento();
	}

	/**
	 * Agrega el elemento que entra por parametro al nodo en la posicion indicada
	 * @param pos Posicion del nodo
	 * @param elem Elemento que se agrega al nodo
	 */
	public void set (int pos, T elem){
		int i = 0;
		NodoSencillo<T> nodo = primero;
		while (nodo!=null){
			if (i == pos){
				nodo.cambiarElemento(elem);
				break;
			}
			i++;
			nodo = nodo.darSiguiente();
		}
	}

	/**
	 * Indica la posici�n del objeto que llega por par�metro en la lista
	 * @param objeto el objeto que se desea buscar en la lista. objeto != null
	 * @return la posici�n del objeto o -1 en caso que no se encuentre en la lista
	 */
	public int indexOf( Object objeto )
	{
		int respuesta = -1;
		boolean termino = false;
		if ( objeto!=null )
		{
			for ( int i  = 0; i < size && !termino; i++ )
			{
				T actual = get( i );
				if ( actual == objeto )
				{
					termino = true;
					respuesta = i;

				}
			}
		}

		return respuesta;
	}

	/**
	 * Crea un nuevo iterador
	 * @return iterador sencillo
	 */
	public IteradorSencillo<T> darIteradorSencillo()
	{
		return new IteradorSencillo<>(this);
	}
}
