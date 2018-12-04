package PEVAL1;

/**
 * Clase Coche: creamos un nuevo Thread y llamamos a los m�todos cruzar() y
 * salirDeCruce() de la clase Rio
 * 
 * @author Jes�s Romero Gonz�les
 * @see Rio
 * @version 1.0
 *
 */
public class Coche extends Thread {
	String nombre;
	Rio rio;

	/**
	 * Constructor
	 * 
	 * @param num <int> n�mero identificativo del veh�culo
	 * @param rio <Rio> objeto de la clase Rio
	 */
	public Coche(int num, Rio rio) {
		this.rio = rio;
		this.setName("coche" + num);
	}

	/**
	 * Llamadas a los m�todos cruzar y salirDeCruce de la clase Rio
	 */
	@Override
	public void run() {
		this.rio.cruzar(this.getName());
		this.rio.salirDeCruce(this.getName());
	}
}
