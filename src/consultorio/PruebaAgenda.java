package consultorio;

import java.util.Arrays;

import implementacion.AgendaDinamica;
import tda.IAgenda;
import tda.TDAColaPrioridad;
import tda.TDAConjunto;

public class PruebaAgenda {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IAgenda agenda = new AgendaDinamica();
		agenda.inicializar();
		agenda.agregar("Juan","20160620","Carlos"	,"05:45");
		agenda.agregar("Juan","20160620","Jose"		,"12:45");
		agenda.agregar("Juan","20160620","Juan"		,"15:45");
		agenda.agregar("Juan","20160620","Jeronimo"	,"11:45");
		agenda.agregar("Juan","20160620","Jacinto"	,"10:45");
		agenda.agregar("Juan","20160620","Joaquin"	,"13:45");
		agenda.agregar("Juan","20160620","Javier"	,"08:30");
		agenda.agregar("Juan","20160620","Julieta"	,"16:15");
		agenda.agregar("Juan","20160620","Juliana"	,"09:00");
		agenda.agregar("Juan","20160623","Antonia"	,"05:45");
		agenda.agregar("Juan","20160623","Carlos"	,"10:45");
		agenda.agregar("Juan","20160623","Ana"		,"13:45");
		agenda.agregar("Juan","20160623","Alma"		,"08:30");
		agenda.agregar("Juan","20160623","Aldana"	,"16:15");
		agenda.agregar("Juan","20160624","Carlos"	,"09:00");
		agenda.agregar("Juan","20160624","Maria"	,"10:00");
		agenda.agregar("Juan","20160624","Carlos"	,"11:00");
		agenda.agregar("Juan","20160624","Maria"	,"12:00");
		agenda.agregar("Juan","20160624","Carlos"	,"13:00");
		agenda.agregar("Juan","20160624","Maria"	,"14:00");
		agenda.agregar("Juan","20160624","Carlos"	,"15:00");
/** Juan */
		agenda.agregar(	"Marcela","20160620","Jose"		,	"12:45"	);
		agenda.agregar(	"Marcela","20160620","Marcela"	,	"15:45"	);
		agenda.agregar(	"Marcela","20160620","Jeronimo"	,	"11:45"	);
		agenda.agregar(	"Marcela","20160620","Jacinto"	,	"10:45"	);
		agenda.agregar(	"Marcela","20160622","Joaquin"	,	"13:45"	);
		agenda.agregar(	"Marcela","20160622","Javier"	,	"08:30"	);
		agenda.agregar(	"Marcela","20160620","Julieta"	,	"16:15"	);
		agenda.agregar(	"Marcela","20160620","Juliana"	,	"09:00"	);
		agenda.agregar(	"Marcela","20160623","Antonia"	,	"05:45"	);
		agenda.agregar(	"Marcela","20160623","Carlos"	,	"10:45"	);
		agenda.agregar(	"Marcela","20160623","Ana"		,	"13:45"	);
		agenda.agregar(	"Marcela","20160623","Alma"		,	"08:30"	);
		agenda.agregar(	"Marcela","20160623","Aldana"	,	"16:15"	);
		agenda.agregar(	"Marcela","20160624","Carlos"	,	"09:00"	);
		agenda.agregar(	"Marcela","20160624","Maria"	,	"10:00"	);
		agenda.agregar(	"Marcela","20160624","Carlos"	,	"11:00"	);
		agenda.agregar(	"Marcela","20160625","Maria"	,	"12:00"	);
		agenda.agregar(	"Marcela","20160625","Carlos"	,	"13:00"	);
		agenda.agregar(	"Marcela","20160625","Maria"	,	"15:00"	);
		agenda.agregar(	"Marcela","20160625","Carlos"	,	"10:00"	);
//		System.out.println(Arrays.deepToString(agenda.obtenerTurnosMedicoEnFecha("Marcela", "20160625")));

		
/** Marcela */
		agenda.agregar(	"Evaristo","20160630","Eustaquio"	,"12:00"	);
/** Evaristo */
		System.out.println("\n\nCargue la agenda, verifico los datos cargados\n\n");
		recorrerAgenda(agenda);
		
		agenda.eliminarTurno("Evaristo", "20160630", "Eustaquio");
		System.out.println("\n\nElimine el turno del medico Evaristo, no deberia aparecer\n\n");
		recorrerAgenda(agenda);
		
		agenda.eliminarTurno("Marcela", "20160625", "Carlos");
		System.out.println("\n\nElimine los turnos de Carlos con Marcela en dia 25, no deberia aparecer\n\n");
		recorrerAgenda(agenda);
		
		agenda.eliminar("Juan");
		System.out.println("\n\nElimine al medico Juan, no deberia aparecer\n\n");
		recorrerAgenda(agenda);
	}

	private static void recorrerAgenda(IAgenda agenda) {
		// TODO Auto-generated method stub
		String medico;
		
		/* Recorro la estructura por mÈdicos */
		System.out.println("\n\nRecorro la Agenda Por Medico\n\n");
		TDAConjunto medicos = agenda.obtenerMedicos();
		while(!medicos.conjuntoVacio())
		{	
			medico = medicos.elegir();
			System.out.println("medico : " + medico);
			System.out.println(" fecha \t paciente \t turno");
			String[][] turnos = agenda.obtenerTurnosMedico(medico);
			muestroDatos(turnos);
			medicos.sacar(medico);
		}
				
		/* Recorro la estructura por fechas */	
		System.out.println("\n\nRecorro la Agenda Por Fecha\n\n");
		TDAColaPrioridad fechas = agenda.obtenerFechas();
		System.out.println(" fecha\t\t\tmedico\t\tpaciente\tturno");
		while(!fechas.colaVacia())
		{
			String[][] turnos = agenda.obtenerTurnosFecha(fechas.primero());
			muestroDatos(turnos);
			fechas.desacolar();
		}
	}

	private static void muestroDatos(String[][] turnos) {
		// TODO Auto-generated method stub
		int rows = turnos.length;
		int cols = turnos[0].length;
		for(int i = 0; i<rows; i++){
			for(int j = 0; j<cols; j++)
			{
				System.out.print(turnos[i][j] + "\t\t");
			}
			System.out.println("");
		}
	}

}
