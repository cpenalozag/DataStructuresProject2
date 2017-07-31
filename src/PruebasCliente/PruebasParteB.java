package PruebasCliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import mundo.Maps;


public class PruebasParteB {
	
	BufferedWriter escritor;
	Scanner lector;

	
	//TODO: Declarar objetos de la parte B
	Maps principal;
	
	public PruebasParteB(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}
	
	public void pruebas() {
		int opcion = -1;
		
		//TODO: Inicializar objetos de la parte B
	
		
		long tiempoDeCarga = System.nanoTime();
		//TODO: Cargar informacion de la parte B
		principal = new Maps();
		
		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;
		
		while (opcion != 0) {
			try {
				escritor.write("---------------Pruebas Proyecto ---------------\n");
				escritor.write("Informacion cargada en: " + tiempoDeCarga + " nanosegundos\n");
				escritor.write("Reportes:\n");
				escritor.write("1: Imprimir la informacion de cada local_authority de mayor a menor prioridad. \n");
				escritor.write("2: Responder consultas sobre el numero de accidentes en un año particular. \n");
				escritor.write("3: Conocer los accidentes, flujo de carros y flujo de todos los vehiculos, de una local_authority (dado su nombre), en un año especifico \n");
				escritor.write("0: Volver\n");
				escritor.write("------------------------------------------------\n");
				escritor.flush();
				opcion = lector.nextInt();
				
				switch(opcion) {
				case 1: b1(); break;
				case 2: b2(); break;
				case 3: b3(); break;
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
	
	private void b1() throws IOException{
		//TODO: Genere la prioridad de cada local_authority usando la media ponderada de los accidentes de todos los aï¿½os.
	    //Recuerde que los pesos por aï¿½o corresponden a un porcentaje y que estos pesos se encuentran en el archivo road-casualties-severity-borough-years-weight.json.
		
		//TODO: Defina una cola de prioridad orientada a mayor a partir de los datos que se encuentran en el archivo road-casualties-severity-borough-child.json.
		
		long tiempo = System.nanoTime();
		//TODO: Obtener e imprimir la informaciï¿½n de cada local_authority de mayor a menor prioridad, incluyendo el valor de prioridad, ejemplo: <local-authority_x>, <prioridad_local_authority_x>.
		//RECUERDE: Debe hacer uso del algoritmo HeapSort
		principal.consulta1B();
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}
	
	private void b2() throws IOException{
		//TODO: Defina una tabla de hash aplicando la tï¿½cnica "Linear Probing" para el indexamiento por cï¿½digo de local_authority
		//y utilizando como llave el cï¿½digo de las local_authorities 
				
		//RECUERDE: El valor asociado a cada llave es el conjunto de accidentes detallado por aï¿½o
		//y estos valores se indexarï¿½n nuevamente en una segunda tabla de hash utilizando una implementaciï¿½n de "Separate Chaining"
				
		//TODO: Defina una segunda tabla de hash utilizando una implementaciï¿½n de "Separate Chaining".
		//En esta segunda tabla la llave es el aï¿½o y el valor el nï¿½mero de accidentes de dicho aï¿½o.
				
		//RECUERDE: La segunda tabla de hash esta almacenada dentro de la primera tabla de hash: Estructura de datos doble hash 
				
		//TODO: Solicitar al usuario la informaciï¿½n requerida para el desarrollo del literal: cï¿½digo de una local_authority y aï¿½o de consulta
		//RECUERDE: El objeto del literal es responder consultas sobre el nï¿½mero de accidentes en un aï¿½o particular de una local_authority dado su cï¿½digo.
		//En ese orden de ideas debe solicitar al usuario un aï¿½o y el cï¿½digo de una local_authority
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//TODO: Obtener e imprimir la informaciï¿½n sobre el nï¿½mero de accidentes en el aï¿½o de interes.
		System.out.println("Escriba el codigo de la autoridad local:");
		String codigo = br.readLine();
		System.out.println("Escriba el año para la consulta:");
		int anho = Integer.parseInt(br.readLine()); 
		long tiempo = System.nanoTime();
		try{
			principal.consulta2B(codigo,anho);
		}
		catch (Exception e){
			System.out.println("No existen entradas para el codigo y el año ingresados.");
		}
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();	
	}
	
	private void b3() throws IOException{
		//TODO: Construya un ï¿½rbol binario balanceado ordenado por el nombre de las local_authority (llave).
		//En cada nodo se debe tener asociado el cï¿½digo de la local_autority. 
				
		//TODO: Construya un segundo ï¿½rbol binario balanceado ordenado por el cï¿½digo de las local_authority (llave).
		//En cada nodo se debe tener dos tablas de hash:
				
		//TABLA 1: Esta tabla debe contener la informaciï¿½n de "traffic flow cars". Llave: aï¿½o de consulta
		//TABLA 2: Esta tabla debe contener la informaciï¿½n de "traffic flow all vehicles". Llave: aï¿½o de consulta 
				
		//RECUERDE: La implementaciï¿½n de la primera tabla se debe hacer mediante "Linear Probing" y la segunda mediante  "Separate Chaining" 
				
		//TODO: Solicitar al usuario la informaciï¿½n requerida para el desarrollo del literal: : nombre de una local_authority y aï¿½o de consulta
		//RECUERDE: El objeto del literal es darle a conocer al usuario los accidentes, flujo de carros y flujo de todos los vehï¿½culos, de una local_authority dado su nombre, en un aï¿½o especï¿½fico.
		//En ese orden de ideas debe solicitar al usuario un aï¿½o y el nombre de una local_authority
			
		//TODO: Obtener e imprimir la informaciï¿½n sobre los accidentes, flujo de carros y flujo de todos los vehï¿½culos dado el nombre del local_authority de interes.
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Escriba el nombre de la autoridad local:");
		String nombre = br.readLine();
		System.out.println("Escriba el aÃ±o para la consulta:");
		int anho = Integer.parseInt(br.readLine());
		long tiempo = System.nanoTime();
		try
		{
			principal.consulta3B(nombre,anho);
		}
		catch (Exception e){
			System.out.println("No existen entradas para la autoridad local y el aÃ±o ingresados.");
		}
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
		
	}
	
}