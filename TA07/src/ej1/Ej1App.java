package ej1;

//Autor Eloi Martorell Martin 11/04/2022

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Ej1App {
	
	static Scanner sc = new Scanner(System.in); //así podemos usarlo en toda la clase

	public static void main(String[] args) {

		Hashtable <String,Double> notasAlumnos = new Hashtable<String,Double>();
		
		int menu;
		do {
			System.out.println();
			System.out.println("1 Entrar datos - 2 Mostrar datos - 0 Salir");
			menu = sc.nextInt();
			
			switch (menu) {
			case 1:
				introducirNotas(notasAlumnos);
				break;
			case 2:
				proporcionarDatos(notasAlumnos);
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
	
	//metodo introducir notas alumno y clacular nota media String Nombre, Double media
	public static void introducirNotas(Hashtable<String, Double> notasAlumnos) {
		
		//introducir datos
		System.out.println("Nombre del alumno?");
		String nombre = sc.next();

		System.out.println("Cuantas notas quieres introducir?");
		double notas[] = new double[sc.nextInt()]; //guardamos las notas en un array por si encontraramos la forma de poder guardarlos
		double media = 0;
		
		for (int i = 0; i < notas.length; i++) {
			System.out.println("Introduce la nota numero " + (i+1) + ":");
			notas[i] = sc.nextDouble();
			media += notas[i];
		}
		
		//calcular media y guardarla
		media = media / notas.length;
		
		//guardarlos 
		notasAlumnos.put(nombre, media);
		
	}
	
	//metodo proporcionar datos
	public static void proporcionarDatos(Hashtable<String, Double> notasAlumnos) {
		
		Enumeration<String> enumerationNombres = notasAlumnos.keys();
		Enumeration<Double> enumerationNotas = notasAlumnos.elements();
		while (enumerationNotas.hasMoreElements()) {
			System.out.println(); //espacio
			System.out.print("" + enumerationNombres.nextElement() );
			System.out.print(" " + enumerationNotas.nextElement() );
		}
	}
	

}
