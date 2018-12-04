package exapsp2018;

public class Principal {

	public static void main(String[] args) {
		Thread hilo;
		PistaAterrizaje pista = new PistaAterrizaje();
		
		for(int i=0; i<20; i++) {
			hilo = new Thread(new Avion(i+1, pista));
			hilo.start();
			
		}
	}
	
}
