/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

// terremoto, se mide en litros
public class Terremoto extends Consumo {
	
	private double litros;
	
	public Terremoto(String consumidor, double litros, String fecha) {
		super(consumidor, fecha);
		this.litros = litros;
	}
	
	public double getLitros() {
		return litros;
	}
	
	public void setLitros(double litros) {
		this.litros = litros;
	}
	
	// (litros * 10) / 3, aca no hay problema porque litros ya es double
	@Override
	public double calcularKcal() {
		return (litros * 10) / 3;
	}
	
	@Override
	public String getTipo() {
		return "Terremoto";
	}
	
	@Override
	public String getFila() {
		return Formato.izquierda(getConsumidor(), 12)
				+ Formato.derecha(Formato.decimal(litros) + " L", 8)
				+ "   " + getFecha()
				+ Formato.derecha(Formato.decimal(calcularKcal()), 9);
	}

}
