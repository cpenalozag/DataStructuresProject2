package estructuras;

/**
 *  The {@code LinearProbingHashST} class represents a symbol table of generic
 *  key-value pairs.
 *  It supports the usual <em>put</em>, <em>get</em>, <em>contains</em>,
 *  <em>delete</em>, <em>size</em>, and <em>is-empty</em> methods.
 *  It also provides a <em>keys</em> method for iterating over all of the keys.
 *  A symbol table implements the <em>associative array</em> abstraction:
 *  when associating a value with a key that is already in the symbol table,
 *  the convention is to replace the old value with the new value.
 *  Unlike {@link java.util.Map}, this class uses the convention that
 *  values cannot be {@code null}—setting the
 *  value associated with a key to {@code null} is equivalent to deleting the key
 *  from the symbol table.
 *  <p>
 *  This implementation uses a linear probing hash table. It requires that
 *  the key type overrides the {@code equals()} and {@code hashCode()} methods.
 *  The expected time per <em>put</em>, <em>contains</em>, or <em>remove</em>
 *  operation is constant, subject to the uniform hashing assumption.
 *  The <em>size</em>, and <em>is-empty</em> operations take constant time.
 *  Construction takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/34hash">Section 3.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For other implementations, see {@link ST}, {@link BinarySearchST},
 *  {@link SequentialSearchST}, {@link BST}, {@link RedBlackBST}, and
 *  {@link SeparateChainingHashST},
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */

public class TablaHashLP<K,V> {

	//Estructura que soporta la tabla.

	/**
	 * Arreglo de llaves
	 */
	private K[] keys;
	
	/**
	 * Arreglo de valores
	 */
	private V[] values;


	/**
	 * La cuenta de elementos actuales.
	 */
	private int n;

	/**
	 * La capacidad actual de la tabla. Tamaño del arreglo fijo.
	 */
	private int m;
	/**
	 * Colisiones al meter los datos
	 */
	private int colisiones;

	//Constructor

	@SuppressWarnings("unchecked")
	public TablaHashLP(int capacidad) {
		m = capacidad;
		n = 0;
		keys = (K[])   new Object[m];
		values = (V[]) new Object[m];
		colisiones = 0;
	}
	
	public int darColisiones()
	{
		return colisiones;
	}

	/**
	 * Returns true if this symbol table contains the specified key.
	 *
	 * @param  key the key
	 * @return {@code true} if this symbol table contains {@code key};
	 *         {@code false} otherwise
	 * @throws NullPointerException if {@code key} is {@code null}
	 */
	public boolean contains(K key) {
		if (key == null) throw new NullPointerException("argument to contains() is null");
		return get(key) != null;
	}

	/**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

	/**
     * Returns the value associated with the specified key.
     * @param key the key
     * @return the value associated with {@code key};
     *         {@code null} if no such value
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public V get(K llave) {
        if (llave == null) throw new NullPointerException("argument to get() is null");
        for (int i = hash(llave); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(llave))
                return values[i];
        return null;
    }

	public V delete(K llave){
		if (llave == null) throw new NullPointerException("argument to delete() is null");
		if (!contains(llave)) return null;

		// find position i of key
		int i = hash(llave);
		while (!llave.equals(keys[i])) {
			i = (i + 1) % m;
		}

		// delete key and associated value
		V resp = values[i];
		keys[i] = null;
		values[i] = null;

		// rehash all keys in same cluster
		i = (i + 1) % m;
		while (keys[i] != null) {
			// delete keys[i] an vals[i] and reinsert
			K keyToRehash = keys[i];
			V valToRehash = values[i];
			keys[i] = null;
			values[i] = null;
			n--;
			put(keyToRehash, valToRehash);
			i = (i + 1) % m;
		}

		n--;

		// halves size of array if it's 12.5% full or less
		if (n > 0 && n <= m/8) resize(m/2);

		assert check();

		return resp;
	}

	//Hash
	private int hash(K llave)
	{
		return (llave.hashCode() & 0x7fffffff) % m;
	}

	// resizes the hash table to the given capacity by re-hashing all of the keys
		private void resize(int capacity) {
			TablaHashLP<K, V> temp = new TablaHashLP<K, V>(capacity);
			for (int i = 0; i < m; i++) {
				if (keys[i] != null) {
					temp.put(keys[i], values[i]);
				}
			}
			keys = temp.keys;
			values = temp.values;
			m = temp.m;
		}

		public void put(K llave, V valor){
			if (llave == null) throw new NullPointerException("first argument to put() is null");

			if (valor == null) {
				delete(llave);
				return;
			}

			if (n >= m/2) resize(2*m);

			if(keys[hash(llave)] != null)
				colisiones++;
				
			int i;
			for (i = hash(llave); keys[i] != null; i = (i + 1) % m) {
				if (keys[i].equals(llave)) {
					values[i] = valor;
					return;
				}
			}
			keys[i] = llave;
			values[i] = valor;
			n++;
		}

	    // integrity check - don't check after each put() because
	    // integrity not maintained during a delete()
	    private boolean check() {

	        // check that hash table is at most 50% full
	        if (m < 2*n) {
	            System.err.println("Hash table size m = " + m + "; array size n = " + n);
	            return false;
	        }

	        // check that each key in table can be found by get()
	        for (int i = 0; i < m; i++) {
	            if (keys[i] == null) continue;
	            else if (get(keys[i]) != values[i]) {
	                System.err.println("get[" + keys[i] + "] = " + get(keys[i]) + "; vals[i] = " + values[i]);
	                return false;
	            }
	        }
	        return true;
	    }
}