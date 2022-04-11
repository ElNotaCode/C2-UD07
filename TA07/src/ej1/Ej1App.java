package ej1;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class Ej1App {
	
	static Scanner sc = new Scanner(System.in); //así podemos usarlo en toda la clase

	public static void main(String[] args) {

		Hashtable <String,Double> notasAlumnos = new Hashtable<String,Double>();
		
		introducirNotas(notasAlumnos);
		proporcionarDatos(notasAlumnos);
		
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
