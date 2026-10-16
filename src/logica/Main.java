/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package logica;

import java.util.Scanner;

import dominio.Choripan;
import dominio.Consumo;
import dominio.Formato;
import dominio.Terremoto;

public class Main {
	
	// nombres de los archivos de datos (estan en la raiz del proyecto)
	public static final String ARCHIVO_PARTICIPANTES = "Participantes.txt";
	public static final String ARCHIVO_CONSUMOS = "Consumido.txt";
	
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
		if (!fonda.cargarParticipantes(ARCHIVO_PARTICIPANTES)) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Participantes.txt\".");
			System.out.println("   Se iniciara el programa sin participantes.");
		}
			
		if (!fonda.cargarConsumos(ARCHIVO_CONSUMOS)) {
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
			
			if (texto.length() == 0) {
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
			
			if (opcion == 1) {
				agregarConsumo(fonda, teclado);
			} else if (opcion == 2) {
				editarConsumo(fonda, teclado);
			} else if (opcion == 3) {
				eliminarConsumo(fonda, teclado);
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
			
			if (opcion == 1) {
				mostrarTotalChoripanes(fonda);
			} else if (opcion == 2) {
				mostrarTotalTerremotos(fonda);
			} else if (opcion == 3) {
				mostrarChoripanesPorUsuario(fonda);
			} else if (opcion == 4) {
				mostrarTerremotosPorUsuario(fonda);
			} else if (opcion == 5) {
				mostrarKcalPorUsuario(fonda);
			} else if (opcion == 6) {
				mostrarDiasEbrio(fonda);
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
	
	// guarda los cambios en el txt y avisa si no se pudo
	// devuelve true si se guardo bien
	public static boolean guardarCambios(Fonda fonda) {
		if (fonda.guardarConsumos(ARCHIVO_CONSUMOS)) {
			return true;
		}
		System.out.println(">> Error: no se pudo escribir en \"" + ARCHIVO_CONSUMOS + "\".");
		return false;
	}
	
	// metrica 1: total de choripanes
	public static void mostrarTotalChoripanes(Fonda fonda) {
		System.out.println("\n>> Total de choripanes comidos: " + fonda.contarPorTipo("Choripan"));
	}
	
	// metrica 2: total de terremotos y litros
	public static void mostrarTotalTerremotos(Fonda fonda) {
		System.out.println("\n>> Total de terremotos tomados: " + fonda.contarPorTipo("Terremoto")
				+ "  (" + Formato.decimal(fonda.sumarLitros()) + " litros en total)");
	}
	
	// ================== ACTUALIZAR REGISTROS ==================
	
	// mini menu para elegir el tipo de registro
	// devuelve "Choripan", "Terremoto" o "" si se cancela
	public static String elegirTipo(Scanner teclado, String titulo) {
		System.out.println("\n" + titulo);
		System.out.println("  [1] Choripan");
		System.out.println("  [2] Terremoto");
		System.out.println("  [3] Cancelar");
		int opcion = leerOpcion(teclado, 1, 3);
		
		if (opcion == 1) {
			return "Choripan";
		} else if (opcion == 2) {
			return "Terremoto";
		}
		return "";
	}
	
	// muestra los participantes numerados y devuelve el nombre elegido
	public static String elegirParticipante(Fonda fonda, Scanner teclado) {
		System.out.println("\nConsumidores disponibles:");
		for (int i = 0; i < fonda.getCantidadParticipantes(); i++) {
			System.out.println("  [" + (i + 1) + "] " + fonda.getParticipante(i).getNombre());
		}
		int numero = leerOpcion(teclado, 1, fonda.getCantidadParticipantes());
		return fonda.getParticipante(numero - 1).getNombre();
	}
	
	// pide un entero mayor a 0 hasta que sea valido
	// si valorActual es mayor a 0 y se deja vacio, se mantiene ese valor (para editar)
	// devuelve 0 si se acaba la entrada
	public static int leerEntero(Scanner teclado, String pregunta, int valorActual) {
		while (true) {
			System.out.print(pregunta);
			if (!teclado.hasNextLine()) {
				return 0;
			}
			String texto = teclado.nextLine();
			
			if (texto.length() == 0 && valorActual > 0) {
				return valorActual;
			}
			try {
				int numero = Integer.valueOf(texto);
				if (numero > 0) {
					return numero;
				}
			} catch (NumberFormatException e) {
				// no era un numero, se vuelve a preguntar
			}
			System.out.println(">> Error: debe ser un numero entero mayor a 0. Intente nuevamente.");
		}
	}
	
	// igual que leerEntero pero para los litros (acepta decimales)
	public static double leerLitros(Scanner teclado, String pregunta, double valorActual) {
		while (true) {
			System.out.print(pregunta);
			if (!teclado.hasNextLine()) {
				return 0;
			}
			String texto = teclado.nextLine();
			
			if (texto.length() == 0 && valorActual > 0) {
				return valorActual;
			}
			try {
				double litros = Double.valueOf(texto);
				if (litros > 0) {
					return litros;
				}
			} catch (NumberFormatException e) {
				// no era un numero, se vuelve a preguntar
			}
			System.out.println(">> Error: los litros deben ser un numero mayor a 0. Intente nuevamente.");
		}
	}
	
	// pide una fecha YYYY-MM-DD valida
	// si valorActual no esta vacio y se deja vacio, se mantiene ese valor (para editar)
	// devuelve null si se acaba la entrada
	public static String leerFecha(Fonda fonda, Scanner teclado, String pregunta, String valorActual) {
		while (true) {
			System.out.print(pregunta);
			if (!teclado.hasNextLine()) {
				return null;
			}
			String texto = teclado.nextLine();
			
			if (texto.length() == 0 && valorActual.length() != 0) {
				return valorActual;
			}
			if (fonda.esFechaValida(texto)) {
				return texto;
			}
			System.out.println(">> Error: la fecha debe tener el formato YYYY-MM-DD. Intente nuevamente.");
		}
	}
	
	// pide el nombre del consumidor al editar, vacio mantiene el actual
	// devuelve null si se acaba la entrada
	public static String leerConsumidor(Fonda fonda, Scanner teclado, String valorActual) {
		while (true) {
			System.out.print("Consumidor [" + valorActual + "]: ");
			if (!teclado.hasNextLine()) {
				return null;
			}
			String texto = teclado.nextLine();
			
			if (texto.length() == 0) {
				return valorActual;
			}
			if (fonda.existeParticipante(texto)) {
				return texto;
			}
			System.out.println(">> Error: ese consumidor no esta en Participantes.txt. Intente nuevamente.");
		}
	}
	
	// muestra la tabla del tipo y pide el numero del registro (0 para cancelar)
	// devuelve la posicion real en el vector, o -1 si se cancela
	public static int elegirRegistro(Fonda fonda, Scanner teclado, String tipo, String accion) {
		if (fonda.contarPorTipo(tipo) == 0) {
			System.out.println("\n>> No hay registros de ese tipo.");
			return -1;
		}
		
		if (tipo.equals("Choripan")) {
			mostrarChoripanes(fonda);
		} else {
			mostrarTerremotos(fonda);
		}
		
		while (true) {
			System.out.print("Seleccione el registro a " + accion + " (0 para cancelar): ");
			if (!teclado.hasNextLine()) {
				return -1;
			}
			String texto = teclado.nextLine();
			
			try {
				int numero = Integer.valueOf(texto);
				if (numero == 0) {
					return -1;
				}
				int indice = fonda.buscarIndice(tipo, numero);
				if (indice != -1) {
					return indice;
				}
			} catch (NumberFormatException e) {
				// no era un numero, se vuelve a preguntar
			}
			System.out.println(">> Error: no existe un registro con ese numero. Intente nuevamente.");
		}
	}
	
	// agrega un choripan o terremoto pidiendo y validando cada dato
	public static void agregarConsumo(Fonda fonda, Scanner teclado) {
		if (fonda.getCantidadConsumos() >= Fonda.MAX_REGISTROS) {
			System.out.println("\n>> Error: se alcanzo la capacidad maxima de registros (" + Fonda.MAX_REGISTROS + ").");
			System.out.println("   No es posible agregar mas consumos.");
			return;
		}
		if (fonda.getCantidadParticipantes() == 0) {
			System.out.println("\n>> Error: no hay participantes cargados.");
			return;
		}
		
		String tipo = elegirTipo(teclado, "Tipo de registro:");
		if (tipo.length() == 0) {
			return;
		}
		String consumidor = elegirParticipante(fonda, teclado);
		
		Consumo nuevo;
		if (tipo.equals("Choripan")) {
			int largo = leerEntero(teclado, "Largo del choripan (cm): ", 0);
			int ancho = leerEntero(teclado, "Ancho del choripan (cm): ", 0);
			String fecha = leerFecha(fonda, teclado, "Fecha (YYYY-MM-DD): ", "");
			if (largo == 0 || ancho == 0 || fecha == null) {
				return;
			}
			nuevo = new Choripan(consumidor, largo, ancho, fecha);
		} else {
			double litros = leerLitros(teclado, "Litros del terremoto: ", 0);
			String fecha = leerFecha(fonda, teclado, "Fecha (YYYY-MM-DD): ", "");
			if (litros == 0 || fecha == null) {
				return;
			}
			nuevo = new Terremoto(consumidor, litros, fecha);
		}
		
		if (fonda.agregarConsumo(nuevo) && guardarCambios(fonda)) {
			System.out.println("\n>> Registro agregado correctamente y guardado en Consumido.txt");
			System.out.println("   " + nuevo.getDetalle() + " | " + Formato.decimal(nuevo.calcularKcal()) + " kcal");
		}
	}
	
	// edita un registro: vacio en un campo mantiene el valor actual
	public static void editarConsumo(Fonda fonda, Scanner teclado) {
		String tipo = elegirTipo(teclado, "Tipo de registro a editar:");
		if (tipo.length() == 0) {
			return;
		}
		int indice = elegirRegistro(fonda, teclado, tipo, "editar");
		if (indice == -1) {
			return;
		}
		
		Consumo actual = fonda.getConsumo(indice);
		System.out.println("\nRegistro actual -> " + actual.getDetalle());
		System.out.println("Deje el campo vacio para mantener el valor actual.\n");
		
		String consumidor = leerConsumidor(fonda, teclado, actual.getConsumidor());
		if (consumidor == null) {
			return;
		}
		
		Consumo nuevo;
		if (tipo.equals("Choripan")) {
			int largo = leerEntero(teclado, "Largo [" + actual.getLargo() + "]: ", actual.getLargo());
			int ancho = leerEntero(teclado, "Ancho [" + actual.getAncho() + "]: ", actual.getAncho());
			String fecha = leerFecha(fonda, teclado, "Fecha [" + actual.getFecha() + "]: ", actual.getFecha());
			if (largo == 0 || ancho == 0 || fecha == null) {
				return;
			}
			nuevo = new Choripan(consumidor, largo, ancho, fecha);
		} else {
			double litros = leerLitros(teclado, "Litros [" + Formato.decimal(actual.getLitros()) + "]: ",
					actual.getLitros());
			String fecha = leerFecha(fonda, teclado, "Fecha [" + actual.getFecha() + "]: ", actual.getFecha());
			if (litros == 0 || fecha == null) {
				return;
			}
			nuevo = new Terremoto(consumidor, litros, fecha);
		}
		
		if (fonda.reemplazarConsumo(indice, nuevo) && guardarCambios(fonda)) {
			System.out.println("\n>> Registro actualizado correctamente y guardado en Consumido.txt");
			System.out.println("   " + nuevo.getDetalle() + " | " + Formato.decimal(nuevo.calcularKcal()) + " kcal");
		}
	}
	
	// elimina un registro despues de pedir confirmacion
	public static void eliminarConsumo(Fonda fonda, Scanner teclado) {
		String tipo = elegirTipo(teclado, "Tipo de registro a eliminar:");
		if (tipo.length() == 0) {
			return;
		}
		int indice = elegirRegistro(fonda, teclado, tipo, "eliminar");
		if (indice == -1) {
			return;
		}
		
		System.out.print("Confirma la eliminacion de \"" + fonda.getConsumo(indice).getDetalle() + "\"? (s/n): ");
		if (!teclado.hasNextLine()) {
			return;
		}
		String respuesta = teclado.nextLine();
		
		if (!respuesta.equalsIgnoreCase("s")) {
			System.out.println("\n>> Eliminacion cancelada.");
			return;
		}
		if (fonda.eliminarConsumo(indice) && guardarCambios(fonda)) {
			System.out.println("\n>> Registro eliminado correctamente. Cambios guardados en Consumido.txt");
		}
	}
	
	// ================== METRICAS POR USUARIO ==================
	
	// pendiente (siguiente commit): cantidad de choripanes por usuario
	public static void mostrarChoripanesPorUsuario(Fonda fonda) {
		System.out.println("\n>> Opcion en construccion.");
	}
	
	// pendiente (siguiente commit): cantidad de terremotos y litros por usuario
	public static void mostrarTerremotosPorUsuario(Fonda fonda) {
		System.out.println("\n>> Opcion en construccion.");
	}
	
	// pendiente (siguiente commit): kcal por usuario y total de la fonda
	public static void mostrarKcalPorUsuario(Fonda fonda) {
		System.out.println("\n>> Opcion en construccion.");
	}
	
	// pendiente (siguiente commit): dias ebrio por usuario
	public static void mostrarDiasEbrio(Fonda fonda) {
		System.out.println("\n>> Opcion en construccion.");
	}

}
