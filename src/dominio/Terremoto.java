/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

// terremoto, se mide en litros
public class Terremoto extends Consumo {
	
	private double litros;
	
	// los datos comunes se los pasa a Consumo con super()
	public Terremoto(String consumidor, double litros, String fecha) {
		super(consumidor, fecha);
		this.litros = litros;
	}
	
	// litros del terremoto
	public double getLitros() {
		return litros;
	}
	
	// cambia los litros (se usa al editar)
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

}
