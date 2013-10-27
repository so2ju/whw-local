/**
 * 
 */
package com.reposteria;

import com.utils.Global;

/**
 * @author JuanPablo
 * La solución utiliza números primos para representar el conjunto de ingredientes de la Receta.
 * Cada Ingrediente tiene asociado un número primo.
 * El "peso" de una Receta es el producto de los números primos de los Ingredientes que componen la Receta.
 * Un Ingrediente se agregó a una Receta, si el "peso" de la Receta es divisible por el número primo que representa al Ingrediente  
 * En forma recursiva se van generando todas las posibles combinaciones de ingredientes para armar la Receta.
 * Antes de agregar un Ingrediente a la Receta, se evalúa factibilidad de si aún queda disponible en la lista de posibles 
 * Ingredientes (1M, 3F,3C,3D) o ya se usaron todas las unidades disponibles del Ingrediente.(ingredientes.haySuficiente)
 * De esa manera se poda el árbol de posible soluciones reduciendo el tiempo de cómputo.
 * 
 * A efectos de mejorar los tiempos del algoritmo:
 * 			1) Se mantiene un "peso" total de la Receta, que permite validar la restrición 1M, 3F, 3C, 3D.
 * 			2) Se mantiene un "peso" por fila y otro por columna de manera que el Repostero pueda determinar si una Receta es o no rica. (esRica)
 * 
 * Si el Repostero determina que la Receta es rica,  se almacena en una lista que se imprime al final de la ejecución.
 *
 */
public class Iniciar {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ingredientes ingredientes=new Ingredientes();
		System.out.println("Sin Parametros, devuelve el numero total de Recetas.");
		System.out.println("Con -verbose, genera informacion por consola durante la ejecucion y un archivo de salida con las recetas. (recetas.txt)");
		Global.setVerbose(false);
		if (args.length>0){
			if (args[0].compareTo("-verbose")==0){
				Global.setVerbose(true);
			}
		}
		ingredientes.agregarIngrediente(Ingrediente.MASITA);
		for (int i=0;i<3;i++){
			ingredientes.agregarIngrediente(Ingrediente.CONFITE);
			ingredientes.agregarIngrediente(Ingrediente.DULCE);
			ingredientes.agregarIngrediente(Ingrediente.FRUTA);
		}
		
		for (int i=0;i<Global.MAX_INGREDIENTES;i++){
			System.out.println("Pos: " + i + " Ingrediente: " + ingredientes.dameIngrediente(i).getNomLargo());
		}
		
		Repostero repostero=new Repostero();
		repostero.setIngredientes(ingredientes);
		repostero.mezclar(0,0, new Receta());
		System.out.println("");	

		if ( Global.isVerbose()){
			repostero.toFile("recetas.txt");
		}
		System.out.println("Total de Recetas Ricas: " + repostero.totalRecetasRicas());
	}

}
