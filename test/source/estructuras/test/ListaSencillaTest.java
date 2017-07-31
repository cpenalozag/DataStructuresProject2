package estructuras.test;

import estructuras.ListaSencillamenteEncadenada;
import junit.framework.TestCase;
import mundo.IComparator;

public class ListaSencillaTest extends TestCase
{
	protected ListaSencillamenteEncadenada<ElementoBasico> lista = new ListaSencillamenteEncadenada<>(new ComparadorElementoBasico());


	public void testAdd()
	{
		lista.insert( new ElementoBasico( "A" ) );
		lista.insert( new ElementoBasico( "B" ) );
		lista.insert( new ElementoBasico( "C" ) );
		lista.insert( new ElementoBasico( "D" ) );
		ElementoBasico elem = lista.removerPrimero();
		assertEquals( "A", elem.darIdentificador( ) );
		elem = lista.removerPrimero();
		assertEquals( "B", elem.darIdentificador( ) );
		elem = lista.removerPrimero();
		assertEquals( "C", elem.darIdentificador( ) );
	}

	public void testClear( )
	{
		lista.insert( new ElementoBasico( "B" ) );
		lista.insert( new ElementoBasico( "C" ) );
		lista.insert( new ElementoBasico( "D" ) );
		assertEquals(false, lista.isEmpty());
		lista.clear( );

		assertTrue( lista.isEmpty( ) );

	}


	public void testGet(  )
	{
		try
		{
			lista.get( -2 );
			fail("Debe lanzar la excepci�n");
		}
		catch( Exception e )
		{
			//Debe lanzar la excepci�n
		}
		try
		{
			lista.get(100);
			fail("Debe lanzar la excepci�n");
		}
		catch( Exception e )
		{
			//Debe lanzar la excepci�n
		}

		ElementoBasico b = new ElementoBasico( "B" );
		ElementoBasico c = new ElementoBasico( "C" );
		ElementoBasico d = new ElementoBasico( "D" );
		lista.insert( b );
		lista.insert( c );
		lista.insert( d );

		assertEquals( "C",lista.get( 1 ).darIdentificador( ));
	}

	public void testIndexOf( )
	{
		ElementoBasico a = new ElementoBasico( "A" );
		ElementoBasico b = new ElementoBasico( "B" );
		ElementoBasico c = new ElementoBasico( "C" );
		ElementoBasico d = new ElementoBasico( "D" );
		lista.insert( b );
		lista.insert( c );
		lista.insert( d );

		assertEquals( -1, lista.indexOf( a) );
		assertEquals( 1, lista.indexOf( c) );

	}

	public void testIsEmpty( )
	{
		assertTrue( lista.isEmpty( ) );
		ElementoBasico d = new ElementoBasico( "D" );
		lista.insert( d );
		assertFalse( lista.isEmpty( ) );

	}


	public void testRemove( )
	{
		ElementoBasico a = new ElementoBasico( "A" );   
		ElementoBasico b = new ElementoBasico( "B" );
		ElementoBasico c = new ElementoBasico( "C" );
		ElementoBasico d = new ElementoBasico( "D" );
		lista.insert( a );
		lista.insert( b );
		lista.insert( c );
		lista.insert( d );

		assertEquals( 4, lista.size( ) );

		ElementoBasico r = new ElementoBasico(null);
		assertEquals (a, r = lista.removerPrimero());
		assertEquals( 3, lista.size( ) );

		ElementoBasico s = new ElementoBasico(null);
		assertEquals (b, s = lista.removerPrimero());
		assertEquals( 2, lista.size( ) );

		ElementoBasico t = new ElementoBasico(null);
		assertEquals (c, t = lista.removerPrimero());
		assertEquals( 1, lista.size( ) );

		lista.removerPrimero();

		assertEquals( 0, lista.size( ) );

	}

	public void testIterador( )
	{

	}

	public void testSize( )
	{
		lista.clear();
		ElementoBasico a = new ElementoBasico( "A" );
		ElementoBasico b = new ElementoBasico( "B" );
		ElementoBasico c = new ElementoBasico( "C" );
		ElementoBasico d = new ElementoBasico( "D" );

		assertEquals( 0, lista.size( ) );
		
		lista.insert( a );
		lista.insert( b );
		lista.insert( c );
		lista.insert( d );

		assertEquals( 4, lista.size( ) );

		lista.removerPrimero();

		assertEquals( 3, lista.size( ) );

	}

	protected class ElementoBasico
	{
		private static final long serialVersionUID = 1L;

		private String valor;

		public ElementoBasico( String nValor )
		{
			valor = nValor;
		}

		public String darIdentificador( )
		{
			return valor;
		}

	}

	protected class ComparadorElementoBasico implements IComparator<ElementoBasico>
	{
		public Double compare(ElementoBasico o1, ElementoBasico o2) 
		{
			return (double) (o1.valor.compareTo(o2.valor));
		}
	}
}
