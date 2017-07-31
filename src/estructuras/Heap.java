package estructuras;

import java.util.NoSuchElementException;

import mundo.IComparator;

public class Heap<T> implements IHeap<T>{

	public final static String MIN_HEAP = "Min Heap";
	public final static String MAX_HEAP = "Max Heap";

	private T[] pq;
	private String tipo;
	private int n;
	private IComparator<T> comparator;
	static int N;

	/**
	 * Inicializa una cola de prioridad vacía con la capacidad inicial dada por parámetro.
	 * @param  capacidadIncial la capacidad inicial de la cola de prioridad
	 */
	public Heap(int capacidadInical){
		pq = (T[]) new Object[capacidadInical + 1];
		n = 0;
	}

	/**
	 * Inicializa una cola de prioridad vacía.
	 */
	public Heap() {
		this(1);
	}

	/**
	 * Inicializa una cola de prioridad vacía con la capacidad inicial dada por parámetro, con el comparador que entra por parámetro.
	 * @param capacidadInicial la capacidad inicial de la cola de prioridad
	 * @param comparator comparador
	 * @param tipo tipo de heap
	 */
	public Heap(int capacidadInicial, IComparator<T> comparator, String pTipo) {
		this.comparator = comparator;
		pq = (T[]) new Object[capacidadInicial + 1];
		tipo = pTipo;
		n = 0;
	}

	/**
	 * Retorna true si la cola de prioridad está vacía.
	 * @return true si la cola de prioridad está vacía
	 * @return false si la cola de prioridad no está vacía
	 */
	public boolean isEmpty() {
		return n == 0;
	}

	/**
	 * Retorna el numero de elementos en la cola de prioridad
	 * @return numero de elementos en la llave de prioridad
	 */
	public int size() {
		return n;
	}
	
	/**
	 * Retorna la capacidad del arreglo de objetos
	 * @return capacidad arreglo de la cola de prioridad
	 */
	public int darCapacidad(){
		return pq.length;
	}

	/**
	 * Duplica el tamaño del arreglo
	 */
	private void resize(int capacidad) {
		assert capacidad > n;
		T[] temp = (T[]) new Object[capacidad];
		for (int i = 1; i <= n; i++) {
			temp[i] = pq[i];
		}
		pq = temp;
	}

	public void add(T elemento) {
		if (n >= pq.length - 1) resize(2 * pq.length);
		pq[++n] = elemento;
		siftUp();
		if (tipo == MAX_HEAP){
			assert isMaxHeap();
		}
		else if (tipo == MIN_HEAP){
			assert isMinHeap();
		}
	}

	public T peek() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		return pq[1];
	}

	public T poll() {
		if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
		T maxOmin = pq[1];
		exch(1, n--);
		siftDown();
		pq[n+1] = null;
		if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
		assert isMaxHeap();
		return maxOmin;
	}

	public void siftUp() {
		swim(n);
	}

	public void siftDown() {
		sink(1);
	}

	// -------------------------------------------------------------
	// Métodos para mantener la invariante de la cola de prioridad
	// -------------------------------------------------------------
	private void swim(int k) {
		if (tipo == MIN_HEAP){
			while (k > 1 && menor(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			}
		}
		else if (tipo == MAX_HEAP){
			while (k > 1 && mayor(k/2, k)) {
				exch(k, k/2);
				k = k/2;
			}
		}
	}

	private void sink(int k) {
		if (tipo == MIN_HEAP){
			while (2*k <= n) {
				int j = 2*k;
				if (j < n && menor(j, j+1)) j++;
				if (!menor(k, j)) break;
				exch(k, j);
				k = j;
			}
		}
		else if (tipo == MAX_HEAP){
			while (2*k <= n) {
				int j = 2*k;
				if (j < n && mayor(j, j+1)) j++;
				if (!mayor(k, j)) break;
				exch(k, j);
				k = j;
			}
		}
	}

	// -------------------------------------------------------------
	// Métodos para comparar e intercambiar elementos
	// -------------------------------------------------------------

	/**
	 * Verifica si el primer elemento es menor al segundo
	 * @param i Posición del primer elemento
	 * @param j Posición del segundo elemento
	 * @return true si el menor es mayor al segundo
	 */
	private boolean menor(int i, int j) {
		if (comparator == null) {
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) < 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) < 0;
		}
	}

	/**
	 * Verifica si el primer elemento es mayor al segundo
	 * @param i Posición del primer elemento
	 * @param j Posición del segundo elemento
	 * @return true si el primero es mayor al segundo
	 */
	private boolean mayor(int i, int j) {
		if (comparator == null) {
			return ((Comparable<T>) pq[i]).compareTo(pq[j]) > 0;
		}
		else {
			return comparator.compare(pq[i], pq[j]) > 0;
		}
	}

	/**
	 * Intercambia los elementos en posiciones dadas
	 * @param i posición elemento a intercabiar
	 * @param j posición elemento a intercabiar
	 */
	private void exch(int i, int j) {
		T intercambio = pq[i];
		pq[i] = pq[j];
		pq[j] = intercambio;
	}

	/**
	 * Verifica si es un max heap
	 * @return true si cumple con las caracteristicas de un max heap
	 */
	private boolean isMaxHeap() {
		return isMaxHeap(1);
	}

	/**
	 * Verifica si el sub arbol con raiz en k es un max heap
	 * @param k posición del elemento raiz del sub arbol
	 * @return true si el subarbol cumple con las caracteristicas de un max heap
	 */
	private boolean isMaxHeap(int k) {
		if (k > n) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= n && menor(k, left))  return false;
		if (right <= n && menor(k, right)) return false;
		return isMaxHeap(left) && isMaxHeap(right);
	}

	/**
	 * Verifica si es un min heap
	 * @return true si cumple con las caracteristicas de un min heap
	 */
	private boolean isMinHeap() {
		return isMinHeap(1);
	}

	/**
	 * Verifica si el sub arbol con raiz en k es un min heap
	 * @param k posición del elemento raiz del sub arbol
	 * @return true si el subarbol cumple con las caracteristicas de un min heap
	 */
	private boolean isMinHeap(int k) {
		if (k > n) return true;
		int left = 2*k;
		int right = 2*k + 1;
		if (left  <= n && mayor(k, left))  return false;
		if (right <= n && mayor(k, right)) return false;
		return isMinHeap(left) && isMinHeap(right);
	}
	
	public T[] heapSort()
	{
		T[] resp = (T[])new Object[size()];
		for(int i = 0; i < size(); i ++)
		{
			resp[i] = poll();
		}
		return resp;
	}
	
	// ----------------------------------
	// Iterador
	// ----------------------------------
	
	public HeapIterator darIterador(){
		return new HeapIterator();
	}
	/**
     * Clase que representa un iterador que itera sobre los elementos de la cola de prioridad en 
     * orden ascendente/descendente
     */
    public class HeapIterator {
    	
        private Heap<T> copia;

        public HeapIterator() {
            if (comparator == null){
            	copia = new Heap<T>(size());
            }
            else{
            	copia = new Heap<T>(size(), comparator, tipo);
            }
            for (int i = 1; i <= n; i++)
                copia.add(pq[i]);
        }

        public boolean hasNext()  { return !copia.isEmpty();                     }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copia.poll();
        }
    }
	
    
}
