package com.reposteria;

public enum Ingrediente {
	VACIA(-1,"*","Vacia"),
	MASITA(2,"M","Masita"),
	DULCE(3,"D","Dulce"),
	FRUTA(5,"F","Fruta"),
	CONFITE(7,"C","Confite");
	private int primo;
	private String nomCorto;
	private String nomLargo;
	Ingrediente(int primo,String nomCorto,String nomLargo){
		this.setPrimo(primo);
		this.setNomCorto(nomCorto);
		this.setNomLargo(nomLargo);
	}
	public void setPrimo(int primo) {
		this.primo = primo;
	}
	public int getPrimo() {
		return primo;
	}
	public void setNomCorto(String nomCorto) {
		this.nomCorto = nomCorto;
	}
	public String getNomCorto() {
		return nomCorto;
	}
	public void setNomLargo(String nomLargo) {
		this.nomLargo = nomLargo;
	}
	public String getNomLargo() {
		return nomLargo;
	}
	
}
