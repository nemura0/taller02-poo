/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package logica;

public class Main {
	
	// carga los txt y muestra un resumen (despues aca va el menu)
	public static void main(String[] args) {
		Fonda fonda = new Fonda();
		
		if (!fonda.cargarParticipantes("Participantes.txt")) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Participantes.txt\".");
			System.out.println("   Se iniciara el programa sin participantes.");
		}
		
		if (!fonda.cargarConsumos("Consumido.txt")) {
			System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Consumido.txt\".");
			System.out.println("   Se iniciara el programa sin registros previos.");
		}
		
		mostrarResumenCarga(fonda);

	}
	
	// muestra cuanto se cargo de cada cosa (temporal, para probar la carga)
	public static void mostrarResumenCarga(Fonda fonda) {
		int choripanes = 0;
		int terremotos = 0;
		
		for (int i = 0; i < fonda.getCantidadConsumos(); i++) {
			if (fonda.getConsumo(i).getTipo().equals("Choripan")) {
				choripanes++;
			} else {
				terremotos++;
			}
		}
		
		System.out.println("Participantes cargados: " + fonda.getCantidadParticipantes());
		System.out.println("Registros cargados: " + fonda.getCantidadConsumos()
				+ " (" + choripanes + " choripanes, " + terremotos + " terremotos)");
		System.out.println("Lineas descartadas: " + fonda.getLineasDescartadas());
	}

}
