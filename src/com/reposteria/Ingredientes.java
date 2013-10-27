package com.reposteria;


import com.utils.Global;

public class Ingredientes {

	private Ingrediente ingredientes[]=new Ingrediente[Global.MAX_INGREDIENTES];//ingredientes disponibles para las recetas: 1M, 3C, 3D , 3F
	private int tope=-1;
	public int agregarIngrediente(Ingrediente ingrediente){
		tope++;
		if (tope>=Global.MAX_INGREDIENTES) return  -1;
		ingredientes[tope]=ingrediente;
		return 0;
	}
	
	public Ingrediente dameIngrediente(int pos){
		if (tope>=Global.MAX_INGREDIENTES) return null;
		return ingredientes[pos];
	}

	/**
	 * Dada una Receta, se le agrega un ingrediente.
	 * Se evalua si el agregado respeta el total de ingredientes disponibles: 3C,3C,3F, 1M.
	 * 
	 * @param receta
	 * @param ingrediente
	 * @return
	 */
	public boolean haySuficiente(Receta receta, Ingrediente ingrediente){
		long peso=receta.getPesoReceta()*ingrediente.getPrimo(); //simulo agregar el ingrediente a la receta
		for (int i=0;i<Global.MAX_INGREDIENTES && peso!=1;i++){
			int primo = ingredientes[i].getPrimo();
			if (peso % primo==0){ // ya esta el ingrediente en la receta?
				peso=peso / primo; 
			}
		}
		if (peso==1) return true; //El ingrediente que se intenta agregar, respeta la restricción de cantidad de ingredientes.
		return false; 
	}
	
}
