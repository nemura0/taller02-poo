/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

public class Choripan extends Consumo {
	
	private int largo;
	private int ancho;
	
	public Choripan(String consumidor, int largo, int ancho, String fecha) {
		super(consumidor, fecha);
		this.largo = largo;
		this.ancho = ancho;
	}

	public int getLargo() {
		return largo;
	}

	public void setLargo(int largo) {
		this.largo = largo;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	// (largo * ancho) / 3, se divide por 3.0 para no caer en division entera
	@Override
	public double calcularKcal() {
		return (largo * ancho) / 3.0;
	}
	
	@Override
	public String getTipo() {
		return "Choripan";
	}
	
	@Override
	public String getFila() {
		return Formato.izquierda(getConsumidor(), 12)
				+ Formato.derecha(largo + " cm", 7)
				+ Formato.derecha(ancho + " cm", 8)
				+ "   " + getFecha()
				+ Formato.derecha(Formato.decimal(calcularKcal()), 9);
	}

}
