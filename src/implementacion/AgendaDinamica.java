package implementacion;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Stack;

import tda.ABBTDATurnos;
import tda.IAgenda;
import tda.TDAColaPrioridad;
import tda.TDAConjunto;

public class AgendaDinamica implements IAgenda {
	
	public class NodoTurno {
		String paciente;
		String turno;
		String medico;
		String fecha;
		
		public NodoTurno(String p, String t, String m, String f) {
			paciente = p;
			turno = t;
			medico = m;
			fecha = f;
		}
	}
	
	NodoClave origen;
	TDAConjunto aux2;
	
	public void inicializar(){
		origen = null;
		aux2 = new ConjuntoEstaticoString();
		aux2.inicializar();
	}
	
	private NodoClave getNodoByNombre(String medico) {
		NodoClave aux = origen;
		while (aux != null && aux.clave != medico)
			aux = aux.siguiente;
		return aux;
	}

	
	public void agregar(String medico, String fecha, String paciente, String turno){
		NodoClave nodoMedico = getNodoByNombre(medico);
		
		// Si no existe el medico -> lo agrego
		// nodoF: nodo fecha
		if (nodoMedico == null) {
			NodoClave nuevoMedico = new NodoClave();
			nuevoMedico.clave = medico;
			
			NodoValor nodoF = new NodoValor();
			nodoF.fecha = fecha;
			
			ABBTDATurnos arbolTurnos = new ABBTurnos();
			arbolTurnos.inicializar();
			arbolTurnos.agregar(paciente, turno);
			
			nodoF.turnos = arbolTurnos;
			nuevoMedico.valores = nodoF;
			nuevoMedico.siguiente = origen;
			origen = nuevoMedico;

		} else {
			// Existe el medico y por lo menos tiene que tener una fecha y un turno (por definición).
			NodoValor nodoF = nodoMedico.valores;
			// Me fijo si la primer fecha de la lista es la que estoy buscando
			if (nodoF.fecha == fecha) {
				// Agregamos el turno en el arbol.
				ABBTDATurnos nuevoTurno = nodoF.turnos;
				nuevoTurno.agregar(paciente, turno);
			} else {
				// Recorro todas las fechas para ver si existe
				NodoValor auxNodoF = nodoF;
				while (auxNodoF.sigFecha != null && auxNodoF.sigFecha.fecha != fecha)
					auxNodoF = auxNodoF.sigFecha;
				if (auxNodoF.sigFecha == null) {
					//La fecha no existe entonces la tengo que agregar
					// Creamos un arbol
					ABBTDATurnos nuevoTurno = new ABBTurnos();
					nuevoTurno.inicializar();
					nuevoTurno.agregar(paciente, turno);
					// Creamos el nodo de fecha
					NodoValor nuevaFecha = new NodoValor();
					nuevaFecha.fecha = fecha;
					// Apuntamos a nuestor arbol previamente creado
					nuevaFecha.turnos = nuevoTurno;
					auxNodoF.sigFecha = nuevaFecha;
					
				} else {
					// La fecha está en la lista, entonces suponemos que ya existe un arbol de turnos
//					System.out.println("La fecha está en la lista, voy a agregar el turno en: "+auxNodoF.sigFecha.fecha);
//					System.out.println("Medico: "+medico+"\nPaciente: "+paciente+"\nTurno: "+turno+"\nFecha: "+fecha);
					ABBTDATurnos arbolTurnos = auxNodoF.sigFecha.turnos;
					arbolTurnos.agregar(paciente, turno);
					
				}
			}
		}
	}
	
		
	public void eliminar(String medico){
		
		// Si es el primero lo elimino
		if (origen.clave == medico) {
			origen = origen.siguiente;
		} else {
			// No es el primero entonces lo busco.
			NodoClave aux = origen;
			while (aux.siguiente != null && aux.siguiente.clave != medico)
				aux = aux.siguiente;
			// Si no es null -> encontre mi medico
			if (aux != null) {
				aux.siguiente = aux.siguiente.siguiente;
			}
		}
		
	}

	
	public void eliminarFecha(String medico, String fecha){
		NodoClave nodoMedico = getNodoByNombre(medico);
		// Checkeo si existe el medico.
		if (nodoMedico == null)
			return;
		
		if (nodoMedico.valores == null)
			return;
		//Si es la primer fecha lo elimino
		if (nodoMedico.valores.fecha == fecha){
			nodoMedico.valores = nodoMedico.valores.sigFecha;
		}
		else{
			//Recorro buscando fecha
			NodoValor auxFecha = nodoMedico.valores;
			while(auxFecha.sigFecha != null){
				if(auxFecha.sigFecha.fecha == fecha){
					auxFecha.sigFecha = auxFecha.sigFecha.sigFecha;
					break;
				}
				auxFecha = auxFecha.sigFecha;
			}
		}
	}
	
	//necesitamos crear un conjunto con todos los turnos del arbol de un determinado paciente para recorrer.
	public void eliminarTurno(String medico, String fecha, String paciente){	
		String turno;
		NodoClave nodoMedico = getNodoByNombre(medico);
		if (nodoMedico == null)
			return;
		NodoValor auxFecha = getNodoByFecha(nodoMedico, fecha);
		if (auxFecha == null)
			return;
		TDAConjunto conj = new ConjuntoEstaticoString ();
		conj = turnosPaciente(auxFecha.turnos, paciente);
		
		while (!conj.conjuntoVacio()){
				turno = conj.elegir();
				auxFecha.turnos.eliminar(turno);
				conj.sacar(turno);
		}
		
		if (auxFecha.turnos.arbolVacio()) {
			//No le quedan más turnos
			this.eliminarFecha(nodoMedico.clave, fecha);
			if (nodoMedico.valores == null)
				this.eliminar(nodoMedico.clave);
		}
			
	}

	private NodoValor getNodoByFecha(NodoClave nodoMedico, String fecha) {
		
		NodoValor aux = nodoMedico.valores;
		while (aux != null && aux.fecha != fecha)
			aux = aux.sigFecha;
		return aux;
	}
	
	private TDAConjunto turnosPaciente (ABBTDATurnos A, String paciente){
		
		//si tiene pacientes
		if (!A.arbolVacio()) {
			//si encuentro el paciente agrego el turno al conjunto
			    if (A.paciente() == paciente) {
			    	aux2.agregar(A.turno());
					if (A.hijoIzquierdo().arbolVacio() && A.hijoDerecho().arbolVacio()) {
						return aux2;
					}
					turnosPaciente(A.hijoIzquierdo(), paciente);
					turnosPaciente(A.hijoDerecho(), paciente);
				} else {
					turnosPaciente(A.hijoIzquierdo(), paciente);
					turnosPaciente(A.hijoDerecho(), paciente);
				} 
		}
		return aux2;
	}

		
	public TDAConjunto obtenerMedicos(){
		NodoClave aux = origen;
		TDAConjunto medicos = new ConjuntoEstaticoString();
		medicos.inicializar();
		while (aux != null) {
			medicos.agregar(aux.clave);
			aux = aux.siguiente;
		}
		return medicos;
	}

	
	public TDAColaPrioridad obtenerFechas(){
		NodoClave nodoMedico = origen;
		TDAColaPrioridad fechas = new ColaEstaticaString();
		fechas.inicializar();
		while (nodoMedico != null) {
			NodoValor auxFecha = nodoMedico.valores;
			while (auxFecha != null) {
				fechas.acolar(auxFecha.fecha);
				auxFecha = auxFecha.sigFecha;
			}
			nodoMedico = nodoMedico.siguiente;
		}
		return fechas;
	}

	
	public TDAColaPrioridad obtenerFechasMedico(String medico){
		// Creo una cola y la inicializo
		TDAColaPrioridad fechas = new ColaEstaticaString();
		fechas.inicializar();
		
		// Busco el medico por nombre
		NodoClave nodoMedico = getNodoByNombre(medico);
		if (nodoMedico == null)
			return fechas;
		
		// Guardo el puntero a la primer fecha
		NodoValor auxFecha = nodoMedico.valores;
		// Recorro todas las fechas y las voy acolando
		while (auxFecha != null) {
			fechas.acolar(auxFecha.fecha);
			auxFecha = auxFecha.sigFecha;
		}
		return fechas;
	}

	public String[][] obtenerTurnosFecha(String fecha){
		NodoClave nodoMedico = origen;
		Stack turnos = new Stack();
		while (nodoMedico != null) {
			// En nodoFecha guardamos la primer fecha del medico en cuestión. 
			NodoValor nodoFecha = nodoMedico.valores;
			while (nodoFecha != null && nodoFecha.fecha != fecha)
				nodoFecha = nodoFecha.sigFecha;
			if (nodoFecha != null) {
				// Encontré la fecha
				//Recorro el arbol y pusheo al stack
				ABBToStack(nodoFecha.turnos, turnos, nodoMedico.clave, nodoFecha.fecha);
			}
			nodoMedico = nodoMedico.siguiente;
		}
		String[][] result = new String[turnos.size()][4];
		Iterator<NodoTurno> iter = turnos.iterator();
		int i=0;
		while (iter.hasNext()){
			NodoTurno aux = iter.next();
			result[i][0] = aux.fecha;
			result[i][1] = aux.medico;
			result[i][2] = aux.paciente;
			result[i][3] = aux.turno;
			i++;
		}
		// x.compareTo(y) == x > y 
		Arrays.sort(result, new Comparator<String[]>() {
            @Override
            public int compare(final String[] record1, final String[] record2) {
            	int c;
                c = record1[0].compareTo(record2[0]);
                if (c == 0)
                   c = record1[1].compareTo(record2[1]);
                if (c == 0)
                   c = record1[3].compareTo(record2[3]);
                return c;
            }
        });
		return result;
	}
	
	private void ABBToStack(ABBTDATurnos node, Stack<NodoTurno> s, String medico, String fecha) {
		if(!node.arbolVacio()){
			s.push(new NodoTurno(node.paciente(), node.turno(), medico, fecha));
			if (!node.hijoDerecho().arbolVacio())
				ABBToStack(node.hijoDerecho(), s, medico, fecha);
			if (!node.hijoIzquierdo().arbolVacio())
				ABBToStack(node.hijoIzquierdo(), s, medico, fecha);	
		}
	}
	
	public String[][] obtenerTurnosMedico(String medico){
		// Busco el medico por nombre
		NodoClave nodoMedico = getNodoByNombre(medico);
		if (nodoMedico != null) {
			// Encontré el medico y lo guardo en <nodoMedico>
			NodoValor auxFecha = nodoMedico.valores;
			Stack turnos = new Stack();
			while (auxFecha != null) {
				ABBToStack(auxFecha.turnos, turnos, nodoMedico.clave, auxFecha.fecha);
				auxFecha = auxFecha.sigFecha;
			}
			String[][] result = new String [turnos.size()][3];
			Iterator<NodoTurno> iter = turnos.iterator();
			int i=0;
			while (iter.hasNext()){
				NodoTurno aux = iter.next();
				result[i][0] = aux.fecha;
				result[i][1] = aux.paciente;
				result[i][2] = aux.turno;
				i++;
			}
			Arrays.sort(result, new Comparator<String[]>() {
	            @Override
	            public int compare(final String[] record1, final String[] record2) {
	            	int c;
	                c = record1[0].compareTo(record2[0]);
	                if (c == 0)
	                   c = record1[2].compareTo(record2[2]);
	                return c;
	            }
	        });
			return result;
		} else {
			return new String[0][0];
		}
	}

		
	private String [][] caja;    	                //mi arreglo bidireccional
	private int x=0;			//indice de las filas 
	private int y=0;			//indice de las columnas
	private int cantidad=0;			//cantidad de valores
	private int filas;			//cantidad de filas
	private int columnas=2;			//cantidad de columnas
	private int max; 
	
	
	
	public String[][] obtenerTurnosMedicoEnFecha(String medico, String fecha){
		NodoClave nodoMedico = getNodoByNombre(medico);
		NodoValor auxFecha = getNodoByFecha(nodoMedico, fecha);
		ABBTDATurnos A =auxFecha.turnos;
		filas= contarElementosABB(A);
		caja = new String [x][y];
		max = filas*columnas;
		enOrden(A);
		return caja;
		
	}
	private void cargarMatriz(String nombre, String hora ){				//carga valores en la matriz, los mismos pueden estar repetidos
		if (cantidad<=max){
			cantidad=cantidad+1;
			if (x<filas && y<columnas){
				caja[x][y]=nombre;
				y++;
				caja[x][y]=hora;
				y++;
				if (y==columnas){
				x++;
				y=0;
				}
			}
		}
	}
	
	private void enOrden(ABBTDATurnos A) {
		if (!A.arbolVacio()) {
			enOrden(A.hijoIzquierdo());
			cargarMatriz(A.paciente(),A.turno());
			enOrden(A.hijoDerecho());
		}
	}
    	
    private int contarElementosABB(ABBTDATurnos A) {
		if (A.arbolVacio()) {
			return 0;
		} else {
			return (1 + contarElementosABB(A.hijoIzquierdo()) + contarElementosABB(A.hijoDerecho()) );
		}
	}

}


