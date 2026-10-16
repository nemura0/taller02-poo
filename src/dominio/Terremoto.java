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
	
	// litros del terremoto (sobrescribe el 0 de Consumo)
	@Override
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
	
	// consumidor, litros, fecha y kcal alineados en columnas
	@Override
	public String getFila() {
		return Formato.izquierda(getConsumidor(), 12)
				+ Formato.derecha(Formato.decimal(litros) + " L", 8)
				+ "   " + getFecha()
				+ Formato.derecha(Formato.decimal(calcularKcal()), 9);
	}
	
	@Override
	public String getDetalle() {
		return "Terremoto | " + getConsumidor() + " | " + Formato.decimal(litros) + " L | " + getFecha();
	}
	
	// mismo formato del txt: Terremoto;Consumidor;Litros;Fecha
	// si los litros son enteros se escriben sin decimal (2 y no 2.0), igual que el original
	@Override
	public String toLineaArchivo() {
		String textoLitros;
		if (litros == (int) litros) {
			textoLitros = "" + (int) litros;
		} else {
			textoLitros = "" + litros;
		}
		return "Terremoto;" + getConsumidor() + ";" + textoLitros + ";" + getFecha();
	}

}
