/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

public class Formato {
	
	// deja un numero con 2 decimales (ej: 6.666 -> 6.67 y 10 -> 10.00)
	public static String decimal(double numero) {
		int centesimas = (int) (numero * 100 + 0.5);
		int parteEntera = centesimas / 100;
		int parteDecimal = centesimas % 100;
		
		if (parteDecimal < 10) {
			return parteEntera + ".0" + parteDecimal;
		}
		return parteEntera + "." + parteDecimal;
		
	}
	
	// rellena con espacios a la derecha hasta el ancho
	public static String izquierda(String texto, int ancho) {
		String resultado = texto;
		while (resultado.length() < ancho) {
			resultado = resultado + " ";
		}
		return resultado;
	}
	
	// rellena con espacios a la izquierda hasta el ancho
	public static String derecha(String texto, int ancho) {
		String resultado = texto;
		while (resultado.length() < ancho) {
			resultado = " " + resultado;
		}
		return resultado;
	}

}
