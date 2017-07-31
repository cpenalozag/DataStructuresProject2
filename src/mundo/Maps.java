package mundo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;

import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import estructuras.ArbolRojoNegro;
import estructuras.Cola;
import estructuras.Heap;
import estructuras.HeapLista;
import estructuras.IteradorSencillo;
import estructuras.ListaSencillamenteEncadenada;
import estructuras.ListaSencillamenteEncadenada.NodoSencillo;
import estructuras.TablaHashLP;
import estructuras.TablaHashSC;
import estructuras.Heap.HeapIterator;

public class Maps {
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de peso por a�o.
	 */
	public final static String PESO_POR_ANHO = "./data/road-casualties-severity-borough-years-weight.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de accidentes.
	 */
	public final static String ARCHIVO_BOROUGH = "./data/road-casualties-severity-borough.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de accidentes con ni�os.
	 */
	public final static String ARCHIVO_BOROUGH_NINHOS = "./data/road-casualties-severity-borough-child.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de tráfico.
	 */
	public final static String FLUJO_TRAFICO_TODOS = "./data/traffic-flow-borough-all.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de tráfico carros.
	 */
	public final static String FLUJO_TRAFICO_CARROS = "./data/traffic-flow-borough-cars.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos deL peso de tráfico.
	 */
	public final static String FLUJO_TRAFICO_ANHOS_PESO = "./data/traffic-flow-borough-years-weight.json";

	/**
	 * Constante que representa la ubicaci�n del archivo con los datos de la parte 1C.
	 */
	public final static String ARCHIVO_ACCIDENTES_CIUDADES = "./data/data.json";

	// -------------------------------------------------------------
	// Atrinutos
	// -------------------------------------------------------------
	//Parte A
	/**
	 * Cola prioridad 1A
	 */
	private Heap<LocalAuthority> punto1A;

	/**
	 * Hash Table 2A 
	 */
	private TablaHashSC<String, TablaHashLP<Integer, Integer>> tablaDatosA;

	/**
	 * Arbol rojo negro 3A
	 */
	private ArbolRojoNegro<String, String> arbol3Anombres;

	private ArbolRojoNegro<String, TablasFlujo> arbol3AFlujo;

	//Parte B
	/**
	 * Cola de prioridad 1B
	 */
	private HeapLista<LocalAuthority> punto1B;

	/**
	 * Tabla de hash linear probing
	 */
	private TablaHashLP<String, TablaHashSC<Integer, Integer>> tablaDeTablasB;

	/**
	 * Arbol rojo negro 3B con los nombres y codigos de las autoridades locales
	 */
	private ArbolRojoNegro<String, String> arbol3BNombreCodigo;

	/**
	 * Arbol rojo negro 3B
	 */
	private ArbolRojoNegro<String, TablasFlujo> arbol3BCodigoFlujo;

	private DefaultCategoryDataset infoc1;

	private DefaultCategoryDataset infoc21;

	private DefaultCategoryDataset infoc22;


	// -------------------------------------------------------------
	// Constructor
	// -------------------------------------------------------------

	/**
	 * Construye un nuevo sistema Maps.<br>
	 * <b> post: </b> El sistema de Maps esta inicializado
	 */

	public Maps(){
		punto1A = new Heap<LocalAuthority>(53, new ComparatorPrioridad(), Heap.MAX_HEAP);
		tablaDatosA = new TablaHashSC<>(53);

		punto1B = new HeapLista<LocalAuthority>(new ComparatorPrioridad());
		tablaDeTablasB = new TablaHashLP<>(101);

		leerArchivoAccidentes(ARCHIVO_BOROUGH, PESO_POR_ANHO);
		leerArchivoAccidentes(ARCHIVO_BOROUGH_NINHOS, PESO_POR_ANHO);

		arbol3Anombres = new ArbolRojoNegro<String, String>();
		arbol3AFlujo = new ArbolRojoNegro<String, TablasFlujo>();

		arbol3BNombreCodigo = new ArbolRojoNegro<>();
		arbol3BCodigoFlujo = new ArbolRojoNegro<>();

		infoc1 = new DefaultCategoryDataset();

		leerArchivoFlujos(FLUJO_TRAFICO_CARROS, FLUJO_TRAFICO_TODOS);
	}
	
	/**
	 * Construye un nuevo sistema Maps.<br>
	 * <b> post: </b> El sistema de Maps esta inicializado
	 */

	public Maps(int tam){
		punto1A = new Heap<LocalAuthority>(tam, new ComparatorPrioridad(), Heap.MAX_HEAP);
		tablaDatosA = new TablaHashSC<>(tam);

		punto1B = new HeapLista<LocalAuthority>(new ComparatorPrioridad());
		tablaDeTablasB = new TablaHashLP<>(tam);

		leerArchivoAccidentes(ARCHIVO_BOROUGH, PESO_POR_ANHO);
		leerArchivoAccidentes(ARCHIVO_BOROUGH_NINHOS, PESO_POR_ANHO);

		arbol3Anombres = new ArbolRojoNegro<String, String>();
		arbol3AFlujo = new ArbolRojoNegro<String, TablasFlujo>();

		arbol3BNombreCodigo = new ArbolRojoNegro<>();
		arbol3BCodigoFlujo = new ArbolRojoNegro<>();

		infoc1 = new DefaultCategoryDataset();

		leerArchivoFlujos(FLUJO_TRAFICO_CARROS, FLUJO_TRAFICO_TODOS);
	}

	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo a las estructuras de datos
	 * @param rutaAccidentes la ruta donde se encuentra el archivo. ruta != null
	 */
	public void leerArchivoAccidentes(String rutaAccidentes, String rutaPeso) 
	{
		File f = new File(rutaAccidentes);
		File p = new File(rutaPeso);
		try {
			JSONTokener tkn = new JSONTokener(new FileReader(f));
			JSONArray arr = new JSONArray(tkn);
			JSONTokener tk = new JSONTokener(new FileReader(p));
			JSONArray ar = new JSONArray(tk);

			switch (rutaAccidentes){
			case(ARCHIVO_BOROUGH):
				for(int i = 0; i < arr.length(); i++)
				{
					Double numerador = 0.0;
					Double denominador = 0.0;
					JSONObject peso = ar.getJSONObject(0);
					JSONObject actual = arr.getJSONObject(i);
					JSONArray info = actual.names();
					String localAuthority = actual.getString("Local-Authority");
					String codigo = actual.getString("Code");

					TablaHashLP<Integer, Integer> tablaLocalAuthority = new TablaHashLP<>(29);

					for(int j = 0; j < info.length(); j++)
					{
						try{
							String txt = info.getString(j);
							int anho = Integer.parseInt(txt.substring(0,4));
							int cantidad = (int) actual.get(""+anho);
							Double pesoAnho = (double) peso.get(anho+"");
							numerador+=cantidad*pesoAnho;
							denominador+=pesoAnho;
							tablaLocalAuthority.put(anho, cantidad);
						}
						catch(Exception e){
							continue;
						}
					}
					Double ps = numerador/denominador;
					LocalAuthority la = new LocalAuthority(localAuthority, codigo, ps);
					punto1A.add(la);
					tablaDatosA.put(codigo, tablaLocalAuthority);
				}
			break;
			case (ARCHIVO_BOROUGH_NINHOS):
				for(int i = 0; i < arr.length(); i++)
				{
					Double numerador = 0.0;
					Double denominador = 0.0;
					JSONObject peso = ar.getJSONObject(0);
					JSONObject actual = arr.getJSONObject(i);
					JSONArray info = actual.names();
					String localAuthority = actual.getString("LocalAuthority");
					String codigo = actual.getString("Code");
					TablaHashSC<Integer, Integer> tablaLocalAuthority = new TablaHashSC<>(13);

					for(int j = 0; j < info.length(); j++)
					{
						try{
							String txt = info.getString(j);
							int anho = Integer.parseInt(txt.substring(0,4));
							int cantidad = (int) actual.get(""+anho);
							Double pesoAnho = (double) peso.get(anho+"");
							numerador+=cantidad*pesoAnho;
							denominador+=pesoAnho;
							tablaLocalAuthority.put(anho, cantidad);
						}
						catch(Exception e){
							continue;
						}
					}
					Double ps = numerador/denominador;
					LocalAuthority la = new LocalAuthority(localAuthority, codigo, ps);
					punto1B.add(la);
					tablaDeTablasB.put(codigo, tablaLocalAuthority);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo a las estructuras de datos
	 * @param rutaAccidentes la ruta donde se encuentra el archivo. ruta != null
	 */
	public void leerArchivoAccidentesC(String rutaAccidentes, String rutaPeso, int numEntradas, String punto) 
	{
		File f = new File(rutaAccidentes);
		File p = new File(rutaPeso);
		try {
			JSONTokener tkn = new JSONTokener(new FileReader(f));
			JSONArray arr = new JSONArray(tkn);
			JSONTokener tk = new JSONTokener(new FileReader(p));
			JSONArray ar = new JSONArray(tk);

			for(int i = 0; i < numEntradas; i++)
			{
				Double numerador = 0.0;
				Double denominador = 0.0;
				JSONObject peso = ar.getJSONObject(0);
				JSONObject actual = arr.getJSONObject(i);
				JSONArray info = actual.names();
				String localAuthority = actual.getString("Ciudad");
				String codigo = actual.getString("Code");


				for(int j = 0; j < info.length(); j++)
				{
					try{
						String txt = info.getString(j);
						int anho = Integer.parseInt(txt.substring(0,4));
						int cantidad = (int) actual.get(""+anho);
						Double pesoAnho = (double) peso.get(anho+"");
						numerador+=cantidad*pesoAnho;
						denominador+=pesoAnho;
					}
					catch(Exception e){
						continue;
					}
				}
				Double ps = numerador/denominador;
				LocalAuthority la = new LocalAuthority(localAuthority, codigo, ps);
				if (punto.equals("A")){
					punto1A.add(la);
				}
				else if (punto.equals("B")){
					punto1B.add(la);
				}
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Importa la informaci�n del archivo que llega por par�metro
	 * post:	Se agregaron los datos del archivo a as estructuras de datos
	 * @param rutaFlujos la ruta donde se encuentra el archivo. ruta != null
	 */
	public void leerArchivoFlujos(String rutaFlujosC, String rutaFlujosT) 
	{
		File f = new File(rutaFlujosC);
		File a = new File(rutaFlujosT);
		try {
			JSONTokener tkn = new JSONTokener(new FileReader(f));
			JSONArray arr = new JSONArray(tkn);
			JSONTokener tkn2 = new JSONTokener(new FileReader(a));
			JSONArray arr2 = new JSONArray(tkn2);

			for(int i = 0; i < arr.length(); i++)
			{
				JSONObject actual = arr.getJSONObject(i);
				JSONObject actual2 = arr2.getJSONObject(i);
				JSONArray info = actual.names();
				JSONArray info2 = actual.names();
				String localAuthority = actual.getString("Local-Authority");
				String codigo = actual.getString("Code");
				arbol3Anombres.put(localAuthority, codigo);
				arbol3BNombreCodigo.put(localAuthority, codigo);
				TablasFlujo tabla = new TablasFlujo(codigo);
				for(int j = 0; j < info.length(); j++)
				{
					try{
						String txt = info.getString(j);
						int anho = Integer.parseInt(txt.substring(0,4));
						int cantidad = (int) actual.get(""+anho);
						tabla.darTablaFlujoCarrosA().put(anho, cantidad);
						tabla.darTablaFlujoCarrosB().put(anho, cantidad);

						String txt2 = info2.getString(j);
						int anho2 = Integer.parseInt(txt2.substring(0,4));
						int cantidad2 = (int) actual2.get(""+anho2);
						tabla.darTablaFlujoTodosA().put(anho, cantidad2);
						tabla.darTablaFlujoTodosB().put(anho, cantidad2);
					}

					catch(Exception e){
						continue;
					}
				}
				arbol3AFlujo.put(codigo, tabla);
				arbol3BCodigoFlujo.put(codigo, tabla);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Realiza la consulta 1 de la parte A. Imprime las local authoritys ordenadas por su prioridad desde un 
	 *      heap implementado con un arreglo.
	 */
	public void consulta1A(){
		Heap<LocalAuthority> temp = punto1A;
		Object[] aux = temp.heapSort();
		for (int i = 0; i <= punto1A.size() ; i++)
		{
			try{
				LocalAuthority la = (LocalAuthority)aux[i];
				System.out.println(la.toString());
			}
			catch(Exception e){
				continue;
			}
		}
	}
	
	/**
	 * Realiza la consulta 1 de la parte B. Imprime las local authorities ordenadas por su prioridad desde
	 * 		un heap implementado con lista
	 */
	public void consulta1B(){
		HeapLista<LocalAuthority> temp = punto1B;
		while (!temp.isEmpty()){
			LocalAuthority la = temp.poll();
			System.out.println(la.toString());
		}
	}

	/**
	 * Busca la autoridad local con el codigo que entra por parametro y la informacion de los accidentes
	 * 	en el a�o que entra por parametro
	 * @param pCodigo Codigo de la autoridad local de la cual se quiere conocer la informacion
	 * @param pAnho A�o del cual se quiere conocer el numero de accidentes
	 */
	public void consulta2A (String pCodigo, int pAnho)
	{
		TablaHashLP<Integer, Integer> local = tablaDatosA.get(pCodigo);
		int cantidad = local.get(pAnho);
		System.out.println("Codigo local authority: " + pCodigo + "\n" + "Ocurrieron " + cantidad + " accidentes en el a�o " + pAnho + ".");
	}

	/**
	 * Busca la autoridad local con el codigo que entra por parametro y la informacion de los accidentes
	 * 	en el a�o que entra por parametro
	 * @param pCodigo Codigo de la autoridad local de la cual se quiere conocer la informacion
	 * @param pAnho A�o del cual se quiere conocer el numero de accidentes
	 */
	public void consulta2B(String pCodigo, int pAnho){
		TablaHashSC<Integer, Integer> lA = tablaDeTablasB.get(pCodigo);
		int cantidad = lA.get(pAnho);
		System.out.println("Codigo autoridad local: " +  pCodigo+ "\n" + "Ocurrieron " + cantidad + " accidentes en el a�o " + pAnho + ".");
	}

	/**
	 * Busca el codigo de la autoridad local que entra por parametro para posteriormente buscar el flujo de 
	 * 	carros y vehiculos de esta en el a�o que entra por parametro asi como el numero de accidentes.
	 * @param pNombre Nombre de la autoridad local de la cual se quiere conocer la informacion
	 * @param pAnho A�o del cual se quiere conocer la informacion
	 */
	public void consulta3A (String pNombre, int pAnho)
	{
		String codigo = arbol3Anombres.get(pNombre);
		TablasFlujo tablas = arbol3AFlujo.get(codigo);
		TablaHashLP<Integer, Integer> todos = tablas.darTablaFlujoTodosA();
		TablaHashSC<Integer, Integer> carros = tablas.darTablaFlujoCarrosA();
		int cantAll = todos.get(pAnho);
		int cantCarros = carros.get(pAnho);
		consulta2A(codigo, pAnho);
		System.out.println("En el a�o " + pAnho + " transitaron " + cantCarros + " carros y un total de " + 
				cantAll + " vehiculos.");
	}

	/**
	 * Busca el codigo de la autoridad local que entra por parametro para posteriormente buscar el flujo de 
	 * 	carros y vehiculos de esta en el a�o que entra por parametro asi como el numero de accidentes.
	 * @param pNombre Nombre de la autoridad local de la cual se quiere conocer la informacion
	 * @param pAnho A�o del cual se quiere conocer la informacion
	 */
	public void consulta3B(String pNombre, int pAnho){
		String codigo = arbol3BNombreCodigo.get(pNombre);
		
		TablasFlujo tablas = arbol3BCodigoFlujo.get(codigo);
		TablaHashLP<Integer, Integer> flujoCarros = tablas.darTablaFlujoCarrosB();
		TablaHashSC<Integer, Integer> flujoTodos = tablas.darTablaFlujoTodosB();
		int cantidadCarros = flujoCarros.get(pAnho);
		int cantidadTodos = flujoTodos.get(pAnho);
		consulta2B(codigo, pAnho);
		System.out.println("En el a�o " + pAnho + " transitaron " + cantidadCarros + " carros y un total de " + 
				cantidadTodos + " vehiculos.");
	}	

	/**
	 * Guarda en un dataset los tiempos que toma ejecutar la consulta 1A y 1B con cantidades de datos diferentes.
	 * @return dataset con los tiempos de ejecución
	 */
	public DefaultCategoryDataset consulta1C()
	{
		infoc1 = new DefaultCategoryDataset();
		long t1 = 0;
		long t2 = 0;
		for(int i = 1; i <= 10 ; i ++)
		{
			int datos = 50*i;
			punto1A = new Heap<LocalAuthority>(53, new ComparatorPrioridad(), Heap.MAX_HEAP);
			leerArchivoAccidentesC(ARCHIVO_ACCIDENTES_CIUDADES, PESO_POR_ANHO, datos, "A");
			long tiempo = 0;
			tiempo=System.nanoTime();
			consulta1A();
			tiempo = System.nanoTime() - tiempo;
			t1+=tiempo;
			infoc1.addValue((Number)tiempo, "A", datos);
		}
		for(int i = 1; i <= 10 ; i ++)
		{
			int datos = 50*i;
			punto1B = new HeapLista<LocalAuthority>(new ComparatorPrioridad());
			leerArchivoAccidentesC(ARCHIVO_ACCIDENTES_CIUDADES, PESO_POR_ANHO, datos, "B");
			long tiempo = 0;
			tiempo = System.nanoTime();
			consulta1B();
			tiempo = System.nanoTime() - tiempo;
			t2+=tiempo;
			infoc1.addValue((Number)tiempo, "B ", datos);
		}
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("El tiempo que toma para realizar la consulta 1A es de " + (t1/10) + " nanosegundos, mientras"
		+ " que para realizar la consulta 1B es de " + (t2/10) + " nanosengundos. \nEl tiempo de la consulta 1A es "+ df.format(((double)t1/(double)t2))
		+ " veces el de la consulta 1B. Esto se debe a que en la consulta 1B se hace uso de un heap implementado mediante "
		+ "\nuna lista, en la cual se agregan los elementos de manera ordenada por lo cual obtener los elementos en orden es mas rapido.");
		return infoc1;
	}

	/**
	 * Guarda en un dataset los tiempos promedio de correr 100 veces las consultas 2A y 2B-
	 * @return dataset con los tiempos promedio de ejecución
	 */
	public DefaultCategoryDataset consulta2C1()
	{
		infoc21 = new DefaultCategoryDataset();
		int cod = 9000000;
		int j = 1;
		long t = 0;
		Double t1=0.0;
		Double t2=0.0;
		String ini = "E0";
		while (cod++ < 9000009){
			String codigo = ini+cod;
			int anho = 2003;
			while (anho++ < 2014){
				j++;
				long tiempo = System.nanoTime();
				consulta2A(codigo, anho);
				tiempo = System.nanoTime() - tiempo;
				t+=tiempo;
			}
		}
		t1=(double) (t/j);
		infoc21.addValue((Number)t1, "A", j);
		cod = 9000000;
		j = 1;
		t = 0;
		ini = "E0";
		while (cod++ < 9000009){
			String codigo = ini+cod;
			int anho = 2003;
			while (anho++ < 2014){
				j++;
				long tiempo = System.nanoTime();
				consulta2B(codigo, anho);
				tiempo = System.nanoTime() - tiempo;
				t+=tiempo;
			}
		}
		t2 = (double) (t/j);
		infoc21.addValue((Number)t2, "B", j);
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("El tiempo que toma para realizar la consulta 2A es de " + (t1/10) + " nanosegundos, mientras"
		+ " que para realizar la consulta 2B es de " + (t2/10) + " nanosengundos. \nEl tiempo de la consulta 2A es "+ df.format((t1/t2))
		+ " veces el de la consulta 2B. Esto se debe a que en la consulta 2A se hace uso de una tabla de Hash con separate chaining para indexar \n"
		+ "las autoridades locales (la mayor cantidad de datos manejada en este punto) por lo cual encuentra con mayor velocidad la información \n"
		+ "buscada, esto se debe a que en linear probing se puede perder mucho tiempo encontrando el dato deseado en caso de que se hubieran agregado \n"
		+ "elementos que hayan sido direccionados a la misma posicion de la tabla del elemento buscado.");
		return infoc21;
	}
	
	public int darColisionesA()
	{
		return tablaDatosA.darColisiones();
	}
	
	public int darColisionesB()
	{
		return tablaDeTablasB.darColisiones();
	}
	
	/**
	 *  Calcula en tiempo que demora en buscar todas las autoridades locales en los dos difetnes tipos de 
	 *  	tablas de hash.
	 * @return La base de datos que contiene los resultados
	 */
	public long consulta2C2A()
	{
		Cola<LocalAuthority> la = new Cola<LocalAuthority>();
		Heap<LocalAuthority> la2 = punto1A;
		int cant = punto1A.size();
		LocalAuthority aux = la2.poll();
		while(!la2.isEmpty())
		{
			la.enqueue(aux);
			aux = la2.poll();
		}
		long tiempos = 0;
		while(!la.isEmpty())
		{
			LocalAuthority act = la.dequeue();
			long tiempo = System.nanoTime();
			tablaDatosA.contains(act.darCodigo());
			tiempo = System.nanoTime() - tiempo;
			tiempos = tiempos + tiempo; 
		}
		tiempos = tiempos/cant;
		return tiempos;
		
	}
	
	/**
	 *  Calcula en tiempo que demora en buscar todas las autoridades locales en los dos difetnes tipos de 
	 *  	tablas de hash.
	 * @return La base de datos que contiene los resultados
	 */
	public long consulta2C2B()
	{
		Cola<LocalAuthority> las = new Cola<LocalAuthority>();
		HeapLista<LocalAuthority> las2 = punto1B;
		int cant = punto1B.size();
		LocalAuthority aux1 = las2.poll();
		while(!las2.isEmpty())
		{
			las.enqueue(aux1);
			aux1 = las2.poll();
		}
		long tiempos1 = 0;
		while(!las.isEmpty())
		{
			LocalAuthority act = las.dequeue();
			long tiempo = System.nanoTime();
			tablaDeTablasB.contains(act.darCodigo());
			tiempo = System.nanoTime() - tiempo;
			tiempos1 = tiempos1 + tiempo; 
		}
		tiempos1 = tiempos1 / cant;
		return tiempos1;
	}
	
	/**
	 * Mide el tiempo que demora en realizar la consulta 3A Y 3B.
	 * @return Un mensaje con los resultados.
	 */
	public DefaultCategoryDataset consulta3c()
	{
		DefaultCategoryDataset data = new DefaultCategoryDataset();
		long tiempo1 = 0;
		int i = 0;
		while (i< 100)
		{
			long tiempo = System.nanoTime();
			consulta3A("Barnet", 2014);
			i++;
			tiempo = System.nanoTime() - tiempo;
			tiempo1 += tiempo;
			
		}
		tiempo1 = tiempo1/100;
		data.addValue((Number) tiempo1, "Consulta A", "Consulta");
		
		long tiempo2 = 0;
		i = 0;
		while(i < 100)
		{
			long tiempo = System.nanoTime();
			consulta3B("Barnet", 2014);
			i++;
			tiempo = System.nanoTime() - tiempo;
			tiempo2 += tiempo;
		}
		tiempo2 = tiempo2/100;
		data.addValue((Number) tiempo2, "Consulta B", "Consulta");
		DecimalFormat df = new DecimalFormat("#0.00");
		System.out.println("***RESPUESTA***");
		System.out.println("Luego de realizar las consultas 100 veces, el tiempo promedio que toma para realizar la consulta 3A es de " + tiempo1 + " nanosegundos, mientras"
				+ " promedio que toma para realizar la consulta 3B es de " + tiempo2 + " nanosengundos. \nEl tiempo de la consulta 3A es "+ df.format(((double)tiempo2/(double)tiempo1))
				+ " veces el de la consulta 3B. \n En general vemos que el tiempo de consulta de 3A tiende a ser mayor al de la consulta 3B, esto se debe a como estan "
				+ " distribuidos los datos ya que cada una usa una tabla de hash distinta para guardar los mismos datos, y esto puede generar que difieran los tiempos de"
				+ " consulta ya que ambas tablas no tienen el mismo tiempo para consultar un dato. \n Del punto 2c-2 podemos observar que Separate chaining toma mas tiempo en promedio"
				+ " para realiza una busqueda y esto puede generar la diferencia en estas consultas tambien. ");
		return data;
				
	}
}



