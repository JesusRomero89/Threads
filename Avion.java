package exapsp2018;

public class Avion extends Thread {
	String nombre;
	PistaAterrizaje pista;

	public Avion(int numero, PistaAterrizaje pista) {
		this.setName("avion" + numero);
		this.pista = pista;
	}

	@Override
	public void run() {
		try {
			this.pista.solicitarDespegue(this.getName());
			this.pista.despegue(this.getName());
//			Thread.sleep(1000);
			this.pista.solicitarAterrizaje(this.getName());
//			Thread.sleep(1000);
			this.pista.aterrizaje(this.getName());
//			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
