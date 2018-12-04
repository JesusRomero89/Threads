package PEVAL1;

/**
 * Clase Barco: creamos un hilo y llamamos a los m�todos avisar(), cruzar() y
 * salirDeCruce() de la clase Rio
 * 
 * @author Jes�s Romero Gonz�lez
 * @see Rio
 * @version 1.0
 * 
 */
public class Barco extends Thread {
	String nombre;
	Rio rio;

	/**
	 * Constructor
	 * 
	 * @param num <int> n�mero identificativo del veh�culo
	 * @param rio <Rio> objeto de la clase Rio
	 */
	public Barco(int num, Rio rio) {
		this.rio = rio;
		this.setName("barco" + num);
	}

	/**
	 * Llamadas a los m�todos avisar, cruzar y salirDeCruce de la clase Rio
	 */
	@Override
	public void run() {
		this.rio.avisar(this.getName());
		this.rio.cruzar(this.getName());
		this.rio.salirDeCruce(this.getName());
	}
}
