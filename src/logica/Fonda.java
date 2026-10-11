/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package logica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import dominio.Choripan;
import dominio.Consumo;
import dominio.Participante;
import dominio.Terremoto;

// guarda participantes y consumos en vectores fijos y carga de los txt
public class Fonda {
	
	public static final int MAX_REGISTROS = 200;
	
	private Participante[] participantes;
	private int cantidadParticipantes;
	
	private Consumo[] consumos;
	private int cantidadConsumos;
	
	private int lineasDescartadas;
	
	// parte vacía con los vectores listos para 200
	public Fonda() {
		participantes = new Participante[MAX_REGISTROS];
		cantidadParticipantes = 0;
		consumos = new Consumo[MAX_REGISTROS];
		cantidadConsumos = 0;
		lineasDescartadas = 0;
	}
	
	// lee un nombre por linea, salta vacias, repetidos y lo que pase de 200
	// devuelve false si el archivo no existe
	public boolean cargarParticipantes(String nombreArchivo) {
		try {
			Scanner lector = new Scanner(new File(nombreArchivo));
			
			while (lector.hasNextLine()) {
				String nombre = lector.nextLine();
				
				if (nombre.isEmpty() || existeParticipante(nombre)
						|| cantidadParticipantes >= MAX_REGISTROS) {
					lineasDescartadas++;
				} else {
					participantes[cantidadParticipantes] = new Participante(nombre);
					cantidadParticipantes++;
				}
			}
			
			lector.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	// lee los consumos, las lineas malas se saltan sin parar la lectura
	// hay que llamarlo despues de cargarParticipantes (solo acepta consumidores registrados)
	// devuelve false si el archivo no existe
	public boolean cargarConsumos(String nombreArchivo) {
		try {
			Scanner lector = new Scanner(new File(nombreArchivo));
			
			while (lector.hasNextLine()) {
				String linea = lector.nextLine();
				Consumo consumo = crearConsumo(linea);
				
				if (consumo == null || cantidadConsumos >= MAX_REGISTROS) {
					lineasDescartadas++;
				} else {
					consumos[cantidadConsumos] = consumo;
					cantidadConsumos++;
				}
			}
			
			lector.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
	
	// arma un Choripan o Terremoto desde una linea del txt
	// devuelve null si la linea no sirve (campos de mas o de menos, letras en vez
	// de numeros, medidas <= 0, consumidor desconocido o fecha mala)
	private Consumo crearConsumo(String linea) {
		String[] partes = linea.split(";");
		
		try {
			if (partes.length == 5 && partes[0].equals("Choripan")) {
				String consumidor = partes[1];
				int largo = Integer.valueOf(partes[2]);
				int ancho = Integer.valueOf(partes[3]);
				String fecha = partes[4];
				
				if (largo > 0 && ancho > 0 && existeParticipante(consumidor)
						&& esFechaValida(fecha)) {
					return new Choripan(consumidor, largo, ancho, fecha);
				}
			} else if (partes.length == 4 && partes[0].equals("Terremoto")) {
				String consumidor = partes[1];
				double litros = Double.valueOf(partes[2]);
				String fecha = partes[3];
				
				if (litros > 0 && existeParticipante(consumidor) && esFechaValida(fecha)) {
					return new Terremoto(consumidor, litros, fecha);
				}
			}
		} catch (NumberFormatException e) {
			// venia texto donde iba un numero, se descarta la linea
		}
		
		return null;
	}
	
	// true si hay un participante con ese nombre exacto
	public boolean existeParticipante(String nombre) {
		for (int i = 0; i < cantidadParticipantes; i++) {
			if (participantes[i].getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}
	
	// revisa formato YYYY-MM-DD, mes 1-12 y dia 1-31
	public boolean esFechaValida(String fecha) {
		String[] partes = fecha.split("-");
		
		if (fecha.length() != 10 || partes.length != 3 || partes[0].length() != 4
				|| partes[1].length() != 2 || partes[2].length() != 2) {
			return false;
		}
		
		try {
			int anio = Integer.valueOf(partes[0]);
			int mes = Integer.valueOf(partes[1]);
			int dia = Integer.valueOf(partes[2]);
			return anio > 0 && mes >= 1 && mes <= 12 && dia >= 1 && dia <= 31;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public int getCantidadParticipantes() {
		return cantidadParticipantes;
	}
	
	// participante en esa posicion, o null si el indice no existe
	public Participante getParticipante(int indice) {
		if (indice < 0 || indice >= cantidadParticipantes) {
			return null;
		}
		return participantes[indice];
	}
	
	public int getCantidadConsumos() {
		return cantidadConsumos;
	}
	
	// consumo en esa posicion, o null si el indice no existe
	public Consumo getConsumo(int indice) {
		if (indice < 0 || indice >= cantidadConsumos) {
			return null;
		}
		return consumos[indice];
	}
	
	// lineas de los txt que no se cargaron (vacias, malas, repetidas o sobre el tope)
	public int getLineasDescartadas() {
		return lineasDescartadas;
	}

}
