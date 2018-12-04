package PEVAL1;

/**
 * Clase Principal: donde creamos y ejecutamos x hilos, donde x es un
 * n�mero de iteraciones determinado
 * 
 * @author Jes�s Romero Gonz�lez
 * @see Rio
 * @see Thread
 * @version 1.0
 *
 */
public class Principal {
	/**
	 * M�todo principal
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Thread t;
		Rio guadalmedina = new Rio();
		/**
		 * creamos y ejecutamos los hilos creados
		 */
		for (int i = 0; i < 50; i++) {
			t = new Thread(new Coche(i, guadalmedina));
			t.start();
			t = new Thread(new Barco(i, guadalmedina));
			t.start();
		}
	}
}
