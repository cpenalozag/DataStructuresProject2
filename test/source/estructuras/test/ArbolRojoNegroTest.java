package estructuras.test;

import estructuras.ArbolRojoNegro;
import junit.framework.TestCase;

public class ArbolRojoNegroTest extends TestCase {

	ArbolRojoNegro<Integer, String> arbol = new ArbolRojoNegro<>();
	protected void setUp()
	{
		for (int i = 0; i<50; i++){
			arbol.put(i+1, "Nodo"+i);
		}
		
	}
	
	public void testSize(){
		assertEquals(50, arbol.size());
	}
	
	public void testContains(){
		assertTrue(arbol.contains(20));
		assertFalse(arbol.contains(60));
	}
	
	public void testIsEmpty(){
		assertFalse(arbol.isEmpty());
	}

	public void testGet(){
		assertEquals("Nodo20", arbol.get(21));
		assertEquals("Nodo30", arbol.get(31));
		assertNotNull(arbol.get(5));
		assertNull(arbol.get(100));
	}
	
	public void testPut(){
		arbol.put(51, "Nodo51");
		assertEquals(51, arbol.size());
		assertTrue(arbol.contains(51));
	}
	
	public void testDeleteMin(){
		arbol.deleteMin();
		assertEquals(49, arbol.size());
		assertFalse(arbol.contains(1));
	}
	
	public void testDeleteMax(){
		arbol.deleteMax();
		assertEquals(49, arbol.size());
		assertFalse(arbol.contains(51));
	}
	
	public void testDelete(){
		arbol.delete(5);
		assertEquals(49, arbol.size());
		assertFalse(arbol.contains(5));
	}
	
	
	public void testMin(){
		assertEquals(1, (int)arbol.min());
	}
	
	public void testMax(){
		assertEquals(50, (int)arbol.max());
	}
}
