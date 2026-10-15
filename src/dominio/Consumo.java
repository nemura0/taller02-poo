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
	
	public double getLitros() {
		return 0;
	}
	
	// kcal del consumo, cada tipo tuene su formula
	public abstract double calcularKcal();
	
	// tipo del consumo tal como sale en el txt: "Choripan" o "Terremoto"
	public abstract String getTipo();
	
	// fila para la tabla de "ver registros" cada tipo muestra sus propias columnas
	public abstract String getFila();
	
}
