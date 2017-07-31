package PruebasCliente;

import java.awt.Dimension;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import mundo.Grafica;
import mundo.Maps;


public class PruebasParteC {

	BufferedWriter escritor;
	Scanner lector;


	//TODO: Declarar objetos de la parte A
	//TODO: Declarar objetos de la parte B
	Maps principal;

	public PruebasParteC(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}

	public void pruebas() {
		int opcion = -1;

		//TODO: Inicializar objetos de la parte A
		//TODO: Inicializar objetos de la parte B

		long tiempoDeCarga = System.nanoTime();
		//TODO: Cargar informacion de la parte A
		//TODO: Cargar informacion de la parte B
		principal = new Maps();

		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;

		while (opcion != 0) {
			try {
				escritor.write("---------------Pruebas Proyecto ---------------\n");
				escritor.write("Informacion cargada en: " + tiempoDeCarga + " nanosegundos\n");
				escritor.write("Reportes:\n");
				escritor.write("1: Requerimiento 1C. \n");
				escritor.write("2: Requerimiento 2C - 1. \n");
				escritor.write("3: Requerimiento 2C - 2. \n");
				escritor.write("4: Requerimiento 3C. \n");
				escritor.write("0: Volver\n");
				escritor.write("------------------------------------------------\n");
				escritor.flush();
				opcion = lector.nextInt();

				switch(opcion) {
				case 1: c1(); break;
				case 2: c21(); break;
				case 3: c22(); break;
				case 4: c3(); break;
				}
			}
			catch (IOException ioe) {
				ioe.printStackTrace();
			}
			catch (NumberFormatException nfe) {
				try {
					escritor.write("No ingreso el periodo de tiempo correctamente\n");
					escritor.write("Ingrese cualquier letra y enter para continuar\n");
					escritor.flush();
					lector.nextLine();
					lector.nextLine();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
			catch (InputMismatchException ime) {
				try {
					escritor.write("No ingreso un numeral\n");
					escritor.write("Ingrese cualquier letra y enter para continuar\n");
					escritor.flush();
					lector.nextLine();
					lector.nextLine();
				}
				catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}

	private void c1() throws IOException{
		long tiempo = System.nanoTime();
		DefaultCategoryDataset d = principal.consulta1C();
		Grafica g = new Grafica("1", d);
		g.mostrarGraficaLinea("Consulta 1", "Numero de Ciudades", "Tiempo (En nanosegundos)");
		g.pack();
		RefineryUtilities.centerFrameOnScreen(g);
		g.setVisible(true);
		//TODO:Generar y obtener el conjunto de datos requeridos para realizar la grafica solicitada.
		//Para graficar PUEDE hacer uso de la  libreria  JFREECHART disponible en "http://www.jfree.org/jfreechart/"
		//Si opta por usar JFREECHART puede hacer uso (como guia) del ejemplo que se presenta en el siguiente link "https://www.tutorialspoint.com/jfreechart/jfreechart_line_chart.htm" 
		//Recuerde que en el eje de abcisas se tendrï¿½ el # de ciudades (N) y en el eje de ordenadas se tendrï¿½n los tiempos de ejecuciï¿½n en milisegundos o nanosegundos 
		//Independientemente de la forma en que grafique, nombre tanto la grafica como sus ejes
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}

	private void c21() throws IOException{
		long tiempo = System.nanoTime();
		DefaultCategoryDataset d = principal.consulta2C1();
		Grafica g = new Grafica("21", d);
		g.mostrarGraficaBarras("Consulta 2C-1", "Numero de Ciudades", "Tiempo (En nanosegundos)");
		g.pack();
		RefineryUtilities.centerFrameOnScreen(g);
		g.setVisible(true);
		//TODO:Generar y obtener el conjunto de datos requeridos para realizar la grafica solicitada.
		//Para graficar PUEDE hacer uso de la  libreria  JFREECHART disponible en "http://www.jfree.org/jfreechart/"
		//Si opta por usar JFREECHART puede hacer uso (como guia) del ejemplo que se presenta en el siguiente link "https://www.youtube.com/watch?v=qVkqyuTiWCc" 
		//Recuerde que en el eje de abcisas se tendrï¿½ los metodos para indexar ("Separate Chaining""Linear Probing") y en el eje de ordenadas se tendrï¿½n los tiempos de ejecuciï¿½n en milisegundos o nanosegundos 
		//Independientemente de la forma en que grafique, nombre tanto la grafica como sus ejes
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}

	private void c22() throws IOException{
		long tiempo = System.nanoTime();
		//TODO:Generar y obtener el conjunto de datos requeridos para realizar la grafica solicitada.
		//Para graficar PUEDE hacer uso de la  libreria  JFREECHART disponible en "http://www.jfree.org/jfreechart/"
		//Si opta por usar JFREECHART puede hacer uso (como guia) del ejemplo que se presenta en el siguiente link "https://www.youtube.com/watch?v=qVkqyuTiWCc" 
		//Independientemente de la forma en que grafique, nombre tanto las grafica como sus ejes
		DefaultCategoryDataset a = new DefaultCategoryDataset();
	
		DefaultCategoryDataset b = new DefaultCategoryDataset();
		
		Maps mundo1 = new Maps(20);
		int colisionesa1 = mundo1.darColisionesA();
		int colisionesb1 = mundo1.darColisionesB();
		long tiempoA = mundo1.consulta2C2A();
		long tiempoB = mundo1.consulta2C2B();
		a.addValue((Number) tiempoA, "Separate Chaining", 20);
		a.addValue((Number) tiempoB, "Linear Probing", 20);
		b.addValue((Number)colisionesa1, "Separate Chaining", 20);
		b.addValue((Number) colisionesb1, "Linear Probing", 20);
		
		Maps mundo2 = new Maps (337);
		int colisionesa2 = mundo2.darColisionesA();
		int colisionesb2 = mundo2.darColisionesB();
		long tiempoA2 = mundo2.consulta2C2A();
		long tiempoB2= mundo2.consulta2C2B();
		a.addValue((Number) tiempoA2, "Separate Chaining", 337);
		a.addValue((Number) tiempoB2, 	"Linear Probing", 337);
		b.addValue((Number) colisionesa2, "Separate Chaining", 337);
		b.addValue((Number) colisionesb2, "Linear Probing", 337);
		
		Maps mundo3 = new Maps (1009);
		int colisionesa3 = mundo3.darColisionesA();
		int colisionesb3 = mundo3.darColisionesB();	
		long tiempoA3 = mundo3.consulta2C2A();
		long tiempoB3 = mundo3.consulta2C2B();
		a.addValue((Number) tiempoA3, "Separate Chaining", 1009);
		a.addValue((Number) tiempoB3, "Linear Probing", 1009);
		b.addValue((Number) colisionesa3, "Separate Chaining", 1009);
		b.addValue((Number) colisionesb3, "Linear Probing", 1009);
		
		Grafica h = new Grafica("Colisiones", b);
		h.mostrarGraficaLinea("Consulta 2C-2", "Numero inicial", "Colisiones");
		h.pack();
		RefineryUtilities.centerFrameOnScreen(h);
		h.setVisible(true);
		
		Grafica f = new Grafica("Tiempo consulta Local Authorities", a);
		f.mostrarGraficaLinea("Consulta 2C-2", "Numero inicial", "Tiempo (Nanosegundos)");
		f.pack();
		RefineryUtilities.centerFrameOnScreen(f);
		f.setVisible(true);
	
		System.out.println("Podemos ver como Separate chaining presenta mas colisiones que Linear probing a la hora de guardar los datos"
				+ "debido a que este maneja un factor de carga mayor lo cual incrementa las probabilidades de que la casilla que busca este ocupada,"
				+  " mientras que Linear probing maneja un factor de carga menor y esto le permite tener un tamaño mayor reduciendo las probabilidades "
				+ " de que las posiciones ya esten ocupadas. \n Sin embargo vemos que si se usan numeros primos ya sean mayores o menores a los datos"
				+ " que van a guardar en las tablas las colisiones se reducen y se puede observar que el numero de colisiones entre ambas no difiere mucho."
				+ " \n En cuanto al tiempo para buscar un elemento en especifico encontramos que igualmente Separate chaining demora mas al tener mas posibilidades "
				+ "+ de que la celda este ocupada y tenga que iterar entre las que se extienden de esa para encontrar la que busca mientras que en linear probing "
				+ " tiene mas posibilidades de encontrar directamente al que esta buscando, reduciendo el tiempo de busqueda.");
		tiempo = System.nanoTime() - tiempo;

		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();	
	}
	
	private void c3() throws IOException{
		long tiempo = System.nanoTime();
		//TODO:Haga la comparacion pertienente, explique los resultados obtenidos
		//y concluya (Imprima en pantalla lo que compara, explique en la misma impresion
		//en que consiste dicha comparaciï¿½n e imprima su conclusiï¿½n)
		DefaultCategoryDataset a = principal.consulta3c();
		
		Grafica f = new Grafica("Tiempo tercera consulta", a);
		f.mostrarGraficaBarras("Consulta 3C", "Consulta", "Tiempo (Nanosegundos)");
		f.pack();
		RefineryUtilities.centerFrameOnScreen(f);
		f.setVisible(true);
		
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();	

	}

}