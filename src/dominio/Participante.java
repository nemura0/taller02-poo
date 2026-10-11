/* Vicente Guerra - 21.855.415-6 - ITI
 * Diego Meneses - 22.087.010-3 - ICCI
 */

package dominio;

public class Participante {
	
	private String nombre;
	
	public Participante(String nombre) {
		this.nombre = nombre;
	}
	
	// nombre tal como sale en Participantes.txt
	public String getNombre() {
		return nombre;
	}

}
