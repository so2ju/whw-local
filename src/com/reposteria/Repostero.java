package com.reposteria;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.utils.Global;

public class Repostero {
	private List<Receta> recetas=new ArrayList<Receta>();
 	private Ingredientes ingredientes; //Ingredientes disponibles
 	
 	/**
 	 * Genera las combinaciones de Ingredientes para las posibles Recetas.
 	 * En caso de encontrar una Receta rica, se almacena en la lista "recetas".
 	 *  
 	 * @param x Posicion X en la tortera
 	 * @param y Posicion Y en la tortera
 	 * @param receta Receta que esta siendo elaborada
 	 * @return
 	 */
	public void mezclar(int x, int y, Receta receta){
		if (x>=Global.LARGO_TORTERA){
			y++;
			x=0;
		}
		if (receta.isTorteraLlena()){
			if (esRica(receta)){
				if (Global.isVerbose()){
					System.out.println("\t\t\t ES RICA :) !!!!");
					receta.print();
				}
				recetas.add(new Receta(receta));
				
			}
			else{
				if (Global.isVerbose()){
					System.out.println("NO ES RICA :(");
				}
			}
			return;
		}

		for (Ingrediente ingrediente:Ingrediente.values()){
			if (ingrediente.ordinal()==Ingrediente.VACIA.ordinal()) continue;
			if (ingredientes.haySuficiente(receta, ingrediente)){ //poda de soluciones, respetando la cantidad de ingredientes disponibles
				receta.agregarIngrediente(ingrediente, x,y);
				mezclar(x+1,y,new Receta(receta));
			}			
		}
	}
 	
 	public void print(){
		for (Receta t : recetas){
			t.print();
		}
		System.out.println("");
		System.out.println("Total de Recetas: " + recetas.size());
	}

	public void toFile(String filename){
		long total=recetas.size();
		FileOutputStream fop = null;
		File file;

		try {

			file = new File(filename);
			fop = new FileOutputStream(file);
			int i=0;
			for (Receta r : recetas){
		 
					fop.write(r.toStringFlat().getBytes());
					fop.flush();
					i++;
					if  (i % 10 ==0 ){
						System.out.print(".");
					}
					if  (i % 500 ==0 ){
						System.out.println("");
						System.out.println("Van: " + i + "/" + total);
					}
		 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public void setIngredientes(Ingredientes ingredientes) {
		this.ingredientes = ingredientes;
	}

	public Ingredientes getIngredientes() {
		return ingredientes;
	}

	/**
	 * Recorro la Receta.
	 * Si una fila ó una columna esRica, ya paro de buscar.
	 * @param t
	 * @return
	 */
	private boolean esRica(Receta t){
		long peso=0;
		for (int i=0;i<Global.LARGO_TORTERA;i++){
			peso=t.damePesoColumna(i);
			if (rica(peso)) return true;
			for (int j=0;j<Global.ANCHO_TORTERA;j++){
				peso=t.damePesoFila(j);
				if (rica(peso)) return true;				
			}
		}
		return false;
	}
	
	/**
	 * Una Receta es Rica si 3 ingredientes iguales o 2 ingredientes iguales y 1 masita.
	 * @param valor
	 * @return
	 */
	private boolean rica(long valor){
		long peso;
		long primo;
		long masita;
		masita=Ingrediente.MASITA.getPrimo();
		
		primo = Ingrediente.CONFITE.getPrimo();
		peso=primo*primo*primo;
		if (peso==valor) return true;
		peso=primo*primo*masita;
		if (peso==valor) return true;

		primo = Ingrediente.DULCE.getPrimo();
		peso=primo*primo*primo;
		if (peso==valor) return true;
		peso=primo*primo*masita;
		if (peso==valor) return true;
		
		
		primo = Ingrediente.FRUTA.getPrimo();
		peso=primo*primo*primo;
		if (peso==valor) return true;
		peso=primo*primo*masita;
		if (peso==valor) return true;

		return false;
	}

	public int totalRecetasRicas(){
		return recetas.size();
	}
	
}
