package estructuras;

import java.util.NoSuchElementException;

import mundo.IComparator;

public class Cola<T> extends ListaSencillamenteEncadenada<T> {

    /**
     * Initializes an empty queue.
     */
    public Cola(T obj, IComparator<T> comp){
    	super(obj, comp);
    	size = super.size;
    	primero=null;
    }
    
    /**
     * Initializes an empty queue.
     */
    public Cola(){
    	super(null, null);
    	size = super.size;
    	primero=null;
    }

    /**
     * Returns true if this queue is empty.
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return primero == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return size;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return primero.darElemento();
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(T item) {
        this.insert(item);
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public T dequeue() {
    	return (T) this.removerPrimero();
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    /**
	 * Crea un nuevo iterador
	 * @return iterador sencillo
	 */
	public IteradorSencillo<T> darIteradorSencillo()
	{
		return new IteradorSencillo<>(this);
	}
    
}