package exapsp2018;

public class PistaAterrizaje {
	boolean avisoDespegue;
	boolean avisoAterrizaje;
	int cola;
	
	public PistaAterrizaje() {
		this.avisoAterrizaje = false;
		this.avisoDespegue = false;
		this.cola = 0;
	}

	public synchronized void solicitarDespegue(String nombre) {
		try {
			System.out.println(nombre + ": solicita el despegue");
			if(this.avisoAterrizaje && this.cola != 0) {
				this.cola++;
				System.out.println("\t" + nombre + ": despegue denegado, pista ocupada");
				wait();
			}
			System.out.println(nombre + ": permiso concedido para despegar");
			this.avisoDespegue = false;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public synchronized void solicitarAterrizaje(String nombre) {
		try {
			System.out.println(nombre + ": solicita el aterrizaje");
			if(this.avisoDespegue && this.cola != 0) {
				this.cola++;
				System.out.println("\t" + nombre + ": aterrizaje denegado, pista ocupada");
				wait();
			}
			System.out.println(nombre + ": permiso concedido para aterrizar");
			this.avisoAterrizaje = false;
			notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized void realizarManiobra(String nombre) {
		if(this.avisoAterrizaje) {
			System.out.println(nombre + ": realiza maniobras de aproximación");
		}
	}

	public synchronized void despegue(String nombre) {
		System.out.println(nombre + ": está despegando");
		this.avisoDespegue = true;
		this.cola--;
		System.out.println("\t" + nombre + ": está en el aire");
		notifyAll();
	}

	public synchronized void aterrizaje(String nombre) {
		realizarManiobra(nombre);
		System.out.println(nombre + ": está aterrizando");
		this.avisoAterrizaje = true;
		this.cola--;
		System.out.println("\t" + nombre + ": ha tocado tierra");
		notifyAll();
	}
}
