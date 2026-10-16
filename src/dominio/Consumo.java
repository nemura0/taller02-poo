/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

// clase base de los consumos, guarda lo que tienen en comun: quien y cuando
public abstract class Consumo {
	
	private String consumidor;
	private String fecha;
	
	// constructor con los datos comunes, lo llaman las subclases con super()
	public Consumo(String consumidor, String fecha) {
		this.consumidor = consumidor;
		this.fecha = fecha;
	}
	
	public String getConsumidor() {
		return consumidor;
	}
	
	public void setConsumidor(String consumidor) {
		this.consumidor = consumidor;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	// litros del consumo, por defecto 0 (solo el terremoto tiene litros y lo sobrescribe)
	public double getLitros() {
		return 0;
	}
	
	// largo y ancho del consumo, por defecto 0 (solo el choripan los tiene y los sobrescribe)
	// asi al editar se leen los valores actuales sin castear a Choripan
	public int getLargo() {
		return 0;
	}
	
	public int getAncho() {
		return 0;
	}
	
	// kcal del consumo, cada tipo tiene su formula
	public abstract double calcularKcal();
	
	// tipo del consumo tal como sale en el txt: "Choripan" o "Terremoto"
	public abstract String getTipo();
	
	// fila para la tabla de "ver registros", cada tipo muestra sus propias columnas
	public abstract String getFila();
	
	// resumen en una linea, ej: "Choripan | Koshao | 10 cm x 3 cm | 2026-09-16"
	public abstract String getDetalle();
	
	// linea tal como va en Consumido.txt, ej: "Choripan;Koshao;10;3;2026-09-16"
	public abstract String toLineaArchivo();

}
