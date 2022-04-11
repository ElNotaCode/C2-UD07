package ej3;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

//Autor Eloi Martorell Martin 11/04/2022

public class Ej3App {
	
	static Scanner sc = new Scanner(System.in); //así podemos usarlo en toda la clase
	
	static Hashtable<String,String> nombreProductos = new Hashtable<String,String>();
	static Hashtable<String, Double> precioProductos = new Hashtable<String, Double>();
	static Hashtable<String, Integer> stock = new Hashtable<String, Integer>();

	public static void main(String[] args) {
		//cargamos pre-existentes
		cargarProductos();
		agregarProductos();
		listarProductos();
		
		sc.close();
		
	}
	
	//cargar productos pre-existentes
	private static void cargarProductos() {
		
		nombreProductos.put("1", "Longaniza");
		nombreProductos.put("2" , "Pizza");
		nombreProductos.put("3", "Bacon");
		nombreProductos.put("4", "Coca-cola");
		nombreProductos.put("5", "Fanta Naranja");
		nombreProductos.put("6", "Pan");
		nombreProductos.put("7", "Lechuga");
		nombreProductos.put("8", "Mortadela");
		nombreProductos.put("9", "Avellanas");
		nombreProductos.put("10", "Chocolate");
		
		precioProductos.put("1", 3.99);
		precioProductos.put("2", 2.22);
		precioProductos.put("3", 2.50);
		precioProductos.put("4", 1.50);
		precioProductos.put("5", 1.20);
		precioProductos.put("6", 0.50);
		precioProductos.put("7", 0.60);
		precioProductos.put("8", 1.0);
		precioProductos.put("9", 3.10);
		precioProductos.put("10", 2.15);
		
		stock.put("1", 4);
		stock.put("2", 6);
		stock.put("3", 10);
		stock.put("4", 2);
		stock.put("5", 20);
		stock.put("6", 12);
		stock.put("7", 10);
		stock.put("8", 4);
		stock.put("9", 18);
		stock.put("10", 7);
		
	}
	
	//añadir articulos
	public static void agregarProductos() {
		
		int id = nombreProductos.size() + 1;
		String idString = new String(Integer.toString(id));
		
		System.out.println("Bienvenido al asistente de Stock Management(™)");
		System.out.println("Inserta el nombre del producto:");
		String nombre = new String(sc.next());
		
		System.out.println();
		double precio;
		do {
			System.out.println("Introduce un precio valido:");
			precio = sc.nextDouble();
			
		}while(precio <= 0);
		
		System.out.println();
		Integer stockInt;
		do {
			System.out.println("Introduce una cantidad de stock valida:");
			stockInt = sc.nextInt();
			
		}while (stockInt < 0); //puede llegar a ser un producto sin stock
		
		//metemos la info
		nombreProductos.put(idString, nombre);
		precioProductos.put(idString, precio);
		stock.put(idString, stockInt);
	}
	
	//consultar info especifica / editar
	public static void vistaEditor() {
		
		
		
	}
	
	//listar informacion
	public static void listarProductos() {
		
		Enumeration<String> enumerationId = nombreProductos.keys();
		Enumeration<String> enumerationNombreProductos = nombreProductos.elements();
		Enumeration<Double> enumerationPrecioProductos = precioProductos.elements();
		Enumeration<Integer> enumerationStock = stock.elements();
		
		while (enumerationId.hasMoreElements()) {
			System.out.println(); //espacio
			System.out.print("" + enumerationId.nextElement() );
			System.out.print(" " + enumerationNombreProductos.nextElement() );
			System.out.print(" Precio: " + enumerationPrecioProductos.nextElement() );
			System.out.print(" Stock: " + enumerationStock.nextElement() );
		}
		
		System.out.println();
		System.out.println();
		
	}
	
	//borrar
	public static void borrarProducto() {
		
	}

}
