package com.reposteria;

import com.utils.Global;

public class Receta {
	private Ingrediente tortera[][]=new Ingrediente[Global.LARGO_TORTERA][Global.ANCHO_TORTERA];
	private Long pesoColumnas[]=new Long[Global.LARGO_TORTERA];
	private Long pesoFilas[]=new Long[Global.ANCHO_TORTERA];
	private Long pesoReceta=1L;

	private int totalIngredientes=Global.LARGO_TORTERA*Global.ANCHO_TORTERA; //Mejor tener la cuenta hecha y ahorrar multiplicacion...
	private int totalPedazos=0;	
	
	private boolean torteraLlena=false;
	
	public boolean isTorteraLlena() {
		torteraLlena=totalIngredientes==totalPedazos;
		return torteraLlena;
	}

	public Long getPesoReceta() {
		return pesoReceta;
	}

	public int getTotalPedazos() {
		return totalPedazos;
	}

/**
 * Copia una Receta
 * @param receta
 */
	public Receta(Receta receta){
		super();

		for (int i=0;i<Global.LARGO_TORTERA;i++){
			pesoColumnas[i]=-1L;
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				tortera[i][j]=Ingrediente.VACIA;
				pesoFilas[j]=(long) 1L;//Ingrediente.VACIA.getPrimo();
			}
		}		
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				Ingrediente ingrediente = receta.dameIngrediente(i, j);
				if (ingrediente.ordinal()==Ingrediente.VACIA.ordinal()) continue; //sino queda negativo al copiar receta parcial!!!
				this.agregarIngrediente(ingrediente,i,j);
			}
		}		
	}
	
	public Receta(){
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			pesoColumnas[i]=-1L;
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				tortera[i][j]=Ingrediente.VACIA;
				pesoFilas[j]=(long) Ingrediente.VACIA.getPrimo();
			}
		}
	}
	
	public int agregarIngrediente(Ingrediente ingrediente,int columna, int fila){
		tortera[columna][fila]=ingrediente;
		totalPedazos++;
		int primo = ingrediente.getPrimo();
		pesoColumnas[columna]=pesoColumnas[columna]*primo;
		pesoFilas[fila]=pesoFilas[fila]*primo;
		pesoReceta=pesoReceta*primo;
		return totalPedazos;
	}
	
	public Ingrediente dameIngrediente(int columna,int fila){
		return tortera[columna][fila];
	}
	
	public void print(){
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				System.out.print(tortera[i][j].getNomCorto());

			}
			System.out.println();
		}
		System.out.println("**********************************************");
	}
	
	
	
	
	public Long damePesoFila(int fila){
		return pesoFilas[fila];
	}

	public Long damePesoColumna(int columna){
		return pesoColumnas[columna];
	}

	public String toString(){
		String resu="";
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				resu=resu + tortera[i][j].getNomCorto();

			}
			resu=resu + "\n";
		}
		return resu + "-----------\n";
	}

	public String toStringFlat(){
		String resu="";
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				resu=resu + tortera[i][j].getNomCorto();

			}
		}
		return resu + "\n";
	}
}
