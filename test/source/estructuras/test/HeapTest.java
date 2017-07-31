package estructuras.test;

import java.util.Random;

import estructuras.Heap;
import junit.framework.TestCase;
import mundo.IComparator;

public class HeapTest extends TestCase {

	public HeapTest() {

		heap = new Heap<>(15, new ComparadorElementoHeap(), Heap.MAX_HEAP);
	}

	public void testSize(){
		assertEquals(0, heap.size());
		ElementoHeap e1 = new ElementoHeap(134);
		heap.add(e1);
		assertEquals(1, heap.size());
		ElementoHeap e2 = new ElementoHeap(135);
		heap.add(e2);
		assertEquals(2, heap.size());
		ElementoHeap e3 = new ElementoHeap(136);
		heap.add(e3);
		assertEquals(3, heap.size());
		ElementoHeap e4 = new ElementoHeap(137);
		heap.add(e4);
		assertEquals(4, heap.size());
		ElementoHeap e5 = new ElementoHeap(138);
		heap.add(e5);
		assertEquals(5, heap.size());
	}

	public void testResize(){
		assertEquals(16, heap.darCapacidad());
		for (int i = 0; i < 16; i++) {
			Object data = new Integer(getRandom());
			heap.add(new ElementoHeap((int)data));
		}
		assertEquals(32, heap.darCapacidad());
		for (int i = 0; i < 16; i++) {
			Object data = new Integer(getRandom());
			heap.add(new ElementoHeap((int)data));
		}
		assertEquals(64, heap.darCapacidad());
		for (int i = 0; i < 25; i++) {
			heap.poll();
		}
		assertEquals(16, heap.darCapacidad());
	}



	/**
	 * Prueba el método peek
	 */
	public void testPeek() {
		ElementoHeap e1 = new ElementoHeap(401);
		heap.add(e1);
		assertEquals(e1.darDato(), heap.peek().darDato());
		ElementoHeap e2 = new ElementoHeap(402);
		heap.add(e2);
		assertEquals(e2.darDato(), heap.peek().darDato());
		ElementoHeap e3 = new ElementoHeap(403);
		heap.add(e3);
		assertEquals(e3.darDato(), heap.peek().darDato());
		ElementoHeap e4 = new ElementoHeap(404);
		heap.add(e4);
		assertEquals(e4.darDato(), heap.peek().darDato());
	}

	/**
	 * Prueba el método peek
	 */
	public void testPoll() {
		ElementoHeap e2 = new ElementoHeap(502);
		heap.add(e2);

		ElementoHeap e1 = new ElementoHeap(501);
		heap.add(e1);

		ElementoHeap e4 = new ElementoHeap(504);
		heap.add(e4);

		ElementoHeap e3 = new ElementoHeap(503);
		heap.add(e3);

		assertEquals(e4.darDato(), heap.poll().darDato());
		assertEquals(e3.darDato(), heap.poll().darDato());
		assertEquals(e2.darDato(), heap.poll().darDato());
		assertEquals(e1.darDato(), heap.poll().darDato());
	}

	public void testAdd(){
		assertEquals(0, heap.size());
		ElementoHeap e1 = new ElementoHeap(601);
		heap.add(e1);
		assertEquals(e1.darDato(), heap.peek().darDato());
		ElementoHeap e2 = new ElementoHeap(602);
		heap.add(e2);
		assertEquals(e2.darDato(), heap.peek().darDato());
		ElementoHeap e3 = new ElementoHeap(603);
		heap.add(e3);
		assertEquals(e3.darDato(), heap.peek().darDato());
		ElementoHeap e4 = new ElementoHeap(604);
		heap.add(e4);
		assertEquals(e4.darDato(), heap.peek().darDato());
		assertEquals(4, heap.size());
	}


	// Metodos y atributos privados

	private static Random random = new Random();

	private static final int LIMIT = 400;

	private static int getRandom() {
		return random.nextInt(LIMIT);
	}


	private Heap<ElementoHeap> heap;

	protected class ElementoHeap
	{

		private int dato;

		public ElementoHeap( int nDato )
		{
			dato = nDato;
		}

		public int darDato( )
		{
			return dato;
		}
	}

	protected class ComparadorElementoHeap implements IComparator<ElementoHeap>
	{
		public Double compare(ElementoHeap o1, ElementoHeap o2) 
		{
			return (double) -(o1.darDato()-o2.darDato());
		}
	}

}
