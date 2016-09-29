package implementacion;

import tda.ABBTDATurnos;
import implementacion.NodoABB;



public class ABBTurnos implements ABBTDATurnos {

	private NodoABB raiz;
	
	public void inicializar() {
		raiz = null;
	}
	
	public void agregar(String paciente, String turno) {
		if (raiz == null) {
			raiz = new NodoABB();
			raiz.turno = turno;
			raiz.paciente = paciente;
			raiz.hijoD = new ABBTurnos();
			raiz.hijoI = new ABBTurnos();
			raiz.hijoD.inicializar();
			raiz.hijoI.inicializar();
		} else if(turno.compareTo(raiz.turno) > 0) {
			raiz.hijoD.agregar(paciente, turno);

		} else if(turno.compareTo(raiz.turno) < 0) {
			raiz.hijoI.agregar(paciente, turno);
		}
	}
	
	public void eliminar(String turno) {
		if (raiz != null) {
			// Es hoja
				
			if (raiz.turno.equals(turno) && raiz.hijoI.arbolVacio() && raiz.hijoD.arbolVacio()) {
				raiz = null;
			}
			else if (raiz.turno == turno && !raiz.hijoI.arbolVacio()) {
				String[] nodo = this.mayor(raiz.hijoI).split("|");
				raiz.turno = nodo[0];
				raiz.paciente = nodo[1];
				raiz.hijoI.eliminar(raiz.turno);
			} else if (raiz.turno == turno && !raiz.hijoD.arbolVacio()) {
				String[] nodo = this.menor(raiz.hijoD).split("@");
				raiz.turno = nodo[0];
				raiz.paciente = nodo[1];
				raiz.hijoD.eliminar(raiz.turno);
				// CompareTo == >
			} else if (turno.compareTo(raiz.turno) < 0) {
				raiz.hijoI.eliminar(turno);
			}else
				raiz.hijoD.eliminar(turno);
		}
	}
	
	public String paciente() {
		return raiz.paciente;
	}
	
	public String turno() {
		return raiz.turno;
	}
	
	public ABBTDATurnos hijoIzquierdo() {
		return raiz.hijoI;
	}
	
	public ABBTDATurnos hijoDerecho() {
		return raiz.hijoD;
	}
	
	public boolean arbolVacio() {
		return (raiz == null);
	}
	
	private String mayor(ABBTDATurnos a) {
		if (a.hijoDerecho().arbolVacio())
			return a.turno()+"@"+a.paciente();
		else
			return mayor(a.hijoDerecho());
	}
	
	private String menor(ABBTDATurnos a) {
		if (a.hijoIzquierdo().arbolVacio())
			return a.turno()+"@"+a.paciente();
		else
			return menor(a.hijoIzquierdo());
	}
}
