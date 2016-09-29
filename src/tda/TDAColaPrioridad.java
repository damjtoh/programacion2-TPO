package tda;

public interface TDAColaPrioridad {

	//Inicializado
	void acolar(String x);
	//Inicializado y no vacía
	void desacolar();
	//Inicializado y no vacía
	String primero();
	//Inicializado
	boolean colaVacia();
	//Inicializado y no vacía
	int prioridad();
	void inicializar();
}
