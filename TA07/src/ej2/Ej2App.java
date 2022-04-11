package ej2;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

//Autor Eloi Martorell Martin 11/04/2022

public class Ej2App {
	
	static Scanner sc = new Scanner(System.in); //así podemos usarlo en toda la clase
	
	//cargamos las tablas de los productos
	static Hashtable<String,String> nombreProductos = new Hashtable<String,String>();
	static Hashtable<String,Double> precioProductos = new Hashtable<String,Double>();
	static Hashtable<String,Double> IVAProductos = new Hashtable<String,Double>();
	static Hashtable<String,Integer> carrito = new Hashtable<String,Integer>();
	
	//app que gestione el flujo de ventas
	public static void main(String[] args) {
		
		//cargamos los productos
		cargarProductos();
		
		//comprar x producto > cantidad? > quieres comprar mas?
		//x producto tiene x IVA
		System.out.println("¡Bienvenido! Mira nuestros productos:");
		mostrarCarrito();
		
		int menu;
		do {
			
			System.out.println("1 Comprar productos - 2 Mi carrito de la compra - 3 Pagar - 0 Salir");
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				guardarCantidades();
				mostrarCarrito();
				break;
			case 2:
				mostrarCarrito();
				break;
			case 3:
				
				pagarProductos();
				
				break;
			case 0:
				System.out.println("¡Gracias por comprar en nuestras tiendas!");
				break;

			default:
				System.out.println("¡Tienes que introducir una de las opciones!");
				break;
			}
			
		}while(menu != 0);

		
		sc.close();
	}
	
	//mostrar carrito
	public static void mostrarCarrito() {
		
		Enumeration<String> enumerationId = carrito.keys();
		Enumeration<String> enumerationNombreProductos = nombreProductos.elements();
		Enumeration<Integer> enumerationCantidad = carrito.elements();
		
		while (enumerationCantidad.hasMoreElements()) {
			System.out.println(); //espacio
			System.out.print("" + enumerationId.nextElement() );
			System.out.print(" " + enumerationNombreProductos.nextElement() );
			System.out.print(" " + enumerationCantidad.nextElement() );
		}
		
		System.out.println();
		System.out.println();
		
	}
	
	//guardar cantidades
	public static void guardarCantidades() {
		
		boolean checkProducto = false;
		String productoId = new String();
		int producto = 0;
		int cantidad = 0;
		
		do {
			
			//seleccionar producto
			System.out.println("Selecciona un producto introduciendo su id numerico:");
			producto = sc.nextInt();
			
			if(producto > 0 && producto <= 5) {
				productoId = producto + "";
				checkProducto = true;
			}
			
		}while(!checkProducto);
		
		//seleccionar cantidad
		
		boolean checkCantidad = false;
		
		do {
			
			//seleccionar producto
			System.out.println("Selecciona una cantidad (max 20) :");
			cantidad = sc.nextInt();
			
			if(cantidad > -1 && cantidad <= 20) {
				checkCantidad = true;
			}
			
		}while(!checkCantidad);
		
		//guardar
		carrito.put(productoId, cantidad);
		
	}
	
	//cargar productos
	public static void cargarProductos() {
		
		//harcodeamos algunos productos
		nombreProductos.put("1", "Longaniza");
		nombreProductos.put("2", "Coca-cola");
		nombreProductos.put("3", "Queso");
		nombreProductos.put("4", "Pan");
		nombreProductos.put("5", "Doritos");
		
		precioProductos.put("1", 3.59);
		precioProductos.put("2", 1.25);
		precioProductos.put("3", 2.14);
		precioProductos.put("4", 0.50);
		precioProductos.put("5", 1.66);
		
		IVAProductos.put("1", 0.04);
		IVAProductos.put("2", 0.21);
		IVAProductos.put("3", 0.04);
		IVAProductos.put("4", 0.04);
		IVAProductos.put("5", 0.21);
		
		carrito.put("1", 0);
		carrito.put("2", 0);
		carrito.put("3", 0);
		carrito.put("4", 0);
		carrito.put("5", 0);
		
	}
	
	/*mostrar info 
	 * IVA aplicado 21% o 4%
	 * CANTIDAD PAGADA
	 * CAMBIO A DEVOLVER
	*/
	
	//metodo pagar
	public static void pagarProductos() {
		
		/*	 NOMBRE DE LAS TABLAS
		  	nombreProductos
			precioProductos
			IVAProductos
			carrito
		 */
		
		double precioTotal = 0;
		
		Object key = null;
		Iterator<String> iter = carrito.keySet().iterator();
		
		while (iter.hasNext()) {
			//sacamos los ids y la cantidad 
			key = iter.next();
			int cantidad = carrito.get(key);
			
			if(cantidad > 0) {
				//Nombre, precio y precio + IVA
				String nombre = nombreProductos.get(key);
				double precio = precioProductos.get(key);
				double IvaAplicado = IVAProductos.get(key);
				double precioConIva = (precio*cantidad)+((precio*cantidad)*IvaAplicado);
				
				System.out.println();
				System.out.print(nombre + " Precio: " + precio + " Cantidad: " + cantidad
						+ " Total: " + (precio*cantidad) + " IVA aplicado: " + IvaAplicado
						+ " Total con IVA: " + precioConIva);
				
				precioTotal += precioConIva;
				System.out.println();
				System.out.println();
			}
			
		} //end while
		
		System.out.println("Precio total de la compra: " + precioTotal + "€");
		System.out.println();
		System.out.println("Introduce la cantidad con la que vas a pagar:");
		double cantidad = sc.nextDouble();
		if(cantidad < precioTotal) {
			System.out.println("No dispones de la cantidad necesaria...");
			return;
		}
		
		System.out.println();
		System.out.println(" Cambio a devolver: " + (cantidad - precioTotal));
		System.out.println();
		System.out.println("¡Gracias por comprar en nuestras tiendas!");
		
		System.exit(0);
	}

}
