package PEVAL1;

/**
 * Clase Rio: donde definimos los m�todos que ejecutar�n los distintos hilos
 * creados en la clase Principal
 * 
 * @author Jes�s Romero Gonz�lez
 * @version 1.0
 *
 */
public class Rio {
	boolean puenteLevantado;
	boolean avisoBarco;
	int cocheCruzando;

	/**
	 * Constructor
	 * 
	 * puenteLevantado <boolean> controla el estado del puente (lentado / bajado)
	 * avisoBarco <boolean> controla cuando un barco avisa al puente cocheCruzando
	 * <int> cuenta cuantos coches est�n cruzando
	 */
	public Rio() {
		this.puenteLevantado = false;
		this.avisoBarco = false;
		this.cocheCruzando = 0;
	}

	/**
	 * M�todo cruzar: donde controlamos las acciones cuando pase un coche y un barco
	 * 
	 * @param vehiculo <String> nombre del veh�culo que est� pasando en ese momento
	 */
	public synchronized void cruzar(String vehiculo) {
		try {
			/* control si el hilo en ejecuci�n es un coche */
			if (vehiculo.contains("coche")) {
				System.out.println(vehiculo + " se acerca al cruce");
				/* control si el puente est� bajado y no ha avisado barco */
				if (!this.puenteLevantado && !this.avisoBarco) {
					System.out.println(vehiculo + " est� cruzando");
					/* contabilizamos el coche que est� cruzando */
					this.cocheCruzando++;
				}
				/* si un barco ha avisado pausamos el hilo en ejecuci�n */
				if (this.avisoBarco) {
					System.out.println(vehiculo + " no puede pasar");
					wait();
				}
			}
			/* control si el hilo en ejecuci�n es un barco */
			else {
				System.out.println(vehiculo + " se aproxima al puente");
				/* control si barco avisa */
				if (!this.avisoBarco) {
					System.out.println(vehiculo + " ha avisado");
				}
				/* control si puente est� levantado y no hay coches cruzando */
				if (this.puenteLevantado && this.cocheCruzando == 0) {
					System.out.println(vehiculo + " est� cruzando");
				}
				/* control si el puente est� bajado y hay coches cruzando */
				if (!this.puenteLevantado || this.cocheCruzando != 0) {
					System.out.println(vehiculo + " no puede pasar");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * M�todo salirDeCruce: donde controlamos cuando un veh�culo termina de cruzar
	 * el puente
	 * 
	 * @param vehiculo <String> nombre del veh�culo que est� pasando en ese momento
	 */
	public synchronized void salirDeCruce(String vehiculo) {
		/* control si el veh�culo es un coche */
		if (vehiculo.contains("coche")) {
			System.out.println(vehiculo + " ha salido del cruce");
			/* contabilizamos que el coche ha terminado de cruzar */
			this.cocheCruzando--;
		}
		/* control si el veh�culo es un barco */
		else {
			/* bajos el puente */
			this.puenteLevantado = false;
			System.out.println(vehiculo + " ha salido del cruce");
			System.out.println("\t-----BAJANDO PUENTE-----");
			/* eliminamos el aviso del barco */
			this.avisoBarco = false;
			/* despertamos todos los veh�culos que est�n esperando para cruzar� */
			this.notifyAll();
		}
	}

	/**
	 * M�todo avisar: donde controlamos cuando un barco avisa al puente para cruzar
	 * 
	 * @param vehiculo <String> nombre del veh�culo que est� pasando en ese momento
	 */
	public synchronized void avisar(String vehiculo) {
		System.out.println(vehiculo + " ha avisado");
		/* contabilizamos que ha avisado un barco */
		this.avisoBarco = true;
		/* detenemos todos los coches para que no crucen */
		this.cocheCruzando = 0;
		/* si el puente est� bajado y no hay coches cruzando levantamos el puente */
		if (!this.puenteLevantado && this.cocheCruzando == 0) {
			System.out.println("\t-----SUBIENDO PUENTE-----");
		}
		levantaPuente(vehiculo);
	}

	/**
	 * M�todo levantarPuente: donde levantamos el puente
	 * 
	 * @param vehiculo <String> nombre del veh�culo que est� pasando en ese momento
	 */
	public synchronized void levantaPuente(String vehiculo) {
		this.puenteLevantado = true;
		this.cocheCruzando = 0;
	}
}
