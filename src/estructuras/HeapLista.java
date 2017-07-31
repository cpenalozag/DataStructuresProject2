package estructuras;


import estructuras.ListaSencillamenteEncadenada.NodoSencillo;
import mundo.IComparator;


public class HeapLista<T> implements IHeap<T> {
	private ListaSencillamenteEncadenada<T> lista;

	public HeapLista(IComparator<T> comparator) {
		lista = new ListaSencillamenteEncadenada(comparator);
	}

	public void remove() {
		lista.removerPrimero();
	}

	public void add(T elemento) {
		lista.insert(elemento);
	}

	public T peek() {
		return (T) lista.darPrimero().darElemento();
	}

	public int size() {
		return lista.size();
	}

	public boolean isEmpty() {
		return lista.isEmpty();
	}

	public T poll() {
		T resp = lista.removerPrimero();
		return resp;
	}

	@Override
	public void siftUp() {

	}

	@Override
	public void siftDown() {

	}

	public ListaSencillamenteEncadenada<T> darLista(){
		return lista;
	}
}