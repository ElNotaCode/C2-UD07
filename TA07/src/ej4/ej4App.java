package ej4;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

//Autor Eloi Martorell Martin 13/04/2022

public class ej4App {
	
	static Scanner sc = new Scanner(System.in); //así podemos usarlo en toda la clase

	//cargamos las tablas de los productos
	static Hashtable<String,String> nombreProductos = new Hashtable<String,String>();
	static Hashtable<String,Double> precioProductos = new Hashtable<String,Double>();
	static Hashtable<String,Double> IVAProductos = new Hashtable<String,Double>();
	static Hashtable<String,Integer> carrito = new Hashtable<String,Integer>();
	static Hashtable<String, Integer> stock = new Hashtable<String, Integer>(); //para la próxima usar stockProductos
	
	static int IdIncremental; // vamos a usar esto para hacer la id incremental en un metodo
	
	public static void main(String[] args) {

		cargarProductos();
		IdIncremental = nombreProductos.size();
		//DONE Precargar stock
		//DONE Seleccionador de IVA al agregar
		//DONE Mostrar IVA en la tabla y editor
		//DONE mostrar stock en carrito
		//DONE comprobar stock al comprar
		
		System.out.println("¡Bienvenido a Stock Manager™:");
		int menu;
		do {
			System.out.println();
			System.out.println("1 Acceder a Stock Manager™ - 2 Acceder como cliente - 0 Salir");
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				menuEmpleado();
				break;
			case 2:
				menuCliente();
				break;
			case 0:
				System.out.println("¡Gracias por usar mi programa!");
				break;
			default:
				System.out.println("¡Tienes que introducir una de las opciones!");
				break;
			}
			
		}while(menu != 0);
		
		sc.close();

	}
	
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
		
		stock.put("1", 7);
		stock.put("2", 19);
		stock.put("3", 4);
		stock.put("4", 17);
		stock.put("5", 9);
		
		carrito.put("1", 0);
		carrito.put("2", 0);
		carrito.put("3", 0);
		carrito.put("4", 0);
		carrito.put("5", 0);
		
	}

	//PORTAL EMPLEADO y sus metodos
	public static void menuEmpleado() {
		System.out.println("¡Bienvenido a Stock Manager™:");
		int menu;
		do {
			
			System.out.println("1 Agregar un producto - 2 Vista editor - 3 Listar productos - 0 Salir");
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				agregarProductos();
				break;
			case 2:
				vistaEditor();
				break;
			case 3:
				listarProductos();
				break;
			case 0:
				System.out.println("Redirigiendo al menú principal");
				break;
			default:
				System.out.println("¡Tienes que introducir una de las opciones!");
				break;
			}
			
		}while(menu != 0);
	}
	//este metodo nos saca la ultima id y la pasa a int, pudiendo así hacer un incremental para agregar una nueva tabla
	public static int getLast() {
			
		IdIncremental++;
		
		return IdIncremental;
			
		}
		
	//añadir articulos
	public static void agregarProductos() {
			
			
			
			int id = getLast();
			
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
			double iva;
			boolean ivaBien = false;
			do {
				System.out.println("Introduce el IVA (0,21 o 0,04):");
				iva = sc.nextDouble();
				if(iva == 0.21 || iva == 0.04) 
					ivaBien = true;
				
			}while(!ivaBien);
			System.out.println();
			Integer stockInt;
			do {
				System.out.println("Introduce una cantidad de stock valida:");
				stockInt = sc.nextInt();
				
			}while (stockInt < 0); //puede llegar a ser un producto sin stock

			//metemos la info
			nombreProductos.put(idString, nombre);
			precioProductos.put(idString, precio);
			IVAProductos.put(idString,iva);
			stock.put(idString, stockInt);
		}
		
	//consultar info especifica / editar
	public static void vistaEditor() {
			
			//vista general con info para saber la ID y la info a editar
			listarProductos();
			//preguntar ID
			int idSc;
			do{
				System.out.println("Introduce una Id de un producto para la vista editor:");
				idSc = sc.nextInt();
			}while(idSc <= 0);
			String IdScStr = new String(Integer.toString(idSc));
			
			//abrir vista de la ID
			Object value = null;
			Iterator<String> iter = nombreProductos.keySet().iterator();
			
			while (iter.hasNext()) {
				value = iter.next();
				//nombre //precio //stock
				if(IdScStr.equals(value))
				System.out.println( nombreProductos.get(value) + " Precio: " + precioProductos.get(value) +
						" Stock: " + stock.get(value));
				
			}
			
			//preguntar si quiere editar
			System.out.println("Quieres editar el producto? (Introduce Si o No)");
			String opcionString = new String();
			opcionString = sc.next();
			opcionString = opcionString.toUpperCase();
			if(opcionString.equals("SI")) {
				
				System.out.println("Borrar o editar?");
				opcionString = sc.next();
				opcionString = opcionString.toUpperCase();
				if(opcionString.equals("BORRAR")) {
					borrarProducto(IdScStr);
				}else if(opcionString.equals("EDITAR")) {
					editarProducto(IdScStr);
					
				}

			}
			
		}
		
	//borrar
	public static void borrarProducto(String id) {
			
			Object key = null;
			Iterator<String> iter = nombreProductos.keySet().iterator();
			
			while (iter.hasNext()) {
				
				key = iter.next();
				if(id.equals(key)) {
					
					precioProductos.remove(key);
					IVAProductos.remove(key);
					stock.remove(key);
					nombreProductos.remove(key);
				}
				
			}
		
			listarProductos();
			
		}
		
	public static void editarProducto(String id) {
			
			Object key = null;
			Iterator<String> iter = nombreProductos.keySet().iterator();
			
			while (iter.hasNext()) {
				
				key = iter.next();
				if(id.equals(key)) {
					
					System.out.println("Introduce un nuevo nombre:");
					String nombre = new String(sc.next());
					double precio;
					do {
					System.out.println("Introduce un precio valido:");
					precio = sc.nextDouble();
					}while(precio <= 0);
					double iva;
					boolean ivaBien = false;
					do {
						System.out.println("Introduce el IVA (0,21 o 0,04):");
						iva = sc.nextDouble();
						if(iva == 0.21 || iva == 0.04) 
							ivaBien = true;
						
					}while(!ivaBien);
					Integer  stockInt;
					do {
						System.out.println("Introduce un stock valido:");
						stockInt = sc.nextInt();
					}while(stockInt < 0);
					
					nombreProductos.put(id, nombre);
					precioProductos.put(id, precio);
					IVAProductos.put(id, iva);
					stock.put(id,stockInt);
					
					System.out.println( nombreProductos.get(id) + " Precio: " + precioProductos.get(id) +
							"IVA: "+ IVAProductos.get(id) + "Stock: " + stock.get(id));
					
				}
				
			}
			
		}
		
	//listar informacion
	public static void listarProductos() {
			
			Enumeration<String> enumerationId = nombreProductos.keys();
			Enumeration<String> enumerationNombreProductos = nombreProductos.elements();
			Enumeration<Double> enumerationPrecioProductos = precioProductos.elements();
			Enumeration<Double> enumerationIVA = IVAProductos.elements();
			Enumeration<Integer> enumerationStock = stock.elements();
			
			while (enumerationId.hasMoreElements()) {
				System.out.println(); //espacio
				System.out.print("" + enumerationId.nextElement() );
				System.out.print(" " + enumerationNombreProductos.nextElement() );
				System.out.print(" Precio: " + enumerationPrecioProductos.nextElement() );
				System.out.print(" IVA: " + enumerationIVA.nextElement() );
				System.out.print(" Stock: " + enumerationStock.nextElement() );
			}
			
			System.out.println();
			System.out.println();
			
		}

	//PORTAL CLIENTE y sus metodos
	public static void menuCliente() {
		int menu;
		do {
			mostrarCarrito();
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
				System.out.println("¡Gracias por su compra! Ahora se le redirigirá al menú principal.");
				break;
			default:
				System.out.println("¡Tienes que introducir una de las opciones!");
				break;
			}
			
		}while(menu != 0);
	}
	
	//mostrar carrito
	public static void mostrarCarrito() {
			
			Enumeration<String> enumerationId = carrito.keys();
			Enumeration<String> enumerationNombreProductos = nombreProductos.elements();
			Enumeration<Integer> enumerationCantidad = carrito.elements();
			Enumeration<Integer> enumerationStock = stock.elements();
			
			while (enumerationCantidad.hasMoreElements()) {
				System.out.println(); //espacio
				System.out.print("" + enumerationId.nextElement() );
				System.out.print(" " + enumerationNombreProductos.nextElement() );
				System.out.print(" Stock: " + enumerationStock.nextElement() );
				System.out.print(" En el carrito:" + enumerationCantidad.nextElement() );
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
			int stockInt = 0; //el stock nos ayudará a que el cliente no se pase en el if de cantidad
			
			do {
				
				//seleccionar producto
				System.out.println("Selecciona un producto introduciendo su id numerico:");
				producto = sc.nextInt();
				productoId = producto + "";
				
				Object key = null;
				Iterator<String> iter = nombreProductos.keySet().iterator();
				while (iter.hasNext()) {
					key = iter.next();
					if(productoId.equals(key)) {
						checkProducto = true;
						stockInt = stock.get(key);
					}
				}
				
			}while(!checkProducto);
			
			//seleccionar cantidad
			
			boolean checkCantidad = false;
			
			do {
				
				//seleccionar producto
				System.out.println("Selecciona una cantidad (max " + stockInt +") :");
				cantidad = sc.nextInt();
				
				if(cantidad > -1 && cantidad <= stockInt) {
					checkCantidad = true;
				}
				
			}while(!checkCantidad);
			
			//guardar
			carrito.put(productoId, cantidad);
			
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
			
			Object value = null;
			Iterator<String> iter = carrito.keySet().iterator(); //pillamos la key
			
			while (iter.hasNext()) {
				//sacamos los ids y la cantidad 
				value = iter.next();
				int cantidad = carrito.get(value);
				
				if(cantidad > 0) {
					//Nombre, precio y precio + IVA
					String nombre = nombreProductos.get(value);
					double precio = precioProductos.get(value);
					double IvaAplicado = IVAProductos.get(value);
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
