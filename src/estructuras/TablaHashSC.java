package estructuras;

public class TablaHashSC<K, V> {

	private int n;       // number of key-value pairs
	private int m;       // hash table size
	private Node[] st;   // array of linked-list symbol tables
	private int colisiones;

	// a helper linked list data type
	private static class Node {
		private Object key;
		private Object val;
		private Node next;

		public Node(Object key, Object val, Node next)  {
			this.key  = key;
			this.val  = val;
			this.next = next;
		}
	}

	// create separate chaining hash table with m lists
	public TablaHashSC(int m) {
		this.m = m;
		st = new Node[m];
		colisiones = 0;
	} 
	
	public int darColisiones()
	{
		return colisiones;
	}


	// hash value between 0 and m-1
	private int hash(K key) {
		return (key.hashCode() & 0x7fffffff) % m;
	}

	// return number of key-value pairs in symbol table
	public int size() {
		return n;
	} 

	// is the symbol table empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// is the key in the symbol table?
	public boolean contains(K key) {
		return get(key) != null;
	} 

	// return value associated with key, null if no such key
	public V get(K key) {
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next) 
		{
			if (key.equals(((K)x.key)))
				return ((V) x.val);
		}
		return null;
	}

	// insert key-value pair into the table
	public void put(K key, V val) {
		if (val == null) {
			delete(key);
			return;
		}
		
		int i = hash(key);
		for (Node x = st[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
			colisiones ++;
		}
		
		if (n >= 10*m) resize(2*m);
		
		n++;
		st[i] = new Node(key, val, st[i]);
	}

	// delete key (and associated value) from the symbol table
	public void delete(K key) {
		throw new UnsupportedOperationException("delete not currently supported");
	}
	
	// resizes the hash table to the given capacity by re-hashing all of the keys
			private void resize(int capacity) {
				TablaHashSC<K, V> temp = new TablaHashSC<K, V>(capacity);
				for (int i = 0; i < m; i++) {
					for(Node n = st[i]; n!=null; n=n.next){
						temp.put((K)n.key, (V)n.val);
					}
				}
				this.m = temp.m;
				this.n = temp.n;
				this.st = temp.st;
			}

	// return all keys as an Iterable
	public Iterable<K> keys()  {
		Cola<K> queue = new Cola<K>();
		for (int i = 0; i < m; i++) {
			for (Node x = st[i]; x != null; x = x.next) {
				queue.enqueue((K) x.key);
			}
		}
		return (Iterable<K>) queue;
	}
}