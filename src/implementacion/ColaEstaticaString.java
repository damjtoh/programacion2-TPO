package implementacion;

import tda.TDAColaPrioridad;

public class ColaEstaticaString implements TDAColaPrioridad {
	private int indice;
	private String aux;
	private int aux2;
	private String []valores;
	private int []prioridades;
	
	public void inicializar (){
		indice=0;
		aux="";
		aux2=0;
		valores=new String [100];
		prioridades=new int [100];
	}
	public void acolar (String v){
		int p=0;
		p= Integer.parseInt(v);
		if(indice==0){
			valores[indice]=v;
			prioridades[indice]=p;
			indice++;
		}else{
			valores[indice]=v;
			prioridades[indice]=p;
			indice++;
			for(int i=0;i<indice;i++){
				for(int j=0;j<indice;j++){
					if(prioridades[i]<prioridades[j]){
						aux=valores[i];
						aux2=prioridades[i];
						valores[i]=valores[j];
						prioridades[i]=prioridades[j];
						valores[j]=aux;
						prioridades[j]=aux2;
					}
				}
			}
		}

	}
	public void desacolar (){
		for (int i=0; i<indice-1; i++){
			valores[i]=valores[i+1];
			prioridades[i]=prioridades[i+1];
		}
		indice--;
	}
	public boolean colaVacia(){
		return indice==0;
	}
	
	public String primero(){
		return valores[0];
	}
	public int prioridad() {
		return prioridades[0];
	}
}