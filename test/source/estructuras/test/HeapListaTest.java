package estructuras.test;

import java.util.Random;

import estructuras.Heap;
import estructuras.HeapLista;
import junit.framework.TestCase;
import mundo.IComparator;

public class HeapListaTest extends TestCase {

	public HeapListaTest() {

		heap = new HeapLista( new ComparadorElementoHeap() );
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


	// Metodos y atributos privados

	private static Random random = new Random();

	private static final int LIMIT = 400;

	private static int getRandom() {
		return random.nextInt(LIMIT);
	}


	private HeapLista<ElementoHeap> heap;

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
