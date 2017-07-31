package PruebasCliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import mundo.Maps;



public class PruebasParteA {

	BufferedWriter escritor;
	Scanner lector;


	//TODO: Declarar objetos de la parte A
	Maps principal;

	public PruebasParteA(BufferedWriter escritor, Scanner lector) {
		this.escritor = escritor;
		this.lector = lector;
	}

	public void pruebas() {
		int opcion = -1;


		long tiempoDeCarga = System.nanoTime();
		//TODO: Cargar informacion de la parte A
		principal = new Maps();

		tiempoDeCarga = System.nanoTime() - tiempoDeCarga;

		while (opcion != 0) {
			try {
				escritor.write("---------------Pruebas Proyecto ---------------\n");
				escritor.write("Informacion cargada en: " + tiempoDeCarga + " nanosegundos\n");
				escritor.write("Reportes:\n");
				escritor.write("1: Imprimir la informacion de cada local_authority de mayor a menor prioridad. \n");
				escritor.write("2: Responder consultas sobre el numero de accidentes en un a�o particular. \n");
				escritor.write("3: Conocer los accidentes, flujo de carros y flujo de todos los vehiculos, de una local_authority (dado su nombre), en un a�o especifico \n");
				escritor.write("0: Volver\n");
				escritor.write("------------------------------------------------\n");
				escritor.flush();
				opcion = lector.nextInt();

				switch(opcion) {
				case 1: a1(); break;
				case 2: a2(); break;
				case 3: a3(); break;

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

	private void a1() throws IOException{

		//TODO: Genere la prioridad de cada local_authority usando la media ponderada de los accidentes de todos los a�os.
		//Recuerde que los pesos por a�o corresponden a un porcentaje y que estos pesos se encuentran en el archivo road-casualties-severity-borough-years-weight.json.

		//TODO: Defina una cola de prioridad orientada a mayor a partir de los datos que se encuentran en el archivo road-casualties-severity-borough.json.

		long tiempo = System.nanoTime();
		//TODO: Obtener e imprimir la informacion de cada local_authority de mayor a menor prioridad, incluyendo el valor de prioridad, ejemplo: <local-authority_x>, <prioridad_local_authority_x>.
		//RECUERDE: Debe hacer uso del algoritmo HeapSort
		principal.consulta1A();
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();
	}

	private void a2() throws IOException{
		//TODO: Defina una tabla de hash aplicando la tecnica "Separate Chaining" para el indexamiento por codigo de local_authority
		//y utilizando como llave el codigo de las local_authorities 

		//RECUERDE: El valor asociado a cada llave es el conjunto de accidentes detallado por a�o para la Local Authority
		//y estos valores se indexaron nuevamente en una segunda tabla de hash utilizando una implementacion de "Linear Probing"

		//TODO: Defina una segunda tabla de hash utilizando una implementacion de "Linear Probing".
		//En esta segunda tabla la llave es el a�o y el valor el numero de accidentes de dicho a�o.

		//RECUERDE: La segunda tabla de hash esta almacenada dentro de la primera tabla de hash: Estructura de datos doble hash 

		//TODO: Solicitar al usuario la informacion requerida para el desarrollo del literal: codigo de una local_authority y a�o de consulta
		//RECUERDE: El objeto del literal es responder consultas sobre el numero de accidentes en un a�o particular de una local_authority dado su codigo.
		//En ese orden de ideas debe solicitar al usuario un a�o y el codigo de una local_authority

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//TODO: Obtener e imprimir la informacion sobre el numero de accidentes en el a�o de interes.

		System.out.println("Escriba el codigo de la autoridad local:");
		String codigo = br.readLine();
		System.out.println("Escriba el a�o para la consulta:");
		int anho = Integer.parseInt(br.readLine()); 
		long tiempo = System.nanoTime();
		try{
			principal.consulta2A(codigo,anho);
		}
		catch (Exception e){
			System.out.println("No existen entradas para el odigo y el a�o ingresados.");
		}
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}

	private void a3() throws IOException{
		//TODO: Construya un arbol binario balanceado ordenado por el nombre de las local_authority (llave).
		//En cada nodo se debe tener asociado el c�digo de la local_autority. 

		//TODO: Construya un segundo �rbol binario balanceado ordenado por el c�digo de las local_authority (llave).
		//En cada nodo se debe tener dos tablas de hash:

		//TABLA 1: Esta tabla debe contener la informacion de "traffic flow cars". Llave: a�o de consulta
		//TABLA 2: Esta tabla debe contener la informacion de "traffic flow all vehicles". Llave: a�o de consulta 

		//RECUERDE: La implementacion de la primera tabla se debe hacer mediante "Separate Chaining" y la segunda mediante "Linear Probing"

		//TODO: Solicitar al usuario la informacion requerida para el desarrollo del literal: nombre de una local_authority y a�o de consulta
		//RECUERDE: El objeto del literal es darle a conocer al usuario los accidentes, flujo de carros y flujo de todos los veh�culos, de una local_authority dado su nombre, en un a�o espec�fico.
		//En ese orden de ideas debe solicitar al usuario un a�o y el nombre de una local_authority

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Escriba el nombre de la autoridad local:");
		String nombre = br.readLine();
		System.out.println("Escriba el a�o para la consulta:");
		int anho = Integer.parseInt(br.readLine());
		long tiempo = System.nanoTime();
		try
		{
			principal.consulta3A(nombre,anho);
		}
		catch (Exception e){
			System.out.println("No existen entradas para la autoridad local y el a�o ingresados.");
		}
		//TODO: Obtener e imprimir la informacion sobre los accidentes, flujo de carros y flujo de todos los vehiculos dado el nombre del local_authority de interes.
		tiempo = System.nanoTime() - tiempo;
		escritor.write("Duracion: " + tiempo + " nanosegundos\n");
		escritor.write("Ingrese cualquier letra y Enter para continuar\n");
		escritor.flush();
		lector.next();		
	}
}

