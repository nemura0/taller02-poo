/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package logica;

import java.util.Scanner;

import dominio.Formato;

public class Main {
	
	// carga los txt y abre el menu principal
	public static void main(String[] args) {
		Fonda fonda = new Fonda();
		cargarDatos(fonda);
		
		Scanner teclado = new Scanner(System.in);
		menuPrincipal(fonda, teclado);
		teclado.close();
	}
	
	// carga de archivos
	public static void cargarDatos(Fonda fonda) {
		if (!fonda.cargarParticipantes("Participantes.txt")) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Participantes.txt\".");
			System.out.println("   Se iniciara el programa sin participantes.");
		}
			
		if (!fonda.cargarConsumos("Consumido.txt")) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Consumido.txt\".");
			System.out.println("   Se iniciara el programa sin registros previos.");
		}
	}
	
	public static int leerOpcion(Scanner teclado, int min, int max) {
		while (true) {
			System.out.print("Seleccione una opcion: ");
			
			if (!teclado.hasNextLine()) {
				return max;
			}
			String texto = teclado.nextLine();
			
			if (texto.isEmpty()) {
				System.out.println(">> Error: no ingreso ninguna opcion. Intente nuevamente.");
			} else {
				try {
					int opcion = Integer.valueOf(texto);
					if (opcion >= min && opcion <= max) {
						return opcion;
					}
					System.out.println(">> Error: opcion fuera de rango. Intente nuevamente.");
				} catch (NumberFormatException e) {
					System.out.println(">> Error: la opcion debe ser un numero entre " + min
							+ " y " + max + ". Intente nuevamente.");
				}
			}
		}
	}
	
	public static void esperarEnter(Scanner teclado) {
		System.out.print("\nPresione ENTER para continuar...");
		if (teclado.hasNextLine()) {
			teclado.nextLine();
		}
	}
	
	// menu principal
	public static void menuPrincipal(Fonda fonda, Scanner teclado) {
		int opcion = 0;
		
		while (opcion != 4) {
			System.out.println();
			System.out.println("============================================");
			System.out.println("           LA FONDA DEL PAPU");
			System.out.println("============================================");
			System.out.println("  Registros cargados: " + fonda.getCantidadConsumos());
			System.out.println("--------------------------------------------");
			System.out.println("  [1] Ver registros");
			System.out.println("  [2] Actualizar registros");
			System.out.println("  [3] Calcular metricas");
			System.out.println("  [4] Salir");
			System.out.println("============================================");
			opcion = leerOpcion(teclado, 1, 4);
			
			if (opcion == 1) {
				menuVerRegistros(fonda, teclado);
			} else if (opcion == 2) {
				menuActualizar(fonda, teclado);
			} else if (opcion == 3) {
				menuMetricas(fonda, teclado);
			}
		}
		
		System.out.println("\n>> Hasta luego...");
	}
	
	public static void menuVerRegistros(Fonda fonda, Scanner teclado) {
		int opcion = 0;
		
		while (opcion != 3) {
			System.out.println();
			System.out.println("============ VER REGISTROS =================");
			System.out.println("  [1] Choripanes");
			System.out.println("  [2] Terremotos");
			System.out.println("  [3] Volver al menu principal");
			System.out.println("============================================");
			opcion = leerOpcion(teclado, 1, 3);
			
			if (opcion == 1) {
				mostrarChoripanes(fonda);
				esperarEnter(teclado);
			} else if (opcion == 2) {
				mostrarTerremotos(fonda);
				esperarEnter(teclado);
			}
		}
	}
	
	public static void menuActualizar(Fonda fonda, Scanner teclado) {
		int opcion = 0;
		
		while (opcion != 4) {
			System.out.println();
			System.out.println("========== ACTUALIZAR REGISTROS ============");
			System.out.println("  [1] Agregar");
			System.out.println("  [2] Editar");
			System.out.println("  [3] Eliminar");
			System.out.println("  [4] Volver al menu principal");
			System.out.println("============================================");
			opcion = leerOpcion(teclado, 1, 4);
			
			if (opcion >= 1 && opcion <= 3) {
				System.out.println("\n>> Opcion en construccion.");
			}
		}
	}
	
	public static void menuMetricas(Fonda fonda, Scanner teclado) {
		int opcion = 0;
		
		while (opcion != 7) {
			System.out.println();
			System.out.println("============ CALCULAR METRICAS =============");
			System.out.println("  [1] Choripanes comidos en total");
			System.out.println("  [2] Terremotos tomados en total");
			System.out.println("  [3] Choripanes comidos por usuario");
			System.out.println("  [4] Terremotos tomados por usuario");
			System.out.println("  [5] KCal consumidas por usuario");
			System.out.println("  [6] Dias ebrio por usuario");
			System.out.println("  [7] Volver al menu principal");
			System.out.println("============================================");
			opcion = leerOpcion(teclado, 1, 7);
			
			if (opcion >= 1 && opcion <= 6) {
				System.out.println("\n>> Opcion en construccion.");
			}
		}
	}
	
	public static void mostrarRegistros(Fonda fonda, String tipo) {
		int numero = 0;
		
		for (int i = 0; i < fonda.getCantidadConsumos(); i++) {
			if (fonda.getConsumo(i).getTipo().equals(tipo)) {
				numero++;
				System.out.println("  " + Formato.izquierda("" + numero, 4)
						+ fonda.getConsumo(i).getFila());
			}
		}
	}
	
	public static void mostrarChoripanes(Fonda fonda) {
		System.out.println();
		System.out.println("=============== CHORIPANES =================");
		System.out.println("  " + Formato.izquierda("#", 4) + Formato.izquierda("Consumidor", 12)
				+ Formato.derecha("Largo", 7) + Formato.derecha("Ancho", 8) + "   "
				+ Formato.izquierda("Fecha", 10) + Formato.derecha("KCal", 9));
		
		mostrarRegistros(fonda, "Choripan");
		
		System.out.println("--------------------------------------------------");
		System.out.println("  Total: " + fonda.contarPorTipo("Choripan") + " choripanes registrados");
		System.out.println("==================================================");
	}
	
	public static void mostrarTerremotos(Fonda fonda) {
		System.out.println();
		System.out.println("=============== TERREMOTOS =================");
		System.out.println("  " + Formato.izquierda("#", 4) + Formato.izquierda("Consumidor", 12)
				+ Formato.derecha("Litros", 8) + "   "
				+ Formato.izquierda("Fecha", 10) + Formato.derecha("KCal", 9));
		
		mostrarRegistros(fonda, "Terremoto");
		
		System.out.println("--------------------------------------------------");
		System.out.println("  Total: " + fonda.contarPorTipo("Terremoto") + " terremotos registrados ("
				+ Formato.decimal(fonda.sumarLitros()) + " L)");
		System.out.println("==================================================");
	}

}