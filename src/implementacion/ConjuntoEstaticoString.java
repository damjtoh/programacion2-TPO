package implementacion;

import tda.TDAConjunto;

public class ConjuntoEstaticoString implements TDAConjunto {
	
	String [] valores;
	int indice;

	public void inicializar(){
		valores = new String[100];
		indice = 0;
	}
		
	public void agregar(String valor){
		if(!this.pertenece(valor))
		{
			valores[indice]=valor;
			indice++;
		}
	}
	
	public void sacar(String valor){
		int pos=0;
		for(int i=0;i<indice;i++)
			if(valores[i]==valor)
				pos=i;
		if(pos!=indice)
		{
			for(int j=pos;j<indice;j++)
				valores[j]=valores[j+1];
			indice--;
		}
	}
	
//	public String elegir(){
//		int r =(int) Math.round(Math.random() * 99);
//		return valores[r];
//	}
	public String elegir() {
		if (!conjuntoVacio())
			return valores[indice-1];
		else
			return "";
	}
	
	public boolean pertenece(String valor){
		for (int i=0;i<indice;i++)
			if(valores[i]==valor)
				return true;
		return false;
	}
	
	public boolean conjuntoVacio(){
		return indice==0;
	}
}

	

	